package fr.isika.cda.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.school.School;
import fr.isika.cda.repositories.SchoolRepository;

@ManagedBean
public class SchoolsListBean {

	@Inject
	private SchoolRepository schoolRepository;

	public List<School> allSchools() {
		return schoolRepository.getAll();
	}

}
