package com.icheha.aprendia_api.users.person.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio Municipality (Municipio)
 * Representa un municipio de un estado
 */
public class Municipality {
    
    private final Long id;
    private final String nombre;
    private final State estado;
    
    private Municipality(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.estado = builder.estado;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public State getEstado() {
        return estado;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Municipality that = (Municipality) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Municipality{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", estado=" + (estado != null ? estado.getNombre() : null) +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long id;
        private String nombre;
        private State estado;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }
        
        public Builder estado(State estado) {
            this.estado = estado;
            return this;
        }
        
        public Municipality build() {
            return new Municipality(this);
        }
    }
}

