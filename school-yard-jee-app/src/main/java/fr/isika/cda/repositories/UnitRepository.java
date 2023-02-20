package fr.isika.cda.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.lesson.Unit;

@Stateless
public class UnitRepository {
	
	 @PersistenceContext
	    private EntityManager entityManager;

	    public void save(Unit unit) {
	        entityManager.persist(unit);
	    }

}
