package fr.isika.cda.spring.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	@ResponseBody
	public ModelAndView deleteFeature(@RequestParam String id) {
		Long id2 = Long.parseLong(id);
		Feature feature = featureService.findById(id2);
		featureService.deleteFeature(feature);
		return new ModelAndView("redirect:/featuresList");
	}
	
	@GetMapping("/updateFeatureForm")
	@ResponseBody
	public ModelAndView updateFeature(@RequestParam Feature feature) {

		return new ModelAndView("redirect:/featuresList");
	}
	

}
