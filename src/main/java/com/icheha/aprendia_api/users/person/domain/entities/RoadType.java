package com.icheha.aprendia_api.users.person.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio RoadType (Tipo de Vialidad)
 * Representa el tipo de vialidad de una dirección
 */
public class RoadType {
    
    private final Long id;
    private final String nombre;
    
    private RoadType(Builder builder) {
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
        RoadType roadType = (RoadType) o;
        return Objects.equals(id, roadType.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "RoadType{" +
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
        
        public RoadType build() {
            return new RoadType(this);
        }
    }
}

