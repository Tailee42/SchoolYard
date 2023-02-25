package fr.isika.cda.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.common.SchoolTypeEnum;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.school.StatusSchool;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class SchoolsListBean {

	@Inject
	private SchoolRepository schoolRepository;

	private School school = new School();
	private List<School> schools = new ArrayList<>();

	public List<School> allSchools() {
		schools = schoolRepository.getAll();
		return schools;
	}

	public List<School> allActiveSchools() {
		schools = schoolRepository.getAllActiveSchool(StatusSchool.PUBLISHED);
		return schools;
	}

	public List<School> getByName() {
		schools = schoolRepository.getByName(school.getSchoolName());
		return schools;
	}

	public List<School> getActiveSchoolByName() {
		schools = schoolRepository.getActiveSchoolByName(school.getSchoolName(), StatusSchool.PUBLISHED);
		return schools;
	}

	public List<School> getByType() {
		if (school.getSchoolTypeEnum() != null) {
			schools = schoolRepository.getByType(school.getSchoolTypeEnum());
		}
		return schools;
	}

	public List<School> getActiveSchoolByType() {
		schools = schoolRepository.getActiveSchoolByType(school.getSchoolTypeEnum(), StatusSchool.PUBLISHED);
		return schools;
	}

	public void clearSchoolName() {
		school.setSchoolName(null);
	}

	public String navigationIndexSchool(School school) {
		SessionUtils.setCurrentSchool(school);
		return "indexSchool?faces-redirect=true";
	}

	public Map<String, SchoolTypeEnum> types() {
		return SchoolTypeEnum.levels;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public List<School> getSchools() {
		return schools;
	}

	public void setSchools(List<School> schools) {
		this.schools = schools;
	}

}
