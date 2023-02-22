package fr.isika.cda.beans;

import fr.isika.cda.entities.lesson.SynchronousLesson;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.student.LearningPath;
import fr.isika.cda.repositories.LearningPathRepository;
import fr.isika.cda.repositories.SynchronousLessonRepository;
import fr.isika.cda.utils.SessionUtils;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class SynchronousLessonBean {
    @Inject
    private SynchronousLessonRepository synchronousLessonRepository;
    @Inject
    private LearningPathRepository learningPathRepository;


    public List<SynchronousLesson> getSynchronousLessonsByIdSchool() {
        School school = SessionUtils.getCurrentSchool();
        List<SynchronousLesson> synchronousLessons = synchronousLessonRepository.getSynchronousLessonsByIdSchool(school.getId());
        return synchronousLessons;
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
}
