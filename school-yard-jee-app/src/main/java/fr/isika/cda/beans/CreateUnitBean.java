package fr.isika.cda.beans;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.entities.lesson.UnitStatusEnum;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.repositories.UnitRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class CreateUnitBean {

	private Unit unit = new Unit();

	@Inject
	private UnitRepository unitRepository;

	public String create() {
		Teacher connectedTeacher = (Teacher) SessionUtils.getConnectedMember();
		unit.setTeacher(connectedTeacher);
		unit.setStatus(UnitStatusEnum.TOVALIDATE);
		unitRepository.save(unit);
		return "userDashboard?faces-redirect=true";
	}

	public Map<String, SubjectEnum> subjects() {
		return SubjectEnum.subjects;
	}

	public Map<String, AcademicLevel> levels() {
		return AcademicLevel.Academiclevels;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}
