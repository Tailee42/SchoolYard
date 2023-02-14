package fr.isika.cda.repositories;

import java.util.Optional;

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
	public Optional<User> getUserByLogin(String login) {
		User user = entityManager
				.createQuery("SELECT us FROM User us JOIN FETCH us.security WHERE us.login = :login_param", User.class)
				.setParameter("login_param", login)
				.getSingleResult();
		return Optional.ofNullable(user);
	}

}
