package fr.isika.cda.beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.lesson.PhysicalOption;
import fr.isika.cda.entities.lesson.SynchronousLesson;
import fr.isika.cda.entities.lesson.VirtualOption;
import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.student.LearningPath;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.repositories.LearningPathRepository;
import fr.isika.cda.repositories.PhysicalRepository;
import fr.isika.cda.repositories.SynchronousLessonRepository;
import fr.isika.cda.repositories.VirtualRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class SynchronousLessonBean {
	@Inject
	private SynchronousLessonRepository synchronousLessonRepository;
	@Inject
	private LearningPathRepository learningPathRepository;
	@Inject
	private VirtualRepository virtualRepository;
	@Inject
	private PhysicalRepository physicalRepository;

	private SynchronousLesson lesson = new SynchronousLesson();
	private School school;

	public List<SynchronousLesson> getSynchronousLessonsByIdSchool() {
		school = SessionUtils.getCurrentSchool();
		return synchronousLessonRepository.getFuturSynchronousLessonsByIdSchool(school.getId());
	}

	public List<SynchronousLesson> getSynchronousLessonsByLevel() {
		school = SessionUtils.getCurrentSchool();
		return synchronousLessonRepository.getFuturSynchronousLessonsByLevel(school.getId(), lesson.getLevel());

	}

	public List<SynchronousLesson> getSynchronousLessonsBySubject() {
		school = SessionUtils.getCurrentSchool();
		return synchronousLessonRepository.getFuturSynchronousLessonsBySubject(school.getId(), lesson.getSubject());
	}

	public String freeSeatsNumbers(SynchronousLesson synchronousLesson) {
		List<LearningPath> learningPaths = learningPathRepository.getLearningPathsByActivity(synchronousLesson.getId());
		int numberSeatsFree = synchronousLesson.getMaxStudentNumber() - learningPaths.size();
		return numberSeatsFree + "";
	}

	public String toStringClasseDate(LocalDateTime localDate) {
		final DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		return localDate.format(customFormatter);
	}

	public boolean isStudent() {
		return (SessionUtils.getConnectedMember() instanceof Student);
	}

	public boolean showInscriptionLesson(SynchronousLesson synchronousLesson) {
		Member member = SessionUtils.getConnectedMember();
		if (member instanceof Student) {
			LearningPath learningPath = learningPathRepository.getLearningPathBySynchronousLessonIdAndStudentID(synchronousLesson.getId(), member.getId());
			if (learningPath == null) {
				return true;
			}
		}
		return false;
	}

	public String create(SynchronousLesson synchronousLesson) {
		LearningPath learningPath = new LearningPath();
		learningPath.setActivity(synchronousLesson);
		learningPath.setStudent((Student) SessionUtils.getConnectedMember());
		learningPathRepository.save(learningPath);
		return "indexSchool?faces-redirect=true";
	}

	public String typeOfLesson(Long idSynchronousLesson) {
		if (idSynchronousLesson != null) {
			Optional<VirtualOption> virtualOption = virtualRepository
					.getVirtualOptionBySynchronousLessonById(idSynchronousLesson);
			if (virtualOption.isPresent()) {
				return "Le cours est en distanciel sur : " + virtualOption.get().getPlateforme();
			} else {
				PhysicalOption physicalOption = physicalRepository
						.getPhysicalOptionBySynchronousLessonById(idSynchronousLesson);
				return "Le cours est en présentiel à " + physicalOption.getAddress().getTown();
			}
		}
		return "";

	}

	public SynchronousLesson getLesson() {
		return lesson;
	}

	public void setLesson(SynchronousLesson lesson) {
		this.lesson = lesson;
	}

	public Map<String, SubjectEnum> subjects() {
		return SubjectEnum.subjects;
	}

	public Map<String, AcademicLevel> levels() {
		return AcademicLevel.Academiclevels;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
}
