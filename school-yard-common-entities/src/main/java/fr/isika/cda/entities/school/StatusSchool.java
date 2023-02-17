package fr.isika.cda.entities.school;

public enum StatusSchool {
    TOPUBLISH("A publier"), 
    PUBLISHED("En ligne"), 
    TOUPDATE("A mettre à jour"),
    EXPIRED("Abonnement échu");
    
    private final String displayValue;
	
	private StatusSchool(String displayValue) {
		this.displayValue = displayValue;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}
}
