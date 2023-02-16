package fr.isika.cda.entities.lesson;


import fr.isika.cda.entities.SchoolTypeEnum;
import fr.isika.cda.entities.SubjectEnum;

import javax.persistence.*;

@Entity
public class Unit {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	private String content;

	private boolean visibility;

	@Enumerated(EnumType.STRING)
	private SubjectEnum subject;

	@Enumerated(EnumType.STRING)
	private SchoolTypeEnum level;


}
