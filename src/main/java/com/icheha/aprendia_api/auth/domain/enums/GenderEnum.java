package com.icheha.aprendia_api.auth.domain.enums;

/**
 * Enum para definir los valores de género
 * Valores: MASCULINO, FEMENINO, OTRO
 */
public enum GenderEnum {
    MASCULINO("MASCULINO"),
    FEMENINO("FEMENINO"),
    OTRO("OTRO");
    
    private final String value;
    
    GenderEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static GenderEnum fromValue(String value) {
        for (GenderEnum gender : values()) {
            if (gender.value.equals(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Valor de género no válido: " + value);
    }
}