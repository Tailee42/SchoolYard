package fr.isika.cda.beans;

import fr.isika.cda.entities.student.Student;
import fr.isika.cda.repositories.SchoolRepository;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.subscription.Feature;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class IndexSchoolBean {

	private School school = new School();

	public void getSchoolsInformations() {
		school = SessionUtils.getCurrentSchool();
	}


    public boolean isStudent() {
    	return(SessionUtils.getConnectedMember() instanceof Student);
    }
        
    public boolean isUserConnected() {
        return SessionUtils.isUserConnected();
    }

	public boolean isTeacher() {
		return (SessionUtils.getConnectedMember() instanceof Teacher);
	}

	public boolean isUserConnected() {
		return SessionUtils.isUserConnected();
	}

    public boolean hasLogo() {
        return school.getLogo() != null
                && !school.getLogo().isBlank();
    }

    public boolean isSynchronousLesson() {

		List<Feature> features = school.getMembership().getSubscription().getFeatures();
		for (Feature feature : features) {
			if ("Cours online".equals(feature.getFeatureTitle())) {
				return true;
			}
		}
		return false;
	}

	public boolean isAsynchronousLesson() {
		for (Feature feature : school.getMembership().getSubscription().getFeatures()) {
			if ("Cours offline".equals(feature.getFeatureTitle())) {
				return true;
			}
		}
		return false;
	}

	public String logoutSchool() {
		SessionUtils.setCurrentSchool(null);
		SessionUtils.setConnectedMember(null);
		return "userDashboard?faces-redirect=true";
	}

	public String styles() {
		String colorString = new StringBuffer().append(":root {").append("--accentColor : #")
				.append(school.getSchoolPage().getTheme().getAccentColor()).append(";").append("--backgroundColor : #")
				.append(school.getSchoolPage().getTheme().getBackgroundColor()).append(";").append("--primaryColor : #")
				.append(school.getSchoolPage().getTheme().getPrimaryColor()).append(";").append("}").toString();

		String policeString = getpoliceString();

		return (colorString + policeString);
	}

	private String getpoliceString() {
		switch (school.getSchoolPage().getTheme().getFont()) {
		case "PlayfairDisplay":
			return new StringBuilder().append("@font-face {\n").append("            font-family: 'Playfair';\n")
					.append("            src: url(‘/fonts/PlayfairDisplay-VariableFont_wght.ttf’) format(‘truetype’)\n")
					.append("        }").append("body {\n").append("\tfont-family: 'Playfair', sans-serif;\n")
					.append("}").toString();

		case "Quicksand":
			return new StringBuilder().append("@font-face {\n").append("            font-family: 'Quicksand';\n")
					.append("            src: url(‘/fonts/Quicksand-VariableFont_wght.ttf’) format(‘truetype’)\n")
					.append("        }").append("body {\n").append("\tfont-family: 'Quicksand', sans-serif;\n")
					.append("}").toString();

		case "Roboto":
			return new StringBuilder().append("@font-face {\n").append("            font-family: 'Roboto';\n")
					.append("            src: url(‘/fonts/Roboto-Black.ttf’) format(‘truetype’)\n").append("        }")
					.append("body {\n").append("\tfont-family: 'Roboto', sans-serif;\n").append("}").toString();

		case "Ubuntu":
			return new StringBuilder().append("@font-face {\n").append("            font-family: 'Ubuntu';\n")
					.append("            src: url(‘/fonts/Ubuntu-Light.ttf’) format(‘truetype’)\n").append("        }")
					.append("body {\n").append("\tfont-family: 'Ubuntu', sans-serif;\n").append("}").toString();

		case "Zeyada":
			return new StringBuilder().append("@font-face {\n").append("            font-family: 'Zeyada';\n")
					.append("            src: url(‘/fonts/Zeyada-Regular.ttf’) format(‘truetype’)\n")
					.append("        }").append("body {\n").append("\tfont-family: 'Zeyada', sans-serif;\n").append("}")
					.toString();

		default:
			return "";
		}
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

}
