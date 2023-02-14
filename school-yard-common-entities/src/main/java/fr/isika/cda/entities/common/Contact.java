package fr.isika.cda.entities.common;

import javax.persistence.CascadeType;
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

	@Entity
	public static class Profil implements Serializable {

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
}
