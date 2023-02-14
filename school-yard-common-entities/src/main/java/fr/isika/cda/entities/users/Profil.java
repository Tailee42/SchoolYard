package fr.isika.cda.entities.users;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import fr.isika.cda.entities.common.Contact;

@Entity
public class Profil implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3796628224528778418L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String lastName;
	
	private String firstName;
	
	private String picturePath;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Contact contact;

	public Profil() {
		this.contact = new Contact();
	}
	
	public Long getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	

}
