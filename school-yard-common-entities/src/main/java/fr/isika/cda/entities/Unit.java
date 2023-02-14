package fr.isika.cda.entities;

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
	private LevelEnum level;

}
