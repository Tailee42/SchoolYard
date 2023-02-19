package fr.isika.cda.entities.lesson;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class VirtualOption implements AccesDetailsInterface, Serializable {

    private static final long serialVersionUID = 3300675476295267282L;

    @Id
    @GeneratedValue
    private Long id;

    private String url;

    private String plateforme;

    @OneToOne(cascade = CascadeType.ALL)
    private SynchronousLesson synchronousLesson;

    public VirtualOption() {
        this.synchronousLesson = new SynchronousLesson();
    }

    public VirtualOption(String url, String plateforme, SynchronousLesson synchronousLesson) {
        this.url = url;
        this.plateforme = plateforme;
        this.synchronousLesson = synchronousLesson;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPlateforme() {
        return plateforme;
    }

    public void setPlateforme(String plateforme) {
        this.plateforme = plateforme;
    }

    public SynchronousLesson getSynchronousLesson() {
        return synchronousLesson;
    }

    public void setSynchronousLesson(SynchronousLesson synchronousLesson) {
        this.synchronousLesson = synchronousLesson;
    }
}
