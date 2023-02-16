package fr.isika.cda.repositories;

import fr.isika.cda.entities.school.Admin;
import fr.isika.cda.entities.school.Member;

import javax.ejb.Stateless;
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

    public Optional<Member> findByIdSchoolAndIdUser(Long idSchool,  Long idUser) {
        Member member = entityManager
                .createQuery("SELECT m FROM Member m WHERE m.school.id = :id_school AND m.user.id = :id_user", Member.class)
                .setParameter("id_school", idSchool)
                .setParameter("id_user", idUser)
                .getSingleResult();
        return Optional.ofNullable(member);
    }
}
