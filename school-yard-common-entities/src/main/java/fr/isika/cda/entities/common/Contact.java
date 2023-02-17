package fr.isika.cda.entities.common;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Contact implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String email;
	private String phone;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	public Contact() {
		this.address = new Address();
	}

	public Contact(String email, String phone, Address address) {
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Long getId() {
		return id;
	}

}
