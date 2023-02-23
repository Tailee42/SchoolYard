package fr.isika.cda.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.users.User;

@Stateless
public class UserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(User user) {
		entityManager.persist(user);
	}

	public void update(User user) {
		entityManager.merge(user);
	}
	
	// get user by login
	public Optional<User> getUserByLogin(String login) {
		User user = entityManager
				.createQuery("SELECT us FROM User us JOIN FETCH us.security WHERE us.login = :login_param", User.class)
				.setParameter("login_param", login)
				.getSingleResult();
		return Optional.ofNullable(user);
	}

	public void updateLastConnection(User userConnected, LocalDateTime date) {
		userConnected.setLastConnection(date);
		entityManager.merge(userConnected);
	}

	public Member getMemberByUserId(Long id) {
		return entityManager
				.createQuery("SELECT m FROM Member m JOIN FETCH m.user WHERE m.user.id = :id_user_param", Member.class)
				.setParameter("id_user_param", id).getSingleResult();
	}

}
