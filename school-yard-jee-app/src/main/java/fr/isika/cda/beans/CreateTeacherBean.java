package fr.isika.cda.beans;

import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.SchoolTypeEnum;
import fr.isika.cda.entities.SubjectEnum;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.entities.teacher.TeacherStatusEnum;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.repositories.TeacherRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class CreateTeacherBean {

	private Teacher teacher = new Teacher();

	private Long schoolId;

	@Inject
	private SchoolRepository schoolRepository;

	@Inject
	private TeacherRepository teacherRepository;

	public String create() {
		Optional<School> school = schoolRepository.getSchoolById(schoolId);
		if (school.isPresent()) {
			User user = SessionUtils.getConnectedUser();
			teacher.setUser(user);
			teacher.setSchool(school.get());
			teacher.setStatus(TeacherStatusEnum.InProgress);
			teacherRepository.save(teacher);
			return "userDashboard?faces-redirect=true";
		}
		return "index?faces-redirect=true";
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

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Long getSchoolId() {
		return schoolId;
	}

}
