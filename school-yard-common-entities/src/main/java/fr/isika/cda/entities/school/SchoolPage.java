package fr.isika.cda.entities.school;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class SchoolPage implements Serializable {

		private static final long serialVersionUID = 6017573037713086223L; 

	@Id
	@GeneratedValue
	private Long id;
	@Lob
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL)
	private SchoolValue schoolValue;
	
	@OneToOne(cascade = CascadeType.ALL )
	private Theme theme;
	
	public SchoolPage() {
		
		this.theme = new Theme();
		this.schoolValue = new SchoolValue();	
	}
	
	public SchoolPage(String description, SchoolValue schoolValue, Theme theme) {
		super();
		this.description = description;
		this.schoolValue = schoolValue;
		this.theme = theme;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public SchoolValue getSchoolValue() {
		return schoolValue;
}

	public void setSchoolValue(SchoolValue schoolValue) {
	this.schoolValue = schoolValue;	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
}
