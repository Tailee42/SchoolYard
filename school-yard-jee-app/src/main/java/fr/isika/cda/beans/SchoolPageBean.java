package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.school.FontEnum;
import fr.isika.cda.entities.school.SchoolPage;
import fr.isika.cda.repositories.SchoolPageRepository;

@ManagedBean
public class SchoolPageBean {

	
	private SchoolPage schoolPage = new SchoolPage();
	
	@Inject
private SchoolPageRepository schoolPageRepository;

	
	public String createSchoolPage() {
		schoolPageRepository.save(schoolPage);
		schoolPage = new SchoolPage();
		return "index?faces-redirect=true";
	}


	public SchoolPage getSchoolPage() {
		return schoolPage;
	}


	public void setSchoolPage(SchoolPage schoolPage) {
		this.schoolPage = schoolPage;
	}
		
	public FontEnum[] fontEnum() {
		return FontEnum.values();
	}
	
	}
	
