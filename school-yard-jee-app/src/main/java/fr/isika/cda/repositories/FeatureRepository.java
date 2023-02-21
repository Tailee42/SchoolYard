package fr.isika.cda.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.subscription.Feature;

public class FeatureRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Feature feature) {
		entityManager.persist(feature);
	}
	
	public List<Feature> getAll(){
		return entityManager
				.createQuery("SELECT f FROM Feature f", Feature.class)
				.getResultList();
	}
	
	public List<Feature> getFeatureBySubscriptionId(Long subscriptionId){
		return entityManager
				.createQuery("SELECT f FROM Feature f JOIN FETCH f.subscription WHERE s.subscription = :subscriptionId_param" , Feature.class)
				.setParameter("subscriptionId_param", subscriptionId)
				.getResultList();
	}
}
