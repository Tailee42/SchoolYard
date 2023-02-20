package fr.isika.cda.spring.web.controller;

import java.util.List;

import fr.isika.cda.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import fr.isika.cda.spring.business.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/usersList")
	public String userslist(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "user/usersList";
	}
	
	
}
