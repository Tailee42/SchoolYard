package fr.isika.cda.entities.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Address implements Serializable {

	private static final long serialVersionUID = 3250501824271112440L;

	@Id
	@GeneratedValue
	private Long id;
	
	private Integer number;
	private String street;
	private String town;
	private String zipcode;

	public Address() {
	}

	public Address(Integer number, String street, String town, String zipcode) {
		this.number = number;
		this.street = street;
		this.town = town;
		this.zipcode = zipcode;
	}

	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Long getId() {
		return id;
	}



}
