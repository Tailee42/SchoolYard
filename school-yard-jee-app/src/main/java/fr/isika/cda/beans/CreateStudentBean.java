package fr.isika.cda.beans;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.repositories.StudentRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class CreateStudentBean {
	@Inject
	private StudentRepository studentRepository;

	private Student student = new Student();

	public String create() {
		student.setUser(SessionUtils.getConnectedUser());
		student.setSchool(SessionUtils.getCurrentSchool());
		studentRepository.save(student);
		return "userDashboard?faces-redirect=true";
	}

	public Map<String, AcademicLevel> levels() {
		return AcademicLevel.Academiclevels;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
