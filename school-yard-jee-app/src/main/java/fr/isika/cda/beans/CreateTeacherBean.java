package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.LevelEnum;
import fr.isika.cda.entities.SubjectEnum;
import fr.isika.cda.entities.Teacher;
import fr.isika.cda.repositories.TeacherRepository;

@ManagedBean
public class CreateTeacherBean {

	private Teacher teacher = new Teacher();

	@Inject
	private TeacherRepository teacherRepository;

	public String create() {
		teacherRepository.save(teacher);
		teacher = new Teacher();
		return "teacherDashboard?faces-redirect=true";
	}

	public LevelEnum[] levels() {
		return LevelEnum.values();
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
