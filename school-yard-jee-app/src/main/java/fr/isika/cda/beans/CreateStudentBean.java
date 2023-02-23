package fr.isika.cda.beans;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.repositories.StudentRepository;
import fr.isika.cda.utils.SessionUtils;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean
public class CreateStudentBean {
    @Inject
    private StudentRepository studentRepository;

    @Inject
    private SchoolRepository schoolRepository;

    private Student student = new Student();



    public String create() {
            student.setUser(SessionUtils.getConnectedUser());
            student.setSchool(SessionUtils.getCurrentSchool());
            studentRepository.save(student);
            return "userDashboard?faces-redirect=true";
    }

    public AcademicLevel[] levels() {
        return AcademicLevel.values();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
