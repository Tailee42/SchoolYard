package fr.isika.cda.entities.lesson;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Virtual implements AccesDetailsInterface, Serializable {

    private static final long serialVersionUID = 4665328226968153758L;

    @Id
    @GeneratedValue
    private Long id;

    private String url;

    private String platforme;


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

    public String getPlatforme() {
        return platforme;
    }

    public void setPlatforme(String platforme) {
        this.platforme = platforme;
    }
}
