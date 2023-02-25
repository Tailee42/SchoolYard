package fr.isika.cda.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import fr.isika.cda.entities.school.FontEnum;
import fr.isika.cda.entities.school.SchoolPage;
import fr.isika.cda.repositories.SchoolPageRepository;
import fr.isika.cda.utils.FileUpload;

@ManagedBean
@SessionScoped
public class SchoolPageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6466155197743420541L;

	@Inject
	private SchoolPageRepository schoolPageRepository;

	private String pictureFileName;

	private SchoolPage schoolPage = new SchoolPage();

	public String createSchoolPage() {
		schoolPage.getSchoolValue().setPicture(pictureFileName);
		schoolPageRepository.save(schoolPage);
		resetAll();
		return "index?faces-redirect=true";
	}

	public void uploadFile(FileUploadEvent event) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_hhmmss"));
		UploadedFile file = event.getFile();
		pictureFileName = timestamp + "_" + file.getFileName();
		FileUpload.doUpLoad(file, pictureFileName);
	}

	private void resetAll() {
		schoolPage = new SchoolPage();
		pictureFileName = "empty_school_picture.png";
	}

	public SchoolPage getSchoolPage() {
		return schoolPage;
	}

	public void setSchoolPage(SchoolPage schoolPage) {
		this.schoolPage = schoolPage;
	}

	public Map<String, FontEnum> fontEnum() {
		return FontEnum.fonts;
	}

}
