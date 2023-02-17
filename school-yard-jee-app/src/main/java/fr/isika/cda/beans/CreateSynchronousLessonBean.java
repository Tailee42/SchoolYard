package fr.isika.cda.beans;

import fr.isika.cda.entities.AcademicLevel;
import fr.isika.cda.entities.SubjectEnum;
import fr.isika.cda.entities.lesson.SynchronousLesson;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.repositories.MemberRepository;
import fr.isika.cda.repositories.SynchronousLessonRepository;
import fr.isika.cda.utils.SessionUtils;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean
public class CreateSynchronousLessonBean {

     @Inject
     private SynchronousLessonRepository synchronousLessonRepository;
     @Inject
     private MemberRepository memberRepository;

     private SynchronousLesson synchronousLesson = new SynchronousLesson();


     public String create() {
          Teacher teacher = (Teacher) SessionUtils.getConnectedMember();
          synchronousLesson.setTeacher(teacher);
          synchronousLessonRepository.save(synchronousLesson);
          return "userDashboard?faces-redirect=true";
     }

     public SubjectEnum[] subjects() {
          return SubjectEnum.values();
     }

     public AcademicLevel[] levels() {
          return AcademicLevel.values();
     }

     public SynchronousLesson getSynchronousLesson() {
          return synchronousLesson;
     }

     public void setSynchronousLesson(SynchronousLesson synchronousLesson) {
          this.synchronousLesson = synchronousLesson;
     }
}
