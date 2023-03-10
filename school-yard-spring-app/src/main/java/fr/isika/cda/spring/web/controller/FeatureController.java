package fr.isika.cda.spring.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.isika.cda.entities.subscription.Feature;
import fr.isika.cda.entities.subscription.Subscription;
import fr.isika.cda.spring.business.service.FeatureService;
import fr.isika.cda.spring.business.service.SubscriptionService;

@Controller
public class FeatureController {
	
	private static final String REDIRECT_FEATURES_LIST = "redirect:/featuresList";
	@Autowired
	private FeatureService featureService;
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@GetMapping("/featuresList")
	public String featureslist(Model model) {
		List<Feature> features = featureService.findAll();
		model.addAttribute("features", features);
		return "feature/featuresList";
	}

	@GetMapping("/featureForm")
	public String createFeature() {
		return "feature/featureForm";
	}
	
	@PostMapping("/createFeature")
	public ModelAndView createFeature(@RequestParam String title, @RequestParam String description) {
		featureService.createFeature(title, description);
		return new ModelAndView(REDIRECT_FEATURES_LIST);
	}
	
	@GetMapping("/deleteFeature")
	public ModelAndView deleteFeature(@RequestParam Long id) {
		Optional<Feature> optional = featureService.findById(id);
		if (optional.isPresent()) {
			Feature featureToDelete = optional.get();
			List<Subscription> subscriptions = subscriptionService.findAll();
				for (Subscription subscription : subscriptions) {
					subscription.getFeatures().remove(featureToDelete);
					subscriptionService.updateSubscription(subscription);
				}
			featureService.deleteFeature(featureToDelete);
		}
		return new ModelAndView(REDIRECT_FEATURES_LIST);
	}
	
	@GetMapping("/updateFeatureForm")
	public String updateFeature(@RequestParam Long id, Model model) {
		Optional<Feature> optional = featureService.findById(id);
		if (optional.isPresent()) {
			Feature featureToUpdate = optional.get();
			model.addAttribute("feature", featureToUpdate);
		}	
		return "feature/updateFeatureForm";
	}
	
	@PostMapping("/updateFeature")
	public ModelAndView updateFeature(@ModelAttribute("feature") Feature updatedFeature, @RequestParam Long id) {	
		Optional<Feature> optional = featureService.findById(id);
		if (optional.isPresent()) {
			Feature featureToUpdate = optional.get();
			featureToUpdate.setFeatureTitle(updatedFeature.getFeatureTitle());
			featureToUpdate.setFeatureDescription(updatedFeature.getFeatureDescription());
			featureService.updateFeature(featureToUpdate);
		}	
		return new ModelAndView(REDIRECT_FEATURES_LIST);
	}
	
}
