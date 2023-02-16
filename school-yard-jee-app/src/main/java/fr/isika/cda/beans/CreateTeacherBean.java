package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.SchoolTypeEnum;
import fr.isika.cda.entities.SubjectEnum;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.repositories.TeacherRepository;

@ManagedBean
public class CreateTeacherBean {

	private Teacher teacher = new Teacher();

	@Inject
	private TeacherRepository teacherRepository;

	public String create() {
		teacherRepository.save(teacher);
		teacher = new Teacher();
		return "userDashboard?faces-redirect=true";
	}

	public SchoolTypeEnum[] schoolTypes() {
		return SchoolTypeEnum.values();
	}

	public SubjectEnum[] subjects() {
		return SubjectEnum.values();
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}
