package fr.isika.cda.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.school.SchoolPage;

@Stateless
public class SchoolPageRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(SchoolPage schoolPage) {

		entityManager.persist(schoolPage);
	}

}
