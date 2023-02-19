package fr.isika.cda.entities.lesson;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.teacher.Teacher;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class SynchronousLesson extends Activity {
    private String title;
    private String duration;
    private LocalDateTime classDate;
    private int maxStudentNumber;
    private BigDecimal price;


    public SynchronousLesson(SubjectEnum subject,
                             AcademicLevel level,
                             Teacher teacher,
                             String title,
                             String duration,
                             LocalDateTime classDate,
                             int maxStudentNumber,
                             BigDecimal price) {
        super(subject, level, teacher);
        this.title = title;
        this.duration = duration;
        this.classDate = classDate;
        this.maxStudentNumber = maxStudentNumber;
        this.price = price;
    }

    public SynchronousLesson() {
        this.maxStudentNumber = 1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LocalDateTime getClassDate() {
        return classDate;
    }

    public void setClassDate(LocalDateTime classDate) {
        this.classDate = classDate;
    }

    public int getMaxStudentNumber() {
        return maxStudentNumber;
    }

    public void setMaxStudentNumber(int maxStudentNumber) {
        this.maxStudentNumber = maxStudentNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
