package fr.isika.cda.spring.business.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.isika.cda.entities.subscription.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

	
	
}
