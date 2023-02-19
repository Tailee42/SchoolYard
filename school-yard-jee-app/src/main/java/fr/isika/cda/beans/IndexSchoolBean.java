package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class IndexSchoolBean {

    public String toFormSynchronousLesson(){

        return "synchronousLessonForm?faces-redirect=true";
    }
}
