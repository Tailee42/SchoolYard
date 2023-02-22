package fr.isika.cda.repositories;

import fr.isika.cda.entities.student.LearningPath;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class LearningPathRepository {
     @PersistenceContext
    private EntityManager entityManager;

     public void save(LearningPath learningPath) {
         entityManager.persist(learningPath);
     }

     public List<LearningPath> getLearningPathsByActivity(Long idActivity) {
         return entityManager
                 .createQuery("select l FROM LearningPath l WHERE l.activity.id = :id_activity", LearningPath.class)
                 .setParameter("id_activity", idActivity)
                 .getResultList();
     }

    public List<LearningPath> getLearningPathsByStudentId(Long idStudent) {
        return entityManager
                .createQuery("select l FROM LearningPath l WHERE l.student.id = :id_student", LearningPath.class)
                .setParameter("id_student", idStudent)
                .getResultList();
    }
}
