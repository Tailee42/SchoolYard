package fr.isika.cda.beans;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.common.SchoolTypeEnum;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.lesson.PhysicalOption;
import fr.isika.cda.entities.lesson.SynchronousLesson;
import fr.isika.cda.entities.lesson.VirtualOption;
import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.student.LearningPath;
import fr.isika.cda.entities.student.StatusLearningPath;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.repositories.LearningPathRepository;
import fr.isika.cda.repositories.PhysicalRepository;
import fr.isika.cda.repositories.SynchronousLessonRepository;
import fr.isika.cda.repositories.VirtualRepository;
import fr.isika.cda.utils.SessionUtils;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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

    public List<SynchronousLesson> getSynchronousLessonsByIdSchool() {
        School school = SessionUtils.getCurrentSchool();
        return synchronousLessonRepository.getFuturSynchronousLessonsByIdSchool(school.getId());
    }

    public List<SynchronousLesson> getSynchronousLessonsByIdMember() {
        Member member = SessionUtils.getConnectedMember();
        return synchronousLessonRepository.getFuturSynchronousLessonsByIdMember(member.getId());
    }
    
    public List<SynchronousLesson> getSynchronousLessonsByLevel() {
        School school = SessionUtils.getCurrentSchool();
        return synchronousLessonRepository.getFuturSynchronousLessonsByLevel(school.getId(), lesson.getLevel());
       
    }

    public List<SynchronousLesson> getSynchronousLessonsBySubject() {
    	School school = SessionUtils.getCurrentSchool();
        return synchronousLessonRepository.getFuturSynchronousLessonsBySubject(school.getId(), lesson.getSubject());
    }
    

    public String freeSeatsNumbers(SynchronousLesson synchronousLesson) {
        List<LearningPath> learningPaths = learningPathRepository.getLearningPathsByActivity(synchronousLesson.getId());
        int numberSeatsFree = synchronousLesson.getMaxStudentNumber() - learningPaths.size();
        return numberSeatsFree + "";
    }

    public String toStringClasseDate(LocalDateTime localDate) {
        final DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return localDate.format(customFormatter);
    }
    public boolean isStudent() {
        return (SessionUtils.getConnectedMember() instanceof Student);
    }

    public String create(SynchronousLesson synchronousLesson) {
        LearningPath learningPath = new LearningPath();
        learningPath.setActivity(synchronousLesson);
        learningPath.setStatusLearningPath(StatusLearningPath.ACTIVE);
        learningPath.setStudent((Student) SessionUtils.getConnectedMember());
        learningPath.setRegistrationDate(LocalDateTime.now());
        learningPathRepository.save(learningPath);
        return "indexSchool?faces-redirect=true";
    }

    public String typeOfLesson(Long idSynchronousLesson) {
        if (idSynchronousLesson != null) {
            Optional<VirtualOption> virtualOption = virtualRepository.getVirtualOptionBySynchronousLessonById(idSynchronousLesson);
            if (virtualOption.isPresent()) {
                return "Le cours est en distanciel sur : " + virtualOption.get().getPlateforme();
            } else {
                PhysicalOption physicalOption = physicalRepository.getPhysicalOptionBySynchronousLessonById(idSynchronousLesson);
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
    
    public SubjectEnum[] subjects() {
        return SubjectEnum.values();
    }
    
    public AcademicLevel[] levels() {
        return AcademicLevel.values();
    }

}
