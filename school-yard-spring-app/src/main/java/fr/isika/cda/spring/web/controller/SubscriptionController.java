package fr.isika.cda.spring.web.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private FeatureService featureService;

	@GetMapping("/subscriptionsList")
	public String SubscriptionsList(Model model) {
		List<Subscription> subscriptions = subscriptionService.findAll();
		model.addAttribute("subscriptions", subscriptions);
		return "subscriptionsList";
	}

	@GetMapping("/subscriptionForm")
	public String createSubscription(Model model) {
		List<Feature> features = featureService.findAll();
		model.addAttribute("features", features);
		return "subscriptionForm";
	}

	@PostMapping("/createSubscription")
	public ModelAndView createSubscription(@RequestParam String name, @RequestParam Double price,
			@RequestParam int duration, @RequestParam Long id) {
		System.out.println(id);
		Feature featureToAdd = featureService.findById(id);
		subscriptionService.createSubscription(name, price, duration, featureToAdd);
		return new ModelAndView("redirect:/subscriptionsList");
	}

}
