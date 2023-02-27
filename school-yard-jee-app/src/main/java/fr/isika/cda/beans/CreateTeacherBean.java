package fr.isika.cda.beans;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.common.SchoolTypeEnum;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.entities.teacher.TeacherStatusEnum;
import fr.isika.cda.repositories.TeacherRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class CreateTeacherBean {

	private Teacher teacher = new Teacher();

	@Inject
	private TeacherRepository teacherRepository;

	public String create() {
		teacher.setUser(SessionUtils.getConnectedUser());
		teacher.setSchool(SessionUtils.getCurrentSchool());
		teacher.setStatus(TeacherStatusEnum.InProgress);
		teacherRepository.save(teacher);
		return "userDashboard?faces-redirect=true";
	}

	public Map<String, SchoolTypeEnum> schoolTypes() {
		return SchoolTypeEnum.levels;
	}

	public Map<String, SubjectEnum> subjects() {
		return SubjectEnum.subjects;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}
