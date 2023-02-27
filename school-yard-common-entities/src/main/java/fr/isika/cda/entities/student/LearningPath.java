package fr.isika.cda.entities.student;

import fr.isika.cda.entities.lesson.Activity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class LearningPath implements Serializable {

    private static final long serialVersionUID = 3654120034551383098L;

    @Id
    @GeneratedValue
    private Long id;

    private String assignment;
    private LocalDateTime registrationDate;
    @Lob
    private String sumUp;

    @Enumerated(EnumType.STRING)
    private StatusLearningPath statusLearningPath;

    @OneToOne
    private Activity activity;

    @OneToOne
    private Student student;

    public LearningPath() {
        this.statusLearningPath = StatusLearningPath.ACTIVE;
        this.registrationDate = LocalDateTime.now();
    }

    public LearningPath(Activity activity, Student student, LocalDateTime registrationDate, String sumUp, String assignment) {
        this.assignment = assignment;
        this.registrationDate = registrationDate;
        this.sumUp = sumUp;
        this.statusLearningPath = StatusLearningPath.ACTIVE;
        this.activity = activity;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getSumUp() {
        return sumUp;
    }

    public void setSumUp(String sumUp) {
        this.sumUp = sumUp;
    }

    public StatusLearningPath getStatusLearningPath() {
        return statusLearningPath;
    }

    public void setStatusLearningPath(StatusLearningPath statusLearningPath) {
        this.statusLearningPath = statusLearningPath;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
}
