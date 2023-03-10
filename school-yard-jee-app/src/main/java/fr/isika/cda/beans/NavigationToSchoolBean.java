package fr.isika.cda.beans;

import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.repositories.TeacherRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class NavigationToSchoolBean {
	@Inject
	private SchoolRepository schoolRepository;
	@Inject
	private TeacherRepository teacherRepository;

	// TODO Quand le tableau du dashboard sera en place, prendre en compte l'id de
	// l'école
	public String toIndexSchool() {
		Optional<School> school = Optional.ofNullable(schoolRepository.getSchoolById(19L));
		Optional<Teacher> teacher = teacherRepository.getTeacherById(47L);
		if (school.isPresent() && teacher.isPresent()) {
			SessionUtils.setCurrentSchool(school.get());
			SessionUtils.setConnectedMember(teacher.get());
			return "indexSchool?faces-redirect=true";
		}
		return "";
	}
}
