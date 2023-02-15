package fr.isika.cda.spring.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.isika.cda.entities.Feature;
import fr.isika.cda.spring.business.service.FeatureService;

@Controller
public class FeatureController {
	
	@Autowired
	private FeatureService featureService;
	
	@GetMapping("/featuresList")
	public String listUsers(Model model) {
		List<Feature> features = featureService.findAll();
		model.addAttribute("features", features);
		return "featuresList";
	}

	@GetMapping("/featureForm")
	public String createFeature() {
		return "featureForm";
	}
	
	@PostMapping("/createFeature")
	public ModelAndView createFeature(@RequestParam String title, @RequestParam String description) {
		featureService.createFeature(title, description);
		return new ModelAndView("redirect:/featuresList");
	}
	
	@GetMapping("/deleteFeature")
	public ModelAndView deleteFeature(@RequestParam Long id) {
		Feature feature = featureService.findById(id);
		featureService.deleteFeature(feature);
		return new ModelAndView("redirect:/featuresList");
	}
	
	@GetMapping("/updateFeatureForm")
	public String updateFeature(@RequestParam Long id, Model model) {
		Feature feature = featureService.findById(id);
		model.addAttribute("feature", feature);
		return "updateFeatureForm";
	}
	
	@PostMapping("/updateFeature")
	public ModelAndView updateFeature(@ModelAttribute("feature") Feature updatedFeature, @RequestParam Long id) {	
		System.out.println("feature = "+updatedFeature.getId()+" "+updatedFeature.getFeatureDescription()+" "+updatedFeature.getFeatureTitle());
		Feature feature = featureService.findById(id);
		feature.setFeatureTitle(updatedFeature.getFeatureTitle());
		feature.setFeatureDescription(updatedFeature.getFeatureDescription());
		featureService.updateFeature(feature);
		return new ModelAndView("redirect:/featuresList");
	}


}
