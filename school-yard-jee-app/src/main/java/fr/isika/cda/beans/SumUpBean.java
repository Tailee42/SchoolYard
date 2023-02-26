package fr.isika.cda.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.student.LearningPath;
import fr.isika.cda.repositories.LearningPathRepository;

@ManagedBean
@SessionScoped
public class SumUpBean {
	@Inject
	private LearningPathRepository learningPathRepository;

	private Long idSynchronousLesson;
	private List<LearningPath> learningPathList;

	public List<LearningPath> getLearningPathBySynchronousLesson() {
		learningPathList = learningPathRepository.getLearningPathsByActivity(idSynchronousLesson);
		return learningPathList;
	}

	public String addSumUp() {
		for (LearningPath learningPath : learningPathList) {
			learningPathRepository.update(learningPath);
		}
		return "showSynchronousLessonTeacher?faces-redirect=true";
	}

	// Getters ans Setters
	public Long getIdSynchronousLesson() {
		return idSynchronousLesson;
	}

	public void setIdSynchronousLesson(Long idSynchronousLesson) {
		this.idSynchronousLesson = idSynchronousLesson;
	}

	public List<LearningPath> getLearningPathList() {
		return learningPathList;
	}

	public void setLearningPathList(List<LearningPath> learningPathList) {
		this.learningPathList = learningPathList;
	}

}
