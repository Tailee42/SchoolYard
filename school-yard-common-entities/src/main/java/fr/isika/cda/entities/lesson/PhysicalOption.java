package fr.isika.cda.entities.lesson;

import fr.isika.cda.entities.common.Address;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PhysicalOption implements AccesDetailsInterface, Serializable {

    private static final long serialVersionUID = 9128350787919081032L;

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private SynchronousLesson synchronousLesson;

    public PhysicalOption(Address address, SynchronousLesson synchronousLesson) {
        this.address = address;
        this.synchronousLesson = synchronousLesson;
    }

    public PhysicalOption() {
        this.address = new Address();
        this.synchronousLesson = new SynchronousLesson();
    }

    public Long getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public SynchronousLesson getSynchronousLesson() {
        return synchronousLesson;
    }

    public void setSynchronousLesson(SynchronousLesson synchronousLesson) {
        this.synchronousLesson = synchronousLesson;
    }
}
