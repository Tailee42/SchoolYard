package fr.isika.cda.spring.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		model.addAttribute("featureId", feature.getId());
		model.addAttribute("featureTitle", feature.getFeatureTitle());
		model.addAttribute("featureDescription", feature.getFeatureDescription());
		return "updateFeatureForm";
	}
	
	@PostMapping("/updateFeature")
	public ModelAndView updateFeature(@PathVariable("id") Long id, @RequestParam String title, @RequestParam String description) {		
		Feature feature = featureService.findById(id);
		featureService.updateFeature(feature, title, description);
		return new ModelAndView("redirect:/featuresList");
	}

}
