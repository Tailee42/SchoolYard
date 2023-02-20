package fr.isika.cda.spring.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.isika.cda.entities.subscription.Feature;
import fr.isika.cda.entities.subscription.Subscription;
import fr.isika.cda.spring.business.service.FeatureService;
import fr.isika.cda.spring.business.service.SubscriptionService;

@Controller
public class SubscriptionController {

	private static final String REDIRECT_SUBSCRIPTION_SUBSCRIPTIONS_LIST = "redirect:/subscriptionsList";

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private FeatureService featureService;

	@GetMapping("/subscriptionsList")
	public String subscriptionsList(Model model) {
		List<Subscription> subscriptions = subscriptionService.findAll();
		model.addAttribute("subscriptions", subscriptions);
		return "subscription/subscriptionsList";
	}

	@GetMapping("/subscriptionForm")
	public String createSubscription(Model model) {
		List<Feature> features = featureService.findAll();
		model.addAttribute("features", features);
		return "subscription/subscriptionForm";
	}

	@PostMapping("/createSubscription")
	public ModelAndView createSubscription(@RequestParam String name, @RequestParam Double price,
			@RequestParam int duration, @RequestParam List<Long> id) {
		List<Feature> featuresSelected = getFeaturesListFromIds(id);
		subscriptionService.createSubscription(name, price, duration, featuresSelected);
		return new ModelAndView(REDIRECT_SUBSCRIPTION_SUBSCRIPTIONS_LIST);
	}

	@GetMapping("/deleteSubscription")
	public ModelAndView deleteSubscription(@RequestParam Long id) {
		Optional<Subscription> optional = subscriptionService.findById(id);
		if(optional.isPresent()) {
		Subscription subscriptionToDelete = optional.get();
		subscriptionService.deleteSubscription(subscriptionToDelete);
		}
		return new ModelAndView(REDIRECT_SUBSCRIPTION_SUBSCRIPTIONS_LIST);
	}

	@GetMapping("/updateSubscription")
	public String updateSubscription(Model model, @RequestParam Long id) {
		setModelAttributesForSubscription(model, id);
		return "subscription/updateSubscription";
	}
	
	@PostMapping("/updateSubscription")
	public ModelAndView updateSubscription(@RequestParam Long id, @RequestParam String subscriptionName, @RequestParam double subscriptionPrice, @RequestParam int subscriptionDuration, @RequestParam List<Long> featureId) {	
		List<Feature> featuresSelected = getFeaturesListFromIds(featureId);
		setSubscriptionNewAttributes(id, subscriptionName, subscriptionPrice, subscriptionDuration, featuresSelected);
		return new ModelAndView(REDIRECT_SUBSCRIPTION_SUBSCRIPTIONS_LIST);
	}
	
	public void setSubscriptionNewAttributes(Long id, String subscriptionName, double subscriptionPrice, int subscriptionDuration, List<Feature> featuresSelected) {
		Optional<Subscription> optional = subscriptionService.findById(id);
		if(optional.isPresent()) {
		Subscription subscriptionUpdated = optional.get();
		subscriptionUpdated.setName(subscriptionName);
		subscriptionUpdated.setPrice(subscriptionPrice);
		subscriptionUpdated.setDuration(subscriptionDuration);
		subscriptionUpdated.setFeatures(featuresSelected);
		subscriptionService.updateSubscription(subscriptionUpdated);
		}
	}
	
	//methods below are used in school controller
	
	public void setModelAttributesForSubscription(Model model, Long id) {
		Optional<Subscription> optional = subscriptionService.findById(id);
		if(optional.isPresent()) {
			Subscription subscription = optional.get();
			List<Feature> features = featureService.findAll();
			model.addAttribute("subscription", subscription);
			model.addAttribute("features", features);
		}
	}
	
	public List<Feature> getFeaturesListFromIds(List<Long> id) {
		List<Feature> featuresSelected = new ArrayList<>();
		for(Long idSelected : id) {
			Feature featureToAdd = featureService.findById(idSelected);
			featuresSelected.add(featureToAdd);
		}
		return featuresSelected;
	}
	
}
