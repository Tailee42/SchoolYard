package fr.isika.cda.repositories;

import fr.isika.cda.entities.lesson.VirtualOption;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class VirtualRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(VirtualOption virtualOption) {
        entityManager.persist(virtualOption);
    }
}
