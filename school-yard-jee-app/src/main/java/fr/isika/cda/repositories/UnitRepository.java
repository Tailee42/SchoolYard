package fr.isika.cda.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.entities.lesson.UnitStatusEnum;

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
	
	public Unit getUnitById(Long unitId) {
		return entityManager
			.createQuery("SELECT u FROM Unit u WHERE u.id = :unitId_param", Unit.class)
			.setParameter("unitId_param", unitId)
			.getSingleResult();
	}
	
	public List<Unit> currentSchoolUnits(List<Long> teachersIds) {
		return entityManager.createQuery("SELECT u FROM Unit u where u.teacher.id in(:teachersIds_param) AND u.status = :status_param", Unit.class)
				.setParameter("teachersIds_param", teachersIds)
				.setParameter("status_param", UnitStatusEnum.TOVALIDATE)
				.getResultList();
	}

}
