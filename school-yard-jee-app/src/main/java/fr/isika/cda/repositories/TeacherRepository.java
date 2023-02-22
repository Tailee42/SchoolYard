package fr.isika.cda.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.entities.teacher.TeacherStatusEnum;

import java.util.List;
import java.util.Optional;

@Stateless
public class TeacherRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Teacher teacher) {
		entityManager.persist(teacher);
	}

	public void update(Teacher teacher) {
		entityManager.merge(teacher);
	}

	public Optional<Teacher> getTeacherById(Long id) {
		Teacher teacher = entityManager.createQuery("SELECT t FROM Teacher t WHERE t.id = :id_param", Teacher.class)
				.setParameter("id_param", id).getSingleResult();
		return Optional.ofNullable(teacher);
	}

	public List<Teacher> currentSchoolTeachers(Long schoolId) {
		return entityManager
				.createQuery(
				"SELECT t FROM Teacher t WHERE t.school.id = :schoolId_param AND t.status = :teacherstatus_param",
				Teacher.class)
				.setParameter("schoolId_param", schoolId)
				.setParameter("teacherstatus_param", TeacherStatusEnum.InProgress)
				.getResultList();
	}
}
