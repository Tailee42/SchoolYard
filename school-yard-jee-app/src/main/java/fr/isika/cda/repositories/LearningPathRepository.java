package fr.isika.cda.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.student.LearningPath;

@Stateless
public class LearningPathRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(LearningPath learningPath) {
		entityManager.persist(learningPath);
	}

	public void update(LearningPath learningPath) {
		entityManager.merge(learningPath);
	}

	public List<LearningPath> getLearningPathsByActivity(Long idActivity) {
		return entityManager
				.createQuery("select l FROM LearningPath l WHERE l.activity.id = :id_activity", LearningPath.class)
				.setParameter("id_activity", idActivity).getResultList();
	}

	public List<LearningPath> getLearningPathsByUserId(Long idUser) {
		try {
			return entityManager
					.createQuery("select l FROM LearningPath l WHERE l.student.user.id = :id_user", LearningPath.class)
					.setParameter("id_user", idUser)
					.getResultList();
		} catch (NoResultException noResultException) {
			return new ArrayList<>();
		}
	}

    public LearningPath getLearningPathBySynchronousLessonIdAndStudentID(Long idSynchronousLesson, Long idStudent) {
        return entityManager
                .createQuery("select l FROM LearningPath l WHERE l.student.id = :id_student AND l.activity.id = :id_lesson", LearningPath.class)
                .setParameter("id_student", idStudent)
                .setParameter("id_lesson", idSynchronousLesson)
                .getSingleResult();
    }

}
