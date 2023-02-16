package fr.isika.cda.entities.student;

import fr.isika.cda.entities.AcademicLevel;
import fr.isika.cda.entities.school.Member;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Student extends Member {

    @Enumerated(EnumType.STRING)
    private AcademicLevel level;

    public Student(AcademicLevel level) {
        super();
        this.level = level;
    }

    public Student() {
        super();
    }

    public AcademicLevel getLevel() {
        return level;
    }

    public void setLevel(AcademicLevel level) {
        this.level = level;
    }
}
