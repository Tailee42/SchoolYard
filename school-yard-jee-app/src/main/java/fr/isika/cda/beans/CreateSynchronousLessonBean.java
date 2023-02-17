package fr.isika.cda.beans;

import fr.isika.cda.entities.AcademicLevel;
import fr.isika.cda.entities.SubjectEnum;
import fr.isika.cda.entities.lesson.SynchronousLesson;
import fr.isika.cda.repositories.MemberRepository;
import fr.isika.cda.repositories.SynchronousLessonRepository;

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
