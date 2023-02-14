package fr.isika.cda.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.LevelEnum;
import fr.isika.cda.entities.Teacher;
import fr.isika.cda.repositories.TeacherRepository;

@ManagedBean
public class CreateTeacherBean {

	private Teacher teacher;
	private List<LevelEnum> levels;

	@Inject
	private TeacherRepository teacherRepository;

	public String create() {
		teacherRepository.save(teacher);
		teacher = new Teacher();
		return "index?faces-redirect=true";
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<LevelEnum> getLevels() {
		return levels;
	}

	public void setLevels(List<LevelEnum> levels) {
		this.levels = levels;
	}
}
