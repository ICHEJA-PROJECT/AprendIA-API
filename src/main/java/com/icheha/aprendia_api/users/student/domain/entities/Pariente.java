package com.icheha.aprendia_api.users.student.domain.entities;

import com.icheha.aprendia_api.auth.domain.entities.Persona;

import java.util.Objects;

/**
 * Entidad de dominio Pariente
 * Representa la relación entre una persona (estudiante) y su pariente
 */
public class Pariente {
    
    private final Long id;
    private final Persona persona; // El estudiante
    private final Persona pariente; // El pariente (padre, madre, tutor, etc.)
    private final RolPariente rolPariente; // Tipo de relación
    
    private Pariente(Builder builder) {
        this.id = builder.id;
        this.persona = builder.persona;
        this.pariente = builder.pariente;
        this.rolPariente = builder.rolPariente;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public Persona getPersona() {
        return persona;
    }
    
    public Persona getPariente() {
        return pariente;
    }
    
    public RolPariente getRolPariente() {
        return rolPariente;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pariente pariente1 = (Pariente) o;
        return Objects.equals(id, pariente1.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Pariente{" +
                "id=" + id +
                ", persona=" + (persona != null ? persona.getCurp().getValue() : null) +
                ", pariente=" + (pariente != null ? pariente.getCurp().getValue() : null) +
                ", rolPariente=" + (rolPariente != null ? rolPariente.getNombre() : null) +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long id;
        private Persona persona;
        private Persona pariente;
        private RolPariente rolPariente;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder persona(Persona persona) {
            this.persona = persona;
            return this;
        }
        
        public Builder pariente(Persona pariente) {
            this.pariente = pariente;
            return this;
        }
        
        public Builder rolPariente(RolPariente rolPariente) {
            this.rolPariente = rolPariente;
            return this;
        }
        
        public Pariente build() {
            return new Pariente(this);
        }
    }
}

