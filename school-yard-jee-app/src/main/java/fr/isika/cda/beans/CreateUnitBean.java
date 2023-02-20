package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.repositories.UnitRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class CreateUnitBean {
	
	private Unit unit = new Unit() ;
	
	@Inject
	private UnitRepository unitRepository;
	
	public String create() {
		System.out.println(SessionUtils.isUserConnected());
		System.out.println(SessionUtils.isMemberConnected());
			Teacher connectedTeacher = (Teacher)SessionUtils.getConnectedMember();
			unit.setTeacher(connectedTeacher);
			unit.setVisibility(false);
	        unitRepository.save(unit);
	        return "userDashboard?faces-redirect=true";
	}
	
	public SubjectEnum[] subjects() {
        return SubjectEnum.values();
   }

   public AcademicLevel[] levels() {
        return AcademicLevel.values();
   }

	public Unit getUnit() {
		return unit;
	}
	
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}
