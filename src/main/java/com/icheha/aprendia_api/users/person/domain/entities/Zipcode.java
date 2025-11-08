package com.icheha.aprendia_api.users.person.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio Zipcode (Código Postal)
 * Representa un código postal
 */
public class Zipcode {
    
    private final Long id;
    private final String codigo;
    
    private Zipcode(Builder builder) {
        this.id = builder.id;
        this.codigo = builder.codigo;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zipcode zipcode = (Zipcode) o;
        return Objects.equals(id, zipcode.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Zipcode{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long id;
        private String codigo;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }
        
        public Zipcode build() {
            return new Zipcode(this);
        }
    }
}

