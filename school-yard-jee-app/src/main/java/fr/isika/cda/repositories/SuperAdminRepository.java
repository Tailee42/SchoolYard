package fr.isika.cda.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.users.SuperAdmin;

@Stateless
public class SuperAdminRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(SuperAdmin superAdmin) {
		entityManager.persist(superAdmin);
	}

}
