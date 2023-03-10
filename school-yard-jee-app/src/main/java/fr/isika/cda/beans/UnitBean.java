package fr.isika.cda.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.lesson.AsynchronousLesson;
import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.student.LearningPath;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.repositories.AsynchronousLessonRepository;
import fr.isika.cda.repositories.LearningPathRepository;
import fr.isika.cda.repositories.UnitRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class UnitBean implements Serializable {

	private static final long serialVersionUID = -2774451567369606563L;
	private Unit unit = new Unit();
	private Long id;
	private School school;

	@Inject
	private UnitRepository unitRepository;
	@Inject
	private AsynchronousLessonRepository asynchronousLessonRepository;
	@Inject
	private LearningPathRepository learningPathRepository;


	public String saveAsFavorite() {
		AsynchronousLesson asynchronousLesson = new AsynchronousLesson();
		asynchronousLesson.setTitle(unit.getTitle());
		asynchronousLesson.setLevel(unit.getLevel());
		asynchronousLesson.setSubject(unit.getSubject());
		asynchronousLesson.setTeacher(unit.getTeacher());
		if (id != 0 ) {
			asynchronousLesson.setUnit(unitRepository.getUnitById(id));
		} else {
			asynchronousLesson.setUnit(unitRepository.getUnitById(unit.getId()));
		}
		asynchronousLessonRepository.save(asynchronousLesson);
		LearningPath learningPath = new LearningPath();
		learningPath.setActivity(asynchronousLesson);
		learningPath.setStudent((Student) SessionUtils.getConnectedMember());
		learningPathRepository.save(learningPath);
		return "";
	}

	public boolean isFavorite() {
		List<LearningPath> learningPathList = learningPathRepository
				.getLearningPathsByStudentId(SessionUtils.getConnectedMember().getId());
		for (LearningPath learningPath : learningPathList) {
			if (learningPath.getActivity() instanceof AsynchronousLesson) {
				AsynchronousLesson asynchronousLesson = (AsynchronousLesson) learningPath.getActivity();
				if (asynchronousLesson.getUnit().getId().equals(unit.getId())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isStudent() {
		return SessionUtils.getConnectedMember() instanceof Student;
	}

	public void getUnitId() {
		if (id != null && id != 0) {
			unit = unitRepository.getUnitById(id);
			school = SessionUtils.getCurrentSchool();
		}
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
}
