package fr.isika.cda.spring.business.service;

import java.util.List;
import java.util.Optional;

import fr.isika.cda.entities.school.Admin;
import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.entities.teacher.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cda.spring.business.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	public List<Member> findAll(){
		return (List<Member>) memberRepository.findAll();
	}

	public List<Member> findByUserId(Long id) {		
		return memberRepository.findByUserId(id);
	}
	
	public List<Student> findStudentsByMemberId(Long id) {		
		return memberRepository.findStudentsByMemberId(id);
	}
	
	public List<Teacher> findTeachersByMemberId(Long id) {		
		return memberRepository.findTeachersByMemberId(id);
	}
	
	public List<Admin> findAdminsByMemberId(Long id) {		
		return memberRepository.findAdminsByMemberId(id);
	}
	
	public void deleteStudent(Student student) {
		memberRepository.delete(student);
	}
	
	public void deleteTeacher(Teacher teacher) {
		memberRepository.delete(teacher);
	}
	
	public void deleteAdmin(Admin admin) {
		memberRepository.delete(admin);
	}

}
