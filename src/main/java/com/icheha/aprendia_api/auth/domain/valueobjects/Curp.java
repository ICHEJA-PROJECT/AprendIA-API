package com.icheha.aprendia_api.auth.domain.valueobjects;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Value Object para CURP (Clave Única de Registro de Población)
 * Encapsula la validación y lógica relacionada con CURP
 */
public class Curp {
    
    private static final Pattern CURP_PATTERN = Pattern.compile(
        "^[A-Z]{4}[0-9]{6}[HM][A-Z]{5}[A-Z0-9]{2}$"
    );
    
    private final String value;
    
    public Curp(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("CURP no puede ser nulo o vacío");
        }
        
        String trimmedValue = value.trim().toUpperCase();
        
        if (trimmedValue.length() != 18) {
            throw new IllegalArgumentException("CURP debe tener exactamente 18 caracteres");
        }
        
        if (!CURP_PATTERN.matcher(trimmedValue).matches()) {
            throw new IllegalArgumentException("Formato de CURP inválido");
        }
        
        this.value = trimmedValue;
    }
    
    public String getValue() {
        return value;
    }
    
    /**
     * Obtiene el estado de nacimiento del CURP
     */
    public String getEstadoNacimiento() {
        return value.substring(11, 13);
    }
    
    /**
     * Obtiene el año de nacimiento del CURP
     */
    public String getAnioNacimiento() {
        return value.substring(4, 6);
    }
    
    /**
     * Obtiene el mes de nacimiento del CURP
     */
    public String getMesNacimiento() {
        return value.substring(6, 8);
    }
    
    /**
     * Obtiene el día de nacimiento del CURP
     */
    public String getDiaNacimiento() {
        return value.substring(8, 10);
    }
    
    /**
     * Obtiene el género del CURP
     */
    public char getGenero() {
        return value.charAt(10);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curp curp = (Curp) o;
        return Objects.equals(value, curp.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    @Override
    public String toString() {
        return value;
    }
}

