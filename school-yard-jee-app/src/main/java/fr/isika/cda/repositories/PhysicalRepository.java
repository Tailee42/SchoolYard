package fr.isika.cda.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.lesson.PhysicalOption;

@Stateless
public class PhysicalRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(PhysicalOption physicalOption) {
        entityManager.persist(physicalOption);
    }

    public PhysicalOption getPhysicalOptionBySynchronousLessonById(Long id) {
        return entityManager
                .createQuery("SELECT p FROM PhysicalOption p WHERE p.synchronousLesson.id = :id_synchronousLesson", PhysicalOption.class)
                .setParameter("id_synchronousLesson", id)
                .getSingleResult();
    }
}
