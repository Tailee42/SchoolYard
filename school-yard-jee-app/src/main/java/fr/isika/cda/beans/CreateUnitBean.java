package fr.isika.cda.beans;

import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.repositories.TeacherRepository;
import fr.isika.cda.repositories.UnitRepository;

@ManagedBean
public class CreateUnitBean {
	
	private Unit unit = new Unit() ;
	private Long id;
	
	@Inject
	private UnitRepository unitRepository;
	
	@Inject
	private TeacherRepository teacherRepository;
	
	public String create() {
		Optional<Teacher> teacher = teacherRepository.getTeacherById(id);
		if (teacher.isPresent()) {
			Teacher connectedTeacher = teacher.get();
			unit.setTeacher(connectedTeacher);
			unit.setVisibility(false);
	        unitRepository.save(unit);
	        return "userDashboard?faces-redirect=true";
		}
		return "index?faces-redirect=true";
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
