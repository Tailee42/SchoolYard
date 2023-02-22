package fr.isika.cda.repositories;

import fr.isika.cda.entities.school.Admin;
import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.utils.SessionUtils;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Stateless
public class MemberRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(Admin admin) {
		entityManager.persist(admin);
	}

	public List<Member> getAllMembersForOneUser(Long userId) {
		return entityManager
				.createQuery("SELECT m FROM Member m WHERE m.user.id = :userId ", Member.class)
				.setParameter("userId", userId).getResultList();
	}

    public Optional<Member> findByIdSchoolAndIdUser(Long idSchool,  Long idUser) {
        Member member = entityManager
                .createQuery("SELECT m FROM Member m WHERE m.school.id = :id_school AND m.user.id = :id_user", Member.class)
                .setParameter("id_school", idSchool)
                .setParameter("id_user", idUser)
                .getSingleResult();
        return Optional.ofNullable(member);
    }

    //Method to display on schoolIndex page
	public List<Member> getAllSchoolsMembers(Long schoolId) {
		return entityManager
				.createQuery("SELECT m FROM Member m WHERE m.school.id = :schoolId_param",Member.class)
				.setParameter("schoolId_param", schoolId)
				.getResultList();
	}
	
	
	
}
