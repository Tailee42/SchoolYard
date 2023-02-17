package fr.isika.cda.beans;

import fr.isika.cda.entities.school.School;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.utils.SessionUtils;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.Optional;

@ManagedBean
public class navigationToSchoolBean {
    @Inject
    private SchoolRepository schoolRepository;

    //Quand le tableau du dashboard sera en place, prendre en compte l'id de l'école
    public String toIndexSchool() {
        Optional<School> school = schoolRepository.getSchoolById(11L);
        if (school.isPresent()) {
            SessionUtils.setCurrentSchool(school.get());
            return"indexSchool?faces-redirect=true";
        }
        return "";
    }
}
