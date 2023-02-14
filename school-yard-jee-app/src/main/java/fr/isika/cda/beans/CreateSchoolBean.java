package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.repositories.UserRepository;

@ManagedBean
public class CreateSchoolBean {

	private School school = new School();

	@Inject
	private SchoolRepository schoolRepository;

	public String create() {
		schoolRepository.save(school);
		school = new School();
		return "index?faces-redirect=true";
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
	
}
