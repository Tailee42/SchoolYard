package fr.isika.cda.repositories;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.lesson.SynchronousLesson;
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
		return entityManager.createQuery("SELECT u FROM Unit u WHERE u.id = :unit_id_param", Unit.class)
				.setParameter("unit_id_param", unitId).getSingleResult();
	}

	public List<Unit> currentSchoolUnits(List<Long> teachersIds) {
		return entityManager
				.createQuery(
						"SELECT u FROM Unit u WHERE u.teacher.id in(:teachersIds_param) AND u.status = :status_param",
						Unit.class)
				.setParameter("teachersIds_param", teachersIds).setParameter("status_param", UnitStatusEnum.TOVALIDATE)
				.getResultList();
	}

	public List<Unit> thisSchoolUnits(List<Long> teachersIds) {
		return entityManager.createQuery("SELECT u FROM Unit u WHERE u.teacher.id in(:teachersIds_param)", Unit.class)
				.setParameter("teachersIds_param", teachersIds).getResultList();
	}

	public List<Unit> getAll() {
		return entityManager.createQuery("SELECT u FROM Unit u", Unit.class).getResultList();
	}

	public List<Unit> getAllUnitsValidatedBySchool(Long schoolId) {
		return entityManager.createQuery("SELECT u FROM Unit u WHERE u.teacher.school.id = : id_school_param AND u.status = :status_param", Unit.class)
				.setParameter("id_school_param", schoolId).setParameter("status_param", UnitStatusEnum.VALIDATED).getResultList();
	}
	
    public List<Unit> getValidatedUnitsByLevel(Long id, AcademicLevel level) {
        return entityManager
                .createQuery("SELECT u FROM Unit u WHERE u.level = :unit_level AND u.teacher.school.id = :id_school AND u.status = :status_param", Unit.class)
                .setParameter("unit_level", level)
                .setParameter("id_school", id)
                .setParameter("status_param", UnitStatusEnum.VALIDATED)
                .getResultList();
    }
    
    public List<Unit> getValidatedUnitsBySubject(Long id, SubjectEnum subject) {
        return entityManager
                .createQuery("SELECT u FROM Unit u WHERE u.subject = :unit_subject AND u.teacher.school.id = :id_school AND u.status = :status_param", Unit.class)
                .setParameter("unit_subject", subject)
                .setParameter("id_school", id)
                .setParameter("status_param", UnitStatusEnum.VALIDATED)
                .getResultList();
    }

}
