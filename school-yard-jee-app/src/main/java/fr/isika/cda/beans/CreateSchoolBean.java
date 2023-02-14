package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.servlet.http.Part;

import fr.isika.cda.entities.school.School;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.utils.FileUtils;

import java.util.UUID;

@ManagedBean
public class CreateSchoolBean {

	private School school = new School();

	private Part logoFile;

	@Inject
	private SchoolRepository schoolRepository;

	public String create() {
		FileUtils.initResourcesDir();


		String uuid = UUID.randomUUID().toString();
		String filename = uuid + ".png";
		String logoRelativePath = "/school-yard-resources/images/" + filename;

		// TODO : ecrire le fichier sur le disque

		school.setLogo(logoRelativePath);
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

	public Part getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(Part logoFile) {
		this.logoFile = logoFile;
	}
}
