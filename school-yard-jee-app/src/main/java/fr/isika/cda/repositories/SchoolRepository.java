package fr.isika.cda.repositories;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.SchoolTypeEnum;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.school.SchoolPage;

@Stateless
public class SchoolRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(School school) {
		entityManager.persist(school);
	}

	public Optional<School> getSchoolById(Long id) {
		School school = entityManager
				.createQuery("SELECT s FROM School s WHERE s.id = :id_param", School.class)
				.setParameter("id_param", id)
				.getSingleResult();
		return Optional.ofNullable(school);
	}

	public List<School> getAll() {
		return entityManager
				.createQuery("SELECT s FROM School s ", School.class)
				.getResultList();
	}

	public List<School> getByName(String name) {
		return entityManager
				.createQuery("SELECT s FROM School s WHERE s.schoolName LIKE '%' || :name_param || '%' ", School.class)
				.setParameter("name_param", name)
				.getResultList();
	}

	public List<School> getByType(SchoolTypeEnum schoolType) {
		return entityManager
				.createQuery("SELECT s FROM School s WHERE s.schoolTypeEnum = :type_param", School.class)
				.setParameter("type_param", schoolType)
				.getResultList();
	}


	public void update(School school) {
		entityManager.merge(school);
	}

}
