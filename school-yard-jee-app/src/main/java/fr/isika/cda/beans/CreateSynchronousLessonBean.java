package fr.isika.cda.beans;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.lesson.PhysicalOption;
import fr.isika.cda.entities.lesson.VirtualOption;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.repositories.PhysicalRepository;
import fr.isika.cda.repositories.VirtualRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class CreateSynchronousLessonBean {

	@Inject
	private PhysicalRepository physicalRepository;
	private PhysicalOption physicalOption = new PhysicalOption();
	private VirtualOption virtualOption = new VirtualOption();
	@Inject
	private VirtualRepository virtualRepository;

	public String createPhysical() {
		Teacher teacher = (Teacher) SessionUtils.getConnectedMember();
		physicalOption.getSynchronousLesson().setTeacher(teacher);
		physicalRepository.save(physicalOption);
		return "indexSchool?faces-redirect=true";
	}

	public String createVirtual() {
		Teacher teacher = (Teacher) SessionUtils.getConnectedMember();
		virtualOption.getSynchronousLesson().setTeacher(teacher);
		virtualRepository.save(virtualOption);
		return "indexSchool?faces-redirect=true";
	}

	public Map<String, SubjectEnum> subjects() {
		return SubjectEnum.subjects;
	}

	public Map<String, AcademicLevel> levels() {
		return AcademicLevel.Academiclevels;
	}

	public PhysicalOption getPhysicalOption() {
		return physicalOption;
	}

	public void setPhysicalOption(PhysicalOption physicalOption) {
		this.physicalOption = physicalOption;
	}

	public VirtualOption getVirtualOption() {
		return virtualOption;
	}

	public void setVirtualOption(VirtualOption virtualOption) {
		this.virtualOption = virtualOption;
	}
}
