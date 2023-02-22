package fr.isika.cda.repositories;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.student.Student;
import fr.isika.cda.entities.teacher.Teacher;

@Stateless
public class StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Student student) {
        entityManager.persist(student);
    }
    
    public void update(Student student) {
    	entityManager.merge(student);
    }
    
	public List<Student> currentSchoolStudents(Long schoolId) {
		return entityManager
				.createQuery("SELECT s FROM Student s WHERE s.school.id = :schoolId_param",
				Student.class)
				.setParameter("schoolId_param", schoolId)
				.getResultList();
	}

	public Optional<Student> getStudentById(Long studentId) {
		return Optional.ofNullable( 
				entityManager
				.createQuery("SELECT s FROM Student s WHERE s.id = :id_param", Student.class)
				.setParameter("id_param", studentId)
				.getSingleResult());
	}
}
