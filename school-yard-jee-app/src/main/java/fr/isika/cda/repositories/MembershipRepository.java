package fr.isika.cda.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.school.Membership;

@Stateless
public class MembershipRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Membership membership) {
		entityManager.persist(membership);
	}

}
