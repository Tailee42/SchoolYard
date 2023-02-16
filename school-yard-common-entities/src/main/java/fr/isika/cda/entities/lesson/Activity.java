package fr.isika.cda.entities.lesson;

import fr.isika.cda.entities.SchoolTypeEnum;
import fr.isika.cda.entities.SubjectEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Activity implements Serializable {

	private static final long serialVersionUID = -9156916020990785127L;
	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private SubjectEnum subject;
	
	@Enumerated(EnumType.STRING)
	private SchoolTypeEnum level;

	public Long getId() {
		return id;
	}

	public SubjectEnum getSubject() {
		return subject;
	}

	public void setSubject(SubjectEnum subject) {
		this.subject = subject;
	}

	public SchoolTypeEnum getLevel() {
		return level;
	}

	public void setLevel(SchoolTypeEnum level) {
		this.level = level;
	}
}
