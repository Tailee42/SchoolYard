package fr.isika.cda.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.school.School;


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

	public List<School> getByName(String name) {
		return em
				.createQuery("SELECT s FROM School s WHERE s.schoolName = :name_param", School.class)
				.setParameter("name_param", name)
				.getResultList();

	}

}
