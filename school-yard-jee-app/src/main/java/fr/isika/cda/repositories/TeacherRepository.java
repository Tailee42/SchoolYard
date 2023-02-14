package fr.isika.cda.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.teacher.Teacher;

@Stateless
public class TeacherRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(Teacher teacher) {
		entityManager.persist(teacher);
	}

}
