package fr.isika.cda.entities.common;

public enum RoleTypeEnum {
    SUPER_ADMIN("Super-Administrateur"),
    USER("Utilisateur");
    private final String displayValue;

    private RoleTypeEnum(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
