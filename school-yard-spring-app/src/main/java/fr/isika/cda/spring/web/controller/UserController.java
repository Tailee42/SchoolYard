package fr.isika.cda.spring.web.controller;

import java.util.List;
import java.util.Optional;

import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.isika.cda.spring.business.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/usersList")
	public String userslist(Model model) {
		List<User> users = userService.findAllByStatus(UserStatus.ACTIVE);
		model.addAttribute("users", users);
		return "user/usersList";
	}
	
	@GetMapping("/deleteUser")
	public ModelAndView deleteUser(@RequestParam Long id) {
		Optional<User> optional = userService.findById(id);
		
		if(optional.isPresent()) {
			User userToDelete = optional.get();
			userToDelete.setStatus(UserStatus.INACTIVE);
			userService.updateUser(userToDelete);
			}
		return new ModelAndView("redirect:/usersList");
	}
	
}	

