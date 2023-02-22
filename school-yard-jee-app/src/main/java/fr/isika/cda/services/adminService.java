package fr.isika.cda.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.repositories.StudentRepository;
import fr.isika.cda.repositories.TeacherRepository;
import fr.isika.cda.repositories.UnitRepository;

@Stateless
public class adminService {
	
	@Inject
	private TeacherRepository teacherRepository;
	
	@Inject
	private UnitRepository unitRepository;
	
	@Inject
	private StudentRepository studentRepository;
	
	public void validateTeacher(Teacher teacher) {
		teacher.validate();

		teacherRepository.update(teacher);

	}

	public void rejectTeacher(Teacher teacher) {

		teacher.reject();

		teacherRepository.update(teacher);
	}

	public void deleteTeacher(Teacher teacher) {

		teacher.eraseSchool();

		teacherRepository.update(teacher);
	}

	public void validateUnit(Unit unit) {
		unit.validate();
		unitRepository.update(unit);
	}

	public void rejectUnit(Unit unit) {
		unit.reject();
		unitRepository.update(unit);
	}
	
	public void deleteStudent(Student student) {
		student.eraseSchool();
		studentRepository.update(student);
	}

	

}
