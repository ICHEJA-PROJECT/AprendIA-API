package com.icheha.aprendia_api.users.person.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio SettlementType (Tipo de Asentamiento)
 * Representa el tipo de asentamiento (colonia, fraccionamiento, etc.)
 */
public class SettlementType {
    
    private final Long id;
    private final String nombre;
    
    private SettlementType(Builder builder) {
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
        SettlementType that = (SettlementType) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "SettlementType{" +
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
        
        public SettlementType build() {
            return new SettlementType(this);
        }
    }
}

