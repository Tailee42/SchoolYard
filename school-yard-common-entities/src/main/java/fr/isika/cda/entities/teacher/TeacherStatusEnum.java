package fr.isika.cda.entities.teacher;

public enum TeacherStatusEnum {
	InProgress("Candidature déposée"),
	Rejected("Candidature rejetée"),
	Approved("Candidature acceptée");

	private final String displayValue;

	private TeacherStatusEnum(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
}
