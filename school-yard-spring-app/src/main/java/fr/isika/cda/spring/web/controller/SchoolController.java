package fr.isika.cda.spring.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.school.StatusSchool;
import fr.isika.cda.entities.subscription.Feature;
import fr.isika.cda.entities.subscription.Subscription;
import fr.isika.cda.spring.business.service.FeatureService;
import fr.isika.cda.spring.business.service.SchoolService;
import fr.isika.cda.spring.business.service.SubscriptionService;


@Controller
public class SchoolController {

	@Autowired
	private SchoolService schoolService;
	@Autowired
	private FeatureService featureService;
	@Autowired
	private SubscriptionService subscriptionService;
	
	@GetMapping("/schoolsList")
	public String schoolList(Model model) {
		List<School> schools = schoolService.findAll();
		model.addAttribute("schools", schools);
		return "school/schoolsList";
	}
	
	@GetMapping("/schoolForm")
	public String displaySchool(@RequestParam Long id, Model model) {	
		Optional<School> optional = schoolService.findById(id);
		if(optional.isPresent()) {
			School school = optional.get();
			Subscription subscription = subscriptionService.findById(school.getMembership().getSubscription().getId());
			List<Feature> features = featureService.findAll();
			model.addAttribute("subscription", subscription);
			model.addAttribute("features", features);
			model.addAttribute("school", school);
		}
		return "school/schoolForm";
	}
	
	@PostMapping("/modifySchoolStatus")
	public ModelAndView modifySchoolStatus(@RequestParam Long id, @RequestParam StatusSchool statut) {		
		Optional<School> optional = schoolService.findById(id);
		if(optional.isPresent()) {
			School school = optional.get();
			school.setStatusSchool(statut);	
			schoolService.updateSchool(school);
		}
		return new ModelAndView("redirect:/schoolsList");
	}
	
	
}
