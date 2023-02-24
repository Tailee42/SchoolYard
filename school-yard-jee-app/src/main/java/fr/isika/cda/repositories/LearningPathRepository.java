package fr.isika.cda.repositories;

import fr.isika.cda.entities.student.LearningPath;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

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

	public List<LearningPath> getLearningPathsByStudentId(Long idStudent) {
		try {
			List<LearningPath> learningPaths = entityManager
					.createQuery("select l FROM LearningPath l WHERE l.student.id = :id_student", LearningPath.class)
					.setParameter("id_student", idStudent).getResultList();
			return learningPaths;
		} catch (NoResultException noResultException) {
			return new ArrayList<LearningPath>();
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
