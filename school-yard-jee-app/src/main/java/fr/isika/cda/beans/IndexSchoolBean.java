package fr.isika.cda.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.entities.subscription.Feature;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.utils.SessionUtils;
import fr.isika.cda.utils.StringHelper;

@ManagedBean
@SessionScoped
public class IndexSchoolBean {

	private School school = new School();

	public void getSchoolsInformations() {
		school = SessionUtils.getCurrentSchool();
	}

	public boolean isUserConnected() {
		return SessionUtils.isUserConnected();
	}

	public boolean isStudent() {
		return (SessionUtils.getConnectedMember() instanceof Student);
	}

	public boolean isTeacher() {
		return (SessionUtils.getConnectedMember() instanceof Teacher);
	}

	public boolean hasLogo() {
		return StringHelper.isNullOrEmpty(school.getLogo());
	}

	public boolean hasSchoolPagePicture() {
		return StringHelper.isNullOrEmpty(school.getSchoolPage().getSchoolValue().getPicture());
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
		case "PLAYFAIRDISPLAY":
			return new StringBuilder()
					.append("body {\n")
					.append("\tfont-family: 'Playfair Display', sans-serif;\n")
					.append("}").toString();

		case "QUICKSAND":
			return new StringBuilder().append("body {\n").append("\tfont-family: 'Quicksand', sans-serif;\n")
					.append("}").toString();

		case "ROBOTO":
			return new StringBuilder()
					.append("body {\n").append("\tfont-family: 'Roboto', sans-serif;\n").append("}").toString();

		case "UBUNTU":
			return new StringBuilder()
					.append("body {\n").append("\tfont-family: 'Ubuntu', sans-serif;\n").append("}").toString();

		case "ZEYADA":
			return new StringBuilder().append("body {\n").append("\tfont-family: 'Pacifico', sans-serif;\n").append("}")
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
