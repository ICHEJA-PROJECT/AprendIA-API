package com.icheha.aprendia_api.users.student.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio Progenitor
 * Representa un progenitor (padre o madre) de un estudiante
 */
public class Progenitor {
    
    private final Long id;
    private final String curp;
    private final String primerNombre;
    private final String segundoNombre;
    private final String primerApellido;
    private final String segundoApellido;
    
    private Progenitor(Builder builder) {
        this.id = builder.id;
        this.curp = builder.curp;
        this.primerNombre = builder.primerNombre;
        this.segundoNombre = builder.segundoNombre;
        this.primerApellido = builder.primerApellido;
        this.segundoApellido = builder.segundoApellido;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public String getCurp() {
        return curp;
    }
    
    public String getPrimerNombre() {
        return primerNombre;
    }
    
    public String getSegundoNombre() {
        return segundoNombre;
    }
    
    public String getPrimerApellido() {
        return primerApellido;
    }
    
    public String getSegundoApellido() {
        return segundoApellido;
    }
    
    public String getNombreCompleto() {
        return primerNombre + " " + segundoNombre + " " + primerApellido + " " + segundoApellido;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Progenitor that = (Progenitor) o;
        return Objects.equals(id, that.id) && Objects.equals(curp, that.curp);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, curp);
    }
    
    @Override
    public String toString() {
        return "Progenitor{" +
                "id=" + id +
                ", curp='" + curp + '\'' +
                ", nombreCompleto='" + getNombreCompleto() + '\'' +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long id;
        private String curp;
        private String primerNombre;
        private String segundoNombre;
        private String primerApellido;
        private String segundoApellido;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder curp(String curp) {
            this.curp = curp;
            return this;
        }
        
        public Builder primerNombre(String primerNombre) {
            this.primerNombre = primerNombre;
            return this;
        }
        
        public Builder segundoNombre(String segundoNombre) {
            this.segundoNombre = segundoNombre;
            return this;
        }
        
        public Builder primerApellido(String primerApellido) {
            this.primerApellido = primerApellido;
            return this;
        }
        
        public Builder segundoApellido(String segundoApellido) {
            this.segundoApellido = segundoApellido;
            return this;
        }
        
        public Progenitor build() {
            return new Progenitor(this);
        }
    }
}

