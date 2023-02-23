package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.repositories.UnitRepository;

@ManagedBean
public class UnitBean {
	
	private Unit unit = new Unit();
	private Long id;
	
	@Inject
	private UnitRepository unitRepository;

	public void getUnitId(){
		unit = unitRepository.getUnitById(id);
	}
		
	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	
}
