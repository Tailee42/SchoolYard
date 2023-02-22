package fr.isika.cda.beans;

import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
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
public class AdminBean {

	@Inject
	private MemberRepository memberRepository;

	@Inject
	private SchoolRepository schoolRepository;

	@Inject
	private TeacherRepository teacherRepository;

	@Inject
	private UnitRepository unitRepository;

	public List<Member> members() {
		return memberRepository.getAllSchoolsMembers(getThisSchoolId());
	}

	public String validateTeacher(Long teacherId) {
		Teacher teacher = getThisTeacher(teacherId);
		teacher.validate();

		teacherRepository.update(teacher);
		return "adminDashboard";
	}

	public String rejectTeacher(Long teacherId) {
		Teacher teacher = getThisTeacher(teacherId);
		teacher.reject();

		teacherRepository.update(teacher);
		return "adminDashboard";
	}

	public List<Unit> newSchoolUnits() {
		return unitRepository.thisSchoolUnits(teachersIds(teachers()));
	}

	public School getCurrentSchool() {
		return schoolRepository.getSchoolById(getThisSchoolId());
	}

	private Teacher getThisTeacher(Long teacherId) {
		return teacherRepository.getTeacherById(teacherId).get();
	}

	public List<Teacher> teachers() {
		return teacherRepository.thisSchoolTeachers(getThisSchoolId());
	}

	private Long getThisSchoolId() {
		return SessionUtils.getConnectedMember().getSchool().getId();
	}

	private List<Long> teachersIds(List<Teacher> teachers) {
		return teachers.parallelStream().map(Teacher::getId).collect(Collectors.toList());
	}

}
