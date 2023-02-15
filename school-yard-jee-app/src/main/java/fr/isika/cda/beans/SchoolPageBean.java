package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.school.SchoolPage;
import fr.isika.cda.repositories.SchoolRepository;

@ManagedBean
public class SchoolPageBean {

	
	private SchoolPage schoolPage = new SchoolPage();
	
	@Inject
private SchoolRepository schoolRepository;

	
	public String createSchoolPage() {
		schoolRepository.saveSchoolPage(schoolPage);
		schoolPage = new SchoolPage();
		return "index?faces-redirect=true";
	}
	
	
	
	}
	
