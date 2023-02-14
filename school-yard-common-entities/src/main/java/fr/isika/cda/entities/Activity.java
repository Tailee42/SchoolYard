package fr.isika.cda.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Activity {
	
	@Id
	@GeneratedValue
	private Long Id;
	
	@Enumerated(EnumType.STRING)
	private SubjectEnum subject;
	
	@Enumerated(EnumType.STRING)
	private LevelEnum level;

}
