package fr.isika.cda.entities.school;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class SchoolPage implements Serializable {

		private static final long serialVersionUID = 6017573037713086223L; 

	@Id
	@GeneratedValue
	private Long id;
	private String description;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private ValueSchool valueSchool;
	
	@OneToOne(cascade = CascadeType.ALL )
	private Theme theme;
	
	public SchoolPage() {
		
		this.theme = new Theme();
//		this.valueSchool = new ValueSchool();	
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


//	public ValueSchool getValueSchool() {
//		return valueSchool;
//	}
//
//	public void setValueSchool(ValueSchool valueSchool) {
//		this.valueSchool = valueSchool;
//	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
}
