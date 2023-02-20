package fr.isika.cda.spring.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cda.entities.subscription.Feature;
import fr.isika.cda.entities.subscription.Subscription;
import fr.isika.cda.spring.business.repository.SubscriptionRepository;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	public List<Subscription> findAll() {
		return (List<Subscription>) subscriptionRepository.findAll();
	}

	public Subscription createSubscription(String name, Double price, int duration, List<Feature> featureSelected) {
		Subscription subscription = new Subscription(price, duration, name);
		subscription.getFeatures().addAll(featureSelected);
		subscriptionRepository.save(subscription);
		return subscription;
	}

	public Optional<Subscription> findById(Long id) {
		return subscriptionRepository.findById(id);
	}
	

	public void deleteSubscription(Subscription subscriptionToDelete) {
		subscriptionRepository.delete(subscriptionToDelete);
	}
	
	public void updateSubscription(Subscription subscription) {
		subscriptionRepository.save(subscription);
	}
	
}
