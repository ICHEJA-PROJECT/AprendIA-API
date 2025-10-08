package com.icheha.aprendia_api.preferences.impairments.domain.entities;

public class Impairment {
    
    private final Long id;
    private final String nombre;
    private final String descripcion;
    
    private Impairment(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.descripcion = builder.descripcion;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public static class Builder {
        private Long id;
        private String nombre;
        private String descripcion;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }
        
        public Builder descripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }
        
        public Impairment build() {
            return new Impairment(this);
        }
    }
}
