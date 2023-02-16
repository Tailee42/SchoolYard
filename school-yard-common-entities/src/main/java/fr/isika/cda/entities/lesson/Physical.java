package fr.isika.cda.entities.lesson;

import fr.isika.cda.entities.common.Address;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Physical implements AccesDetailsInterface, Serializable {

    private static final long serialVersionUID = 9128350787919081032L;

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Long getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
