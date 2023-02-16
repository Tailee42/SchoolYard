package fr.isika.cda.entities.lesson;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class SynchronousLesson extends Activity {
    private String title;
    private LocalDateTime duration;
    private LocalDateTime clasDate;
    private int maxSutdentNumber;
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

    public LocalDateTime getClasDate() {
        return clasDate;
    }

    public void setClasDate(LocalDateTime clasDate) {
        this.clasDate = clasDate;
    }

    public int getMaxSutdentNumber() {
        return maxSutdentNumber;
    }

    public void setMaxSutdentNumber(int maxSutdentNumber) {
        this.maxSutdentNumber = maxSutdentNumber;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
