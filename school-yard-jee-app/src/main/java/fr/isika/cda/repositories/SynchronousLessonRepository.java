package fr.isika.cda.repositories;

import fr.isika.cda.entities.lesson.SynchronousLesson;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class SynchronousLessonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(SynchronousLesson synchronousLesson) {
        entityManager.persist(synchronousLesson);
    }


    public List<SynchronousLesson> getFuturSynchronousLessonsByIdMember(Long id) {
        return entityManager
                .createQuery("SELECT sc FROM SynchronousLesson sc WHERE sc.teacher.id = :id_member AND sc.classDate > :id_date", SynchronousLesson.class)
                .setParameter("id_member", id)
                .setParameter("id_date", LocalDateTime.now())
                .getResultList();
    }


    public List<SynchronousLesson> getFuturSynchronousLessonsByIdSchool(Long id) {
        return entityManager
                .createQuery("SELECT sc FROM SynchronousLesson sc WHERE sc.teacher.school.id = :id_school AND sc.classDate > :id_date", SynchronousLesson.class)
                .setParameter("id_school", id)
                .setParameter("id_date", LocalDateTime.now())
                .getResultList();
    }
}
