package fr.isika.cda.repositories;

import fr.isika.cda.entities.school.Admin;
import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.utils.SessionUtils;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MemberRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Admin admin) {
        entityManager.persist(admin);
    }

    public List<Member> getAllMembersForOneUser() {
    	
    	List<Member> Members = entityManager
		.createQuery("SELECT m FROM Member m WHERE m.userid = :userConnected.getId() ", Member.class)
		.getResultList();
		return Members;
    	
    }
    
        
    
}
