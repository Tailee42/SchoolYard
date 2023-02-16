package fr.isika.cda.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.subscription.Subscription;

public class SubscriptionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Subscription subscription) {
        entityManager.persist(subscription);
    }
}
