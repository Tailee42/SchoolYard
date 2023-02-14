package fr.isika.cda.repositories;

import fr.isika.cda.entities.school.Admin;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MemberRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Admin admin) {

        entityManager.persist(admin);
    }
}
