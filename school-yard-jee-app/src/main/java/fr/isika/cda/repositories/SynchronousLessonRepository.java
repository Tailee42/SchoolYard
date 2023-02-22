package fr.isika.cda.repositories;

import fr.isika.cda.entities.lesson.SynchronousLesson;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SynchronousLessonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(SynchronousLesson synchronousLesson) {
        entityManager.persist(synchronousLesson);
    }


    public List<SynchronousLesson> getSynchronousLessonsByIdSchool(Long id) {
        return entityManager
                .createQuery("SELECT sc FROM SynchronousLesson sc WHERE sc.teacher.school.id = :id_school", SynchronousLesson.class)
                .setParameter("id_school", id)
                .getResultList();
    }
}
