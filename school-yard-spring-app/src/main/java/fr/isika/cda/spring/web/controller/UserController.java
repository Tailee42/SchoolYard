package fr.isika.cda.spring.web.controller;

import java.util.List;
import java.util.Optional;

import fr.isika.cda.entities.school.Admin;
import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.isika.cda.spring.business.service.MemberService;
import fr.isika.cda.spring.business.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/usersList")
	public String userslist(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "user/usersList";
	}
	
	@GetMapping("/deleteUser")
	public ModelAndView deleteUser(@RequestParam Long id) {
		Optional<User> optional = userService.findById(id);
		
		if(optional.isPresent()) {
			User userToDelete = optional.get();
			deleteUsersMembers(id);
			userService.deleteUser(userToDelete);
			}
		return new ModelAndView("redirect:/usersList");
	}
	
	public void deleteUsersMembers(Long id) {
		List<Member> members = memberService.findByUserId(id);
		for (Member memberToDelete : members) {
			deleteStudent(memberToDelete);
//			deleteTeacher(memberToDelete);
			deleteAdmin(memberToDelete);
		}
	}

	private void deleteStudent(Member memberToDelete) {
		List<Student> students = memberService.findStudentsByMemberId(memberToDelete.getId());
		if (!students.isEmpty()) {
			for(Student studentToDelete : students) {
				memberService.deleteStudent(studentToDelete);
			}
		}
	}
	
//	private void deleteTeacher(Member memberToDelete) {
//		List<Teacher> teachers = memberService.findTeachersByMemberId(memberToDelete.getId());
//		if (!teachers.isEmpty()) {
//			for(Teacher teacherToDelete : teachers) {
//				memberService.deleteTeacher(teacherToDelete);
//			}
//		}
//	}
	
	private void deleteAdmin(Member memberToDelete) {
		List<Admin> admins = memberService.findAdminsByMemberId(memberToDelete.getId());
		if (!admins.isEmpty()) {
			for(Admin adminToDelete : admins) {
				memberService.deleteAdmin(adminToDelete);
			}
		}
	}
	
}	

