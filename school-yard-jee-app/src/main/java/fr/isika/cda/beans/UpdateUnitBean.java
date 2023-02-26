package fr.isika.cda.beans;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.repositories.UnitRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class UpdateUnitBean {

	@Inject
	private UnitRepository unitRepository;
	
	private Unit unit = new Unit();
	
	public String goToUpdateUnitForm(Unit selectedUnit) {
		unit = unitRepository.getUnitById(selectedUnit.getId());
        SessionUtils.setConnectedMember(unit.getTeacher());
        SessionUtils.setCurrentSchool(unit.getTeacher().getSchool());
		return "/updateUnitForm.xhtml?faces-redirect=true";
	}
	
	public String updateUnit() {
		unitRepository.update(unit);
		unit = new Unit();
		return "showUnitsTeacher";
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Map<String, SubjectEnum> subjects() {
		return SubjectEnum.subjects;
	}

	public Map<String, AcademicLevel> levels() {
		return AcademicLevel.Academiclevels;
	}
	
}
