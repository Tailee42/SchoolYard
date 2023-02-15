package fr.isika.cda.entities.school;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class SchoolPage {
	
	@Id
	@GeneratedValue
	private Long id;
	private String description;
	
	@OneToOne
	private School school;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ValueSchool valueSchool;
	
	@ManyToOne(cascade = CascadeType.REMOVE )
	private Theme theme;
	
	public SchoolPage() {
		this.school = new School();
		this.theme = new Theme();
		this.valueSchool = new ValueSchool();	
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public ValueSchool getValueSchool() {
		return valueSchool;
	}

	public void setValueSchool(ValueSchool valueSchool) {
		this.valueSchool = valueSchool;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
}
