package fr.isika.cda.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.User;

@Stateless
public class UserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(User user) {

		entityManager.persist(user);
	}

	// get user by login
	public List<User> getUserByLogin(String login) {
		Query query = entityManager.createQuery("SELECT us FROM User us JOIN FETCH us.security WHERE us.login = :login", User.class);
		query.setParameter("login", login);
		return query.getResultList();
	}

}
