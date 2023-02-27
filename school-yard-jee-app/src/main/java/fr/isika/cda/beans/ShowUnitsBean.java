package fr.isika.cda.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.lesson.AsynchronousLesson;
import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.student.LearningPath;
import fr.isika.cda.repositories.LearningPathRepository;
import fr.isika.cda.repositories.UnitRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class ShowUnitsBean {

	private Unit unit = new Unit();
	private List<Unit> units = new ArrayList<>();
	private Long schoolId = SessionUtils.getCurrentSchool().getId();

	@Inject
	private UnitRepository unitRepository;
	@Inject
	private LearningPathRepository learningPathRepository;

	public List<Unit> allUnits() {
		units = unitRepository.getAll();
		return units;
	}

	public List<Unit> allUnitsBySchool() {
		units = unitRepository.getAllUnitsValidatedBySchool(schoolId);
		return units;
	}

	public List<Unit> allUnitsByTeacher() {
		units = unitRepository.getAllUnitsByTeacherId(SessionUtils.getConnectedMember().getId());
		return units;
	}

	public List<Unit> allUnitsByUser() {
		List<Unit> unitsList = new ArrayList<>();
		List<LearningPath> learningPathList = learningPathRepository
				.getLearningPathsByUserId(SessionUtils.getConnectedUser().getId());
		for (LearningPath learningPath : learningPathList) {
			if (learningPath.getActivity() instanceof AsynchronousLesson) {
				AsynchronousLesson asynchronousLesson = (AsynchronousLesson) learningPath.getActivity();
				unitsList.add(asynchronousLesson.getUnit());
			}
		}
		return unitsList;

	}

	public List<Unit> getUnitByLevel() {
		School school = SessionUtils.getCurrentSchool();
		return unitRepository.getValidatedUnitsByLevel(school.getId(), unit.getLevel());
	}

	public List<Unit> getUnitBySubject() {
		School school = SessionUtils.getCurrentSchool();
		return unitRepository.getValidatedUnitsBySubject(school.getId(), unit.getSubject());
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public Map<String, SubjectEnum> subjects() {
		return SubjectEnum.subjects;
	}

	public Map<String, AcademicLevel> levels() {
		return AcademicLevel.Academiclevels;
	}

}
