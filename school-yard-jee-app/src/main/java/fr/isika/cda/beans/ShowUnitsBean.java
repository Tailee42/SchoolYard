package fr.isika.cda.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.repositories.UnitRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class ShowUnitsBean {

	private Unit unit = new Unit();
	private List<Unit> units = new ArrayList<>();
	private Long schoolId = SessionUtils.getCurrentSchool().getId();

	@Inject
	private UnitRepository unitRepository;

	public List<Unit> allUnits() {
		units = unitRepository.getAll();
		return units;
	}

	public List<Unit> allUnitsBySchool() {
		units = unitRepository.getAllUnitsValidatedBySchool(schoolId);
		return units;
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

}
