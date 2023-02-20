package fr.isika.cda.repositories;

import fr.isika.cda.entities.lesson.SynchronousLesson;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SynchronousLessonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(SynchronousLesson synchronousLesson) {
        entityManager.persist(synchronousLesson);
    }


}
