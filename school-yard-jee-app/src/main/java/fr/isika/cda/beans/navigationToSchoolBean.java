package fr.isika.cda.beans;

import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.repositories.TeacherRepository;
import fr.isika.cda.utils.SessionUtils;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.Optional;

@ManagedBean
public class navigationToSchoolBean {
    @Inject
    private SchoolRepository schoolRepository;
    @Inject
    private TeacherRepository teacherRepository;

    //Quand le tableau du dashboard sera en place, prendre en compte l'id de l'école
    public String toIndexSchool() {
        Optional<School> school = schoolRepository.getSchoolById(11L);
        Optional<Teacher> teacher = teacherRepository.getTeacherById(41L);
        if (school.isPresent() && teacher.isPresent()) {
            SessionUtils.setCurrentSchool(school.get());
            SessionUtils.setConnectedMember(teacher.get());
            return"indexSchool?faces-redirect=true";
        }
        return "";
    }
}
