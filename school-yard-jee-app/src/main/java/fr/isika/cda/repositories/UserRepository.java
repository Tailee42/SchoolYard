package fr.isika.cda.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.User;

@Stateless
public class UserRepository {

	@PersistenceContext
	
	private EntityManager entityManager;
	
	public void save(User user) {
	
		entityManager.persist(user);
	}
}
