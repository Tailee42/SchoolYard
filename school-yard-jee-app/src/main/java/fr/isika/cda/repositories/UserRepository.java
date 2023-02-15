package fr.isika.cda.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.users.User;

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
				.setParameter("login_param", login).getSingleResult();
		return Optional.ofNullable(user);
	}

	public void updateLastConnection(User userConnected, LocalDateTime date) {
		userConnected.setLastConnection(date);
		entityManager.merge(userConnected);
	}

}
