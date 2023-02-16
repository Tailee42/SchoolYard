package fr.isika.cda.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.subscription.Feature;

public class FeatureRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Feature feature) {
		entityManager.persist(feature);
	}
}
