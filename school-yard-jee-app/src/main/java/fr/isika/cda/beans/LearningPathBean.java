package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.student.LearningPath;
import fr.isika.cda.repositories.LearningPathRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class LearningPathBean {

	@Inject
	private LearningPathRepository learningPathRepository;

	public String getSumUpBySynchronousLessonAndStudent(Long idSynchronousLesson) {
		Long idStudent = SessionUtils.getConnectedMember().getId();
		LearningPath learningPath = learningPathRepository
				.getLearningPathBySynchronousLessonIdAndStudentID(idSynchronousLesson, idStudent);
		return learningPath.getSumUp();
	}
}
