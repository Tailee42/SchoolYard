package fr.isika.cda.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.lesson.AsynchronousLesson;

@Stateless
public class AsynchronousLessonRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save (AsynchronousLesson asynchronousLesson){
		entityManager.persist(asynchronousLesson);
	}
	
	
	
	
	
}
