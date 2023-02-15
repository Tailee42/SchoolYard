package fr.isika.cda.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.school.School;
import fr.isika.cda.repositories.SchoolRepository;

@ManagedBean
public class SchoolsListBean {

	@Inject
	private SchoolRepository schoolRepository;
	
	private School school = new School();
	private List<School> schools = new ArrayList<School>();

	public List<School> allSchools() {
		schools = schoolRepository.getAll();
		return schools;
			}

	public List<School> getByName() {
		schools = schoolRepository.getByName(school.getSchoolName());
		return schools;
	}
	
	public School getSchool() {
		return school;
	}
	
	public void setSchool(School school) {
		this.school = school;
	}
	
}
