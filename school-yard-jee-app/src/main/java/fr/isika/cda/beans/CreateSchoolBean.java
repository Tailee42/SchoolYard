package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;

import fr.isika.cda.entities.common.SchoolTypeEnum;
import fr.isika.cda.entities.school.Admin;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.MemberRepository;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.utils.FileUpload;
import fr.isika.cda.utils.FileUtils;
import fr.isika.cda.utils.SessionUtils;
import org.primefaces.event.FileUploadEvent;

import java.util.UUID;

@ManagedBean
@SessionScoped
public class CreateSchoolBean {

	private School school = new School();

	private String logoFileName;

	@Inject
	private SchoolRepository schoolRepository;

	@Inject
	private MemberRepository memberRepository;

	public String create() {
		school.setLogo(logoFileName);
		schoolRepository.save(school);

		Admin admin = createAdmin();
		memberRepository.save(admin);

		school = new School();
		return "index?faces-redirect=true";

	}

	public void uploadFile(FileUploadEvent event) {
		String absoluteFilePath = FileUtils.getResourceImageFilePath(event.getFile().getFileName());

		// Mémorise le nom du fichier => à reconstituer lors de la lecture
		logoFileName =  event.getFile().getFileName();
		FileUpload.doUpLoad(event.getFile(), absoluteFilePath);
	}


	private Admin createAdmin() {
		Admin admin = new Admin();
		User user = SessionUtils.getConnectedUser();
		admin.setSchool(school);
		admin.setUser(user);
		return admin;
	}

	public SchoolTypeEnum[] levels() {
		return SchoolTypeEnum.values();
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getLogoFile() {
		return logoFileName;
	}

	public void setLogoFile(String logoFile) {
		this.logoFileName = logoFile;
	}
}
