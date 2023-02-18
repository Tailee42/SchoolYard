package fr.isika.cda.repositories;

import fr.isika.cda.entities.lesson.PhysicalOption;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PhysicalRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(PhysicalOption physicalOption) {
        entityManager.persist(physicalOption);
    }
}
