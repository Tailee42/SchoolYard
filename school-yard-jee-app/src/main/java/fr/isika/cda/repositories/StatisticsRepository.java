package fr.isika.cda.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.school.Statistics;

@Stateless
public class StatisticsRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(Statistics stats) {
		entityManager.persist(stats);
	}

	public void update(Statistics stats) {
		entityManager.merge(stats);
	}

}