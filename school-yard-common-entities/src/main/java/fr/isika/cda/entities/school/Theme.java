package fr.isika.cda.entities.school;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Theme {
	
	@Id
	@GeneratedValue
	private Long id;
	private String primaryColor;
	private String accentColor;
	private String backgroundColor;
	private String font;
	
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

}
