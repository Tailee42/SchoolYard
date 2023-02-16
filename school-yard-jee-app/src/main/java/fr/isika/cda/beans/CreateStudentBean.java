package fr.isika.cda.beans;

import fr.isika.cda.entities.AcademicLevel;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.repositories.StudentRepository;
import fr.isika.cda.utils.SessionUtils;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.Optional;

@ManagedBean
public class CreateStudentBean {
    @Inject
    private StudentRepository studentRepository;

    @Inject
    private SchoolRepository schoolRepository;

    private Student student = new Student();

    private Long idSchool;

    public String create() {
        Optional<School> school = schoolRepository.getSchoolById(idSchool);
        if (school.isPresent()) {
            User user = SessionUtils.getConnectedUser();
            student.setUser(user);
            student.setSchool(school.get());
            studentRepository.save(student);
            return "userDashboard?faces-redirect=true";
        }
        return "index?faces-redirect=true";
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

    public Long getIdSchool() {
        return idSchool;
    }

    public void setIdSchool(Long idSchool) {
        this.idSchool = idSchool;
    }
}
