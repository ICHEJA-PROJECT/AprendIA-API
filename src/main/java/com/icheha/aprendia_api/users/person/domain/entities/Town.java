package com.icheha.aprendia_api.users.person.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio Town (Ciudad)
 * Representa una ciudad dentro de un municipio
 */
public class Town {
    
    private final Long id;
    private final String nombre;
    private final Municipality municipio;
    
    private Town(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.municipio = builder.municipio;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public Municipality getMunicipio() {
        return municipio;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Town town = (Town) o;
        return Objects.equals(id, town.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Town{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", municipio=" + (municipio != null ? municipio.getNombre() : null) +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long id;
        private String nombre;
        private Municipality municipio;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }
        
        public Builder municipio(Municipality municipio) {
            this.municipio = municipio;
            return this;
        }
        
        public Town build() {
            return new Town(this);
        }
    }
}

