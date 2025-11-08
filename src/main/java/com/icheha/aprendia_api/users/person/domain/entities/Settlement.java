package com.icheha.aprendia_api.users.person.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio Settlement (Asentamiento)
 * Representa un asentamiento (colonia, fraccionamiento, etc.)
 */
public class Settlement {
    
    private final Long id;
    private final String nombre;
    private final Zipcode codigoPostal;
    private final SettlementType tipoAsentamiento;
    private final Municipality municipio;
    private final Town ciudad;
    
    private Settlement(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.codigoPostal = builder.codigoPostal;
        this.tipoAsentamiento = builder.tipoAsentamiento;
        this.municipio = builder.municipio;
        this.ciudad = builder.ciudad;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public Zipcode getCodigoPostal() {
        return codigoPostal;
    }
    
    public SettlementType getTipoAsentamiento() {
        return tipoAsentamiento;
    }
    
    public Municipality getMunicipio() {
        return municipio;
    }
    
    public Town getCiudad() {
        return ciudad;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Settlement that = (Settlement) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Settlement{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", codigoPostal=" + (codigoPostal != null ? codigoPostal.getCodigo() : null) +
                ", tipoAsentamiento=" + (tipoAsentamiento != null ? tipoAsentamiento.getNombre() : null) +
                ", municipio=" + (municipio != null ? municipio.getNombre() : null) +
                ", ciudad=" + (ciudad != null ? ciudad.getNombre() : null) +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long id;
        private String nombre;
        private Zipcode codigoPostal;
        private SettlementType tipoAsentamiento;
        private Municipality municipio;
        private Town ciudad;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }
        
        public Builder codigoPostal(Zipcode codigoPostal) {
            this.codigoPostal = codigoPostal;
            return this;
        }
        
        public Builder tipoAsentamiento(SettlementType tipoAsentamiento) {
            this.tipoAsentamiento = tipoAsentamiento;
            return this;
        }
        
        public Builder municipio(Municipality municipio) {
            this.municipio = municipio;
            return this;
        }
        
        public Builder ciudad(Town ciudad) {
            this.ciudad = ciudad;
            return this;
        }
        
        public Settlement build() {
            return new Settlement(this);
        }
    }
}

