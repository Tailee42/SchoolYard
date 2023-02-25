package fr.isika.cda.entities.common;

import java.util.HashMap;
import java.util.Map;

public enum AcademicLevel {
    CP("Cp"),
    CE1("CE1"),
    CE2("CE2"),
    CM1("CM1"),
    CM2("CM2"),
    SIXIEME("6ème"),
    CINQUIEME("5ème"),
    QUATRIEME("4ème"),
    TROISIEME("3ème"),
    SECONDE("2nde"),
    PREMIERE("1ère"),
    TERMINALE("Terminale"),
    CLASSESPREPARATOIRES1("Classe préparatoire 1ère année"),
    CLASSESPREPARATOIRES2("Classe préparatoire 2ème année");
	
	private final String displayValue;

	private AcademicLevel (String displayValue){
		this.displayValue = displayValue;
	}

	public String getDisplayValue(){
		return displayValue;
	}
	
    public static Map<String, AcademicLevel> Academiclevels = new HashMap<>();
    
    static {
        for (AcademicLevel a: values()) {
            Academiclevels.put(a.displayValue, a);
        }
    }
}
