package fr.isika.cda.entities.school;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Theme implements Serializable {

	
	private static final long serialVersionUID = 3619206993070814589L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String primaryColor;
	private String accentColor;
	private String backgroundColor;
	private String font;

	public Theme() {
		this.primaryColor = "9899AE";
		this.accentColor = "EB9486";
		this.backgroundColor = "F9f8F8";
		this.font = "Quicksand";
	}

	public String getPrimaryColor() {
		return primaryColor;
	}


	public void setPrimaryColor(String primaryColor) {
		this.primaryColor = primaryColor;
	}


	public String getAccentColor() {
		return accentColor;
	}


	public void setAccentColor(String accentColor) {
		this.accentColor = accentColor;
	}


	public String getBackgroundColor() {
		return backgroundColor;
	}


	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}


	public String getFont() {
		return font;
	}


	public void setFont(String font) {
		this.font = font;
	}


	public Long getId() {
		return id;
	}

}
