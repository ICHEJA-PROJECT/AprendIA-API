package com.icheha.aprendia_api.auth.domain.enums;

/**
 * Enum para definir los valores de género
 * Valores: MASCULINE = 'M', FEMININE = 'F'
 */
public enum GenderEnum {
    MASCULINE('M'),
    FEMININE('F');
    
    private final char value;
    
    GenderEnum(char value) {
        this.value = value;
    }
    
    public char getValue() {
        return value;
    }
    
    public static GenderEnum fromValue(char value) {
        for (GenderEnum gender : values()) {
            if (gender.value == value) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Valor de género no válido: " + value);
    }
}