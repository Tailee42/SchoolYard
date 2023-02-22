package fr.isika.cda.repositories;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.subscription.Subscription;

@Stateless
public class SubscriptionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Subscription subscription) {
        entityManager.persist(subscription);
    }
    
    public List<Subscription> getAll() {
    	return entityManager
    			.createQuery("SELECT s FROM Subscription s", Subscription.class)
    			.getResultList();
    }

	public Subscription getSubscriptionById(Long id) {
		
		return entityManager
				.createQuery("SELECT s FROM Subscription s WHERE s.id = :id_param", Subscription.class)
				.setParameter("id_param", id)
				.getSingleResult();
	}
}
