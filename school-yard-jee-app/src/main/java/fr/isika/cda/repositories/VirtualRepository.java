package fr.isika.cda.repositories;

import fr.isika.cda.entities.lesson.VirtualOption;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Stateless
public class VirtualRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(VirtualOption virtualOption) {
        entityManager.persist(virtualOption);
    }

    public Optional<VirtualOption> getVirtualOptionBySynchronousLessonById(Long id) {
        VirtualOption virtualOption = entityManager
                .createQuery("SELECT v FROM VirtualOption v WHERE v.synchronousLesson.id = :id_synchronousLesson", VirtualOption.class)
                .setParameter("id_synchronousLesson", id)
                .getSingleResult();
        return Optional.ofNullable(virtualOption);

    }
}
