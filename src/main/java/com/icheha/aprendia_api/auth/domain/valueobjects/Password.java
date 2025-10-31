package com.icheha.aprendia_api.auth.domain.valueobjects;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

/**
 * Value Object para Password
 * Encapsula la lógica relacionada con contraseñas
 */
public class Password {
    
    private final String hashedValue;
    private final PasswordEncoder passwordEncoder;
    
    public Password(String hashedValue, PasswordEncoder passwordEncoder) {
        if (hashedValue == null || hashedValue.trim().isEmpty()) {
            throw new IllegalArgumentException("Password no puede ser nulo o vacío");
        }
        if (passwordEncoder == null) {
            throw new IllegalArgumentException("PasswordEncoder no puede ser nulo");
        }
        
        this.hashedValue = hashedValue;
        this.passwordEncoder = passwordEncoder;
    }
    
    /**
     * Constructor para crear Password desde valor ya hasheado (sin PasswordEncoder)
     * Útil para mappers y casos donde no necesitamos verificar contraseñas
     */
    public Password(String hashedValue) {
        if (hashedValue == null || hashedValue.trim().isEmpty()) {
            throw new IllegalArgumentException("Password no puede ser nulo o vacío");
        }
        
        this.hashedValue = hashedValue;
        this.passwordEncoder = null; // No disponible para verificación
    }
    
    public static Password fromPlainText(String plainPassword, PasswordEncoder passwordEncoder) {
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Password no puede ser nulo o vacío");
        }
        if (plainPassword.length() < 6) {
            throw new IllegalArgumentException("Password debe tener al menos 6 caracteres");
        }
        if (passwordEncoder == null) {
            throw new IllegalArgumentException("PasswordEncoder no puede ser nulo");
        }
        
        String hashedValue = passwordEncoder.encode(plainPassword);
        return new Password(hashedValue, passwordEncoder);
    }
    
    public String getHashedValue() {
        return hashedValue;
    }
    
    /**
     * Verifica si la contraseña proporcionada coincide con la contraseña hasheada
     */
    public boolean verificar(String plainPassword) {
        if (plainPassword == null) {
            return false;
        }
        if (passwordEncoder == null) {
            throw new IllegalStateException("PasswordEncoder no está disponible para verificación");
        }
        return passwordEncoder.matches(plainPassword, hashedValue);
    }
    
    /**
     * Verifica si la contraseña es válida según las reglas de negocio
     */
    public static boolean esValida(String plainPassword) {
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            return false;
        }
        
        // Mínimo 6 caracteres
        if (plainPassword.length() < 6) {
            return false;
        }
        
        // Máximo 50 caracteres
        if (plainPassword.length() > 50) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(hashedValue, password.hashedValue);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(hashedValue);
    }
    
    @Override
    public String toString() {
        return "Password{hashedValue='[PROTECTED]'}";
    }
}

