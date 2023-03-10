package fr.isika.cda.beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.entities.school.FontEnum;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.repositories.StudentRepository;
import fr.isika.cda.repositories.TeacherRepository;
import fr.isika.cda.repositories.UnitRepository;
import fr.isika.cda.services.AdminService;
import fr.isika.cda.utils.FileUpload;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class AdminBean {

	@Inject
	private TeacherRepository teacherRepository;

	@Inject
	private UnitRepository unitRepository;

	@Inject
	private StudentRepository studentRepository;

	@Inject
	private AdminService adminService;

	@Inject
	private SchoolRepository schoolRepository;

	private School school = SessionUtils.getCurrentSchool();

	// méthodes de redirection
	public String modifySchool() {
		return "modifySchoolForm";
	}

	// méthodes métier
	public void updateSchool() {
		schoolRepository.update(school);
		modifySchool();
	}

	public String validateTeacher(Long teacherId) {
		Teacher teacher = getCurrentTeacher(teacherId);
		adminService.validateTeacher(teacher);
		return "adminDashboard";
	}

	public String rejectTeacher(Long teacherId) {
		Teacher teacher = getCurrentTeacher(teacherId);
		adminService.rejectTeacher(teacher);
		return "adminDashboard";
	}

	public String deleteTeacher(Long teacherId) {
		Teacher teacher = getCurrentTeacher(teacherId);
		adminService.deleteTeacher(teacher);
		return "schoolTeachersList";
	}

	public String validateUnit(Long unitId) {
		Unit unit = getCurrentUnit(unitId);
		adminService.validateUnit(unit);
		return "adminDashboard";
	}

	public String rejectUnit(Long unitId) {
		Unit unit = getCurrentUnit(unitId);
		adminService.rejectUnit(unit);
		return "adminDashboard?faces-redirect=true";
	}

	public String deleteStudent(Long studentId) {
		Student student = getCurrentStudent(studentId);
		adminService.deleteStudent(student);
		return "schoolStudentsList";
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	// méthode d'affichage
	public List<Teacher> allSchoolTeachers() {
		return teacherRepository.currentSchoolTeachers(getCurrentSchoolId());
	}

	public List<Unit> unitsToUpdate() {
		if (!allSchoolTeachers().isEmpty()) {
			return unitRepository.currentSchoolUnits(teachersIds(allSchoolTeachers()));
		} else
			return Collections.emptyList();
	}

	public List<Teacher> allSchoolCandidates() {
		return teacherRepository.allSchoolCandidates(getCurrentSchoolId());
	}

	public List<Student> allSchoolStudents() {
		return studentRepository.currentSchoolStudents(getCurrentSchoolId());
	}

	public String initSchoolStats() {
		adminService.initStats((allSchoolStudents().size()), (allSchoolTeachers().size()), school);
		return "adminDashboard?faces-redirect=true";
	}

	public void uploadFile(FileUploadEvent event) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_hhmmss"));
		UploadedFile file = event.getFile();
		String pictureFileName = timestamp + "_" + file.getFileName();
		FileUpload.doUpLoad(file, pictureFileName);

		school.getSchoolPage().getSchoolValue().setPicture(pictureFileName);
		schoolRepository.update(school);
	}

	public Map<String, FontEnum> fontEnum() {
		return FontEnum.fonts;
	}

	// méthode internes
	private Unit getCurrentUnit(Long unitId) {
		return unitRepository.getUnitById(unitId);
	}

	private Teacher getCurrentTeacher(Long teacherId) {
		return teacherRepository.getTeacherById(teacherId).get();
	}

	private Student getCurrentStudent(Long studentId) {
		return studentRepository.getStudentById(studentId).get();
	}

	private Long getCurrentSchoolId() {
		return SessionUtils.getConnectedMember().getSchool().getId();
	}

	private List<Long> teachersIds(List<Teacher> teachers) {
		return teachers.parallelStream().map(Teacher::getId).collect(Collectors.toList());
	}

}
