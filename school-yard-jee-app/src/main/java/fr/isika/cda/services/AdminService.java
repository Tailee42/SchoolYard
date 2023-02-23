package fr.isika.cda.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.school.Statistics;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.repositories.ActivityRepository;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.repositories.StatisticsRepository;
import fr.isika.cda.repositories.StudentRepository;
import fr.isika.cda.repositories.TeacherRepository;
import fr.isika.cda.repositories.UnitRepository;

@Stateless
public class AdminService {

	@Inject
	private TeacherRepository teacherRepository;

	@Inject
	private UnitRepository unitRepository;

	@Inject
	private StudentRepository studentRepository;

	@Inject
	private ActivityRepository activityRepository;

	@Inject
	private StatisticsRepository statisticsRepository;
	
	@Inject
	private SchoolRepository schoolRepository;

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

	public void initStats(int studentsNumber, int teachersNumber, School school) {
		school.getStats().setNumberOfStudents(studentsNumber);
		school.getStats().setNumberOfTeachers(teachersNumber);
		school.getStats().setNumberOfLessons(activityRepository.allSchoolActivities(school.getId()));
		statisticsRepository.update(school.getStats());
	}


}