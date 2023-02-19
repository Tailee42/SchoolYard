package fr.isika.cda.beans;

import fr.isika.cda.entities.school.Theme;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class IndexSchoolBean {

    private Theme theme = new Theme();

    public String toFormSynchronousLesson() {

        return "synchronousLessonForm?faces-redirect=true";
    }

    public String styles() {
        return new StringBuffer()
                .append(":root {")
                .append("--accentColor : #").append(theme.getAccentColor()).append(";")
                .append("--backgroundColor : #").append(theme.getBackgroundColor()).append(";")
                .append("--primaryColor : #").append(theme.getPrimaryColor()).append(";")
                .append("}")
                .toString();
    }
}
