package fr.isika.cda.repositories;

import java.util.List;

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
	    
	    public void update(Unit unit) {
	    	entityManager.merge(unit);
	    }
	    
	    public List<Unit> thisSchoolUnits(List<Long> teachersIds){
	    	return entityManager
	    			.createQuery("SELECT u FROM Unit u where u.teacher.id in(:teachersIds_param)", Unit.class)
	    			.setParameter("teachersIds_param", teachersIds)
	    			.getResultList();
	    }

}
