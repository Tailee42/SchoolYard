package fr.isika.cda.repositories;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.common.SchoolTypeEnum;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.school.StatusSchool;

@Stateless
public class SchoolRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(School school) {
		entityManager.persist(school);
	}

	public void update(School school) {
		entityManager.merge(school);
	}

	public School getSchoolById(Long id) {
		return  entityManager
				.createQuery("SELECT s FROM School s WHERE s.id = :id_param", School.class)
				.setParameter("id_param", id)
				.getSingleResult();
		
	}

	public List<School> getAll() {
		return entityManager
				.createQuery("SELECT s FROM School s ", School.class)
				.getResultList();
	}
	
	public List<School> getAllActiveSchool(StatusSchool status1) {
		return entityManager
				.createQuery("SELECT s FROM School s WHERE s.statusSchool = :param1", School.class)
				.setParameter("param1", status1)
				.getResultList();
	}

	public List<School> getByName(String name) {
		return entityManager
				.createQuery("SELECT s FROM School s WHERE s.schoolName LIKE '%' || :name_param || '%' ", School.class)
				.setParameter("name_param", name)
				.getResultList();
	}
	
	public List<School> getActiveSchoolByName(String name, StatusSchool schoolStatus) {
		return entityManager
				.createQuery("SELECT s FROM School s WHERE s.schoolName LIKE '%' || :name_param || '%' and s.statusSchool = :status_param", School.class)
				.setParameter("name_param", name)
				.setParameter("status_param", schoolStatus)
				.getResultList();
	}

	public List<School> getByType(SchoolTypeEnum schoolType) {
		return entityManager
				.createQuery("SELECT s FROM School s WHERE s.schoolTypeEnum = :type_param", School.class)
				.setParameter("type_param", schoolType)
				.getResultList();
	}
	
	public List<School> getActiveSchoolByType(SchoolTypeEnum schoolType, StatusSchool schoolStatus) {
		return entityManager
				.createQuery("SELECT s FROM School s WHERE s.schoolTypeEnum = :type_param and s.statusSchool = :status_param", School.class)
				.setParameter("type_param", schoolType)
				.setParameter("status_param", schoolStatus)
				.getResultList();
	}


}
