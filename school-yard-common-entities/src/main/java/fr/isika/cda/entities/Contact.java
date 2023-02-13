package fr.isika.cda.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Contact {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String email;
	private String phone;
	
	@OneToOne
	private Address address;

}
