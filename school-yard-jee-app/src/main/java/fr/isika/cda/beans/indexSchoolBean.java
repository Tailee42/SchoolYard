package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class indexSchoolBean {

    public String toFormSynchronousLesson(){

        return "synchronousLessonForm?faces-redirect=true";
    }
}
