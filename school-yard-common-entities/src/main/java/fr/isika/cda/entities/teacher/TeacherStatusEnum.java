package fr.isika.cda.entities.teacher;

import java.util.HashMap;
import java.util.Map;

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
	
    public static Map<String, TeacherStatusEnum> teacherStatus = new HashMap<>();
    
    static {
        for (TeacherStatusEnum t: values()) {
            teacherStatus.put(t.displayValue, t);
        }
    }
}
