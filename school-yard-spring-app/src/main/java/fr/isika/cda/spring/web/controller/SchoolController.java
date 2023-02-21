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
	@Autowired
	private SubscriptionController subscriptionController;
	
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
			subscriptionController.setModelAttributesForSubscription(model,school.getMembership().getSubscription().getId());
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
	
	@GetMapping("/schoolForm/schoolSubscriptionForm")
	public String modifySchoolSubscription(@RequestParam Long id, Model model) {	
		Optional<School> optional = schoolService.findById(id);
		if(optional.isPresent()) {
			School school = optional.get();
			model.addAttribute(school);
			subscriptionController.setModelAttributesForSubscription(model, school.getMembership().getSubscription().getId());
		}
	
		return "school/schoolSubscriptionForm";
	}
	
	
	@PostMapping("/modifySchoolSubscription")
	public ModelAndView modifySchoolSubscription(@RequestParam Long id, @RequestParam String name, @RequestParam double price, @RequestParam Long duration, @RequestParam List<Long> featuresId) {		
		List<Feature> newFeatures = subscriptionController.getFeaturesListFromIds(featuresId);
		Subscription subscription = subscriptionService.createSubscription(name, price, duration, newFeatures);	
		Optional<School> optional = schoolService.findById(id);
		if(optional.isPresent()) {
			School school = optional.get();
			System.out.println("je suis la sub n° "+subscription.getId());
			System.out.println("je suis l'école n°"+school.getId());
			school.getMembership().setSubscription(subscription);	
			schoolService.updateSchool(school);
			System.out.println("je suis l'ecole n° "+school.getId()+" et j'ai un membership qui l'abonnement n° "+school.getMembership().getSubscription().getId()+" la modification de l'abo concerne le n° "+subscription.getId());
		}
		return new ModelAndView("redirect:/schoolForm/?id="+id);
	}
	
	
}
