package fr.isika.cda.entities.school;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class SchoolValue implements Serializable {
	
	private static final long serialVersionUID = -2330423842854572421L; 
	
	@Id
	@GeneratedValue
	private Long id;
	private String titleValue;
	@Lob
	private String descriptionValue;
	private String picture;

	
	public SchoolValue() {
		this.picture = "empty_school_picture.png";
	}
	
	public SchoolValue(String titleValue, String descriptionValue, String picture) {
		super();
		this.titleValue = titleValue;
		this.descriptionValue = descriptionValue;
		this.picture = picture;
	}

	public String getTitleValue() {
		return titleValue;
	}

	public void setTitleValue(String titleValue) {
		this.titleValue = titleValue;
	}

	public String getDescriptionValue() {
		return descriptionValue;
	}

	public void setDescriptionValue(String descriptionValue) {
		this.descriptionValue = descriptionValue;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Long getId() {
		return id;
	}
}
