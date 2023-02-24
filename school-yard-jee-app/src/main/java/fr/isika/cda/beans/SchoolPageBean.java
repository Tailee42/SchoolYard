package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.school.FontEnum;
import fr.isika.cda.entities.school.SchoolPage;
import fr.isika.cda.repositories.SchoolPageRepository;
import fr.isika.cda.utils.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ManagedBean
public class SchoolPageBean {

	
	private SchoolPage schoolPage = new SchoolPage();
	
	@Inject
private SchoolPageRepository schoolPageRepository;

	private String pictureFileName;

	
	public String createSchoolPage() {
		schoolPage.getSchoolValue().setPicture(pictureFileName);
		schoolPageRepository.save(schoolPage);
		schoolPage = new SchoolPage();
		return "index?faces-redirect=true";
	}

	public void uploadFile(FileUploadEvent event) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_hhmmss"));
		UploadedFile file = event.getFile();
		pictureFileName = timestamp + "_" + file.getFileName();
		FileUpload.doUpLoad(file, pictureFileName);
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
	
