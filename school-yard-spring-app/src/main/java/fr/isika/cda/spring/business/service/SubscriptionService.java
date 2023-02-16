package fr.isika.cda.spring.business.service;

import java.math.BigDecimal;
import java.util.Date;
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

	public void createSubscription(String name, Double price, int duration, List<Feature> featureSelected) {
		Subscription subscription = new Subscription(price, duration, name);
		subscription.getFeatures().addAll(featureSelected);
		subscriptionRepository.save(subscription);
	}

	public Subscription findById(Long id) {
		Optional<Subscription> subscription = subscriptionRepository.findById(id);
		if (subscription.isPresent()) {
			return subscription.get();
		} else {
			return null;
		}
	}

	public void deleteSubscription(Subscription subscriptionToDelete) {
		subscriptionRepository.delete(subscriptionToDelete);
	}
}
