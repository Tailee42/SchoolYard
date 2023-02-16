package fr.isika.cda.spring.business.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

	public void createSubscription(String name, Double price, int duration, Feature feature) {
		Subscription subscription = new Subscription(price, duration, name, feature);
		subscriptionRepository.save(subscription);
	}
}
