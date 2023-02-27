package fr.isika.cda.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.school.Admin;
import fr.isika.cda.entities.school.Member;

@Stateless
public class MemberRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(Admin admin) {
		entityManager.persist(admin);
	}

	public List<Member> getAllMembersForOneUser(Long userId) {
		try {
			return entityManager
					.createQuery("SELECT m FROM Member m WHERE m.user.id = :userId ", Member.class)
					.setParameter("userId", userId)
					.getResultList();
		} catch (NoResultException noResultException) {
			return new ArrayList<>();
		}

	}

    public Optional<Member> findByIdSchoolAndIdUser(Long idSchool,  Long idUser) {
        Member member = entityManager
                .createQuery("SELECT m FROM Member m WHERE m.school.id = :id_school AND m.user.id = :id_user", Member.class)
                .setParameter("id_school", idSchool)
                .setParameter("id_user", idUser)
                .getSingleResult();
        return Optional.ofNullable(member);
    }	
}
