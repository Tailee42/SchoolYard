package fr.isika.cda.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.isika.cda.spring.business.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public ModelAndView login(@RequestParam String login, @RequestParam String password) {
		if (loginService.isSecured(login, password)) {
			return new ModelAndView("redirect:/superAdminDashboard");
		}
		return new ModelAndView("redirect:/login");
	}

}
