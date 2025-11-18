package com.icheha.aprendia_api.users.student.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio RolPariente
 * Representa un rol de pariente (Padre, Madre, Tutor, etc.)
 */
public class RolPariente {
    
    private final Long id;
    private final String nombre;
    
    private RolPariente(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolPariente that = (RolPariente) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
    
    @Override
    public String toString() {
        return "RolPariente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long id;
        private String nombre;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }
        
        public RolPariente build() {
            return new RolPariente(this);
        }
    }
}

