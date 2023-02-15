package fr.isika.cda.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.common.Contact;


@Stateless
public class SchoolRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void save(School school) {
		em.persist(school);
	}
	
	public List<School> getAll() {
		return em
				.createQuery("SELECT s FROM School s JOIN FETCH s.contact", School.class)
				.getResultList();

	}

}
