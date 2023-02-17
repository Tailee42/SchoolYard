package fr.isika.cda.spring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.isika.cda.entities.school.School;
import fr.isika.cda.spring.business.service.SchoolService;


@Controller
public class SchoolController {

	@Autowired
	private SchoolService schoolService;
	
	@GetMapping("/schoolsList")
	public String schoolList(Model model) {
		List<School> schools = schoolService.findAll();
		model.addAttribute("schools", schools);
		return "schoolsList";
	}
	
}
