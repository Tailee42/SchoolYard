package fr.isika.cda.entities.school;

import javax.persistence.Entity;

@Entity
public class Statistics {

	private int numberOfTeachers;

	private int numberOfStudents;

	private int numberOfLessons;

	public int getNumberOfLessons() {
		return numberOfLessons;
	}

	public void setNumberOfLessons(int numberOfLessons) {
		this.numberOfLessons = numberOfLessons;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	public int getNumberOfTeachers() {
		return numberOfTeachers;
	}

	public void setNumberOfTeachers(int numberOfTeachers) {
		this.numberOfTeachers = numberOfTeachers;
	}

}
