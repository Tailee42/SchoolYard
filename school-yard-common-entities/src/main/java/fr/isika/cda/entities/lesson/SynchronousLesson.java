package fr.isika.cda.entities.lesson;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class SynchronousLesson extends Activity {
    private String title;
    private LocalDateTime duration;
    private LocalDateTime classDate;
    private int maxStudentNumber;
    private Float price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDuration() {
        return duration;
    }

    public void setDuration(LocalDateTime duration) {
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
