package fr.isika.cda.entities.common;

import java.util.HashMap;
import java.util.Map;

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
    
    public static Map<String, RoleTypeEnum> roles = new HashMap<>();
    
    static {
        for (RoleTypeEnum r: values()) {
            roles.put(r.displayValue, r);
        }
    }
}
