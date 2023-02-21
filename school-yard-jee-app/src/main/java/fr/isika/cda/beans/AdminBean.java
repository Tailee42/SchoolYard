package fr.isika.cda.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.entities.teacher.TeacherStatusEnum;
import fr.isika.cda.repositories.MemberRepository;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.repositories.TeacherRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class AdminBean {

	@Inject
	private MemberRepository memberRepository;

	@Inject
	private SchoolRepository schoolRepository;

	@Inject
	private TeacherRepository teacherRepository;

	public List<Member> members() {
		return memberRepository.getAllSchoolsMembers(getThisSchoolId());
	}

	public String validateTeacher(Long teacherId) {
		Teacher teacher = getThisTeacher(teacherId);
		teacher.setStatus(TeacherStatusEnum.Approved);
		teacherRepository.update(teacher);
		return "adminDashboard";
	}

	public String rejectTeacher(Long teacherId) {
		Teacher teacher = getThisTeacher(teacherId);
		teacher.setStatus(TeacherStatusEnum.Rejected);
		teacherRepository.update(teacher);
		return "adminDashboard";
	}

	private Teacher getThisTeacher(Long teacherId) {
		return teacherRepository.getTeacherById(teacherId).get();
	}

	public List<Teacher> teachers() {
		return teacherRepository.thisSchoolTeachers(getThisSchoolId());
	}

	public Long getThisSchoolId() {
		return SessionUtils.getConnectedMember().getSchool().getId();
	}

	public School getCurrentSchool() {
		return schoolRepository.getSchoolById(getThisSchoolId());
	}
}
