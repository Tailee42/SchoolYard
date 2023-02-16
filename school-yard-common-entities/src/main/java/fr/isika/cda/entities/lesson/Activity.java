package fr.isika.cda.entities.lesson;

import fr.isika.cda.entities.SchoolTypeEnum;
import fr.isika.cda.entities.SubjectEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Activity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private SubjectEnum subject;
	
	@Enumerated(EnumType.STRING)
	private SchoolTypeEnum level;

}
