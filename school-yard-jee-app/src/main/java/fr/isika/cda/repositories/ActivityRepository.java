package fr.isika.cda.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.lesson.Activity;

@Stateless
public class ActivityRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public int allSchoolActivities(Long schoolId) {
		return entityManager
				.createQuery("SELECT a FROM Activity a WHERE a.teacher.school.id = :schoolId_param"
						,Activity.class)
				.setParameter("schoolId_param", schoolId)
				.getResultList()
				.size();
	}

}