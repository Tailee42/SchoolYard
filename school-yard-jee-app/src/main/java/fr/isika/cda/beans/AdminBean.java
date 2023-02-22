package fr.isika.cda.beans;

import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.repositories.MemberRepository;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.repositories.TeacherRepository;
import fr.isika.cda.repositories.UnitRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class AdminBean {

	@Inject
	private TeacherRepository teacherRepository;

	@Inject
	private UnitRepository unitRepository;

	public String validateTeacher(Long teacherId) {
		Teacher teacher = getCurrentTeacher(teacherId);
		teacher.validate();

		teacherRepository.update(teacher);
		return "adminDashboard";
	}

	public String rejectTeacher(Long teacherId) {
		Teacher teacher = getCurrentTeacher(teacherId);
		teacher.reject();

		teacherRepository.update(teacher);
		return "adminDashboard";
	}
	
	public String deleteTeacher(Long teacherId) {
		Teacher teacher = getCurrentTeacher(teacherId);
		teacher.eraseSchool();

		teacherRepository.update(teacher);
		return "adminDashboard";
	}
	
	public String validateUnit(Long unitId) {
		Unit unit = getCurrentUnit(unitId);
		unit.validate();
		unitRepository.save(unit);
		return "adminDashboard?faces-redirect=true";
	}
	
	public String rejectUnit(Long unitId) {
		Unit unit = getCurrentUnit(unitId);
		unit.reject();
		unitRepository.save(unit);
		return "adminDashboard?faces-redirect=true";
	}

	public List<Teacher> teachers() {
		return teacherRepository.currentSchoolTeachers(getCurrentSchoolId());
	}

	public List<Unit> unitsToUpdate() {
		return unitRepository.currentSchoolUnits(teachersIds(teachers()));
	}

	private Unit getCurrentUnit(Long unitId) {
		return unitRepository.getUnitById(unitId);
	}
	private Teacher getCurrentTeacher(Long teacherId) {
		return teacherRepository.getTeacherById(teacherId).get();
	}

	private Long getCurrentSchoolId() {
		return SessionUtils.getConnectedMember().getSchool().getId();
	}

	private List<Long> teachersIds(List<Teacher> teachers) {
		return teachers.parallelStream().map(Teacher::getId).collect(Collectors.toList());
	}

}
