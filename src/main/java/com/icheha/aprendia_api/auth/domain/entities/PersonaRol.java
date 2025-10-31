package com.icheha.aprendia_api.auth.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio PersonaRol
 * Representa la relación entre una persona y un rol
 * Tabla de relación Many-to-Many
 */
public class PersonaRol {
    
    private final Long idPersonaRol;
    private final Long idPersona;
    private final Long idRol;
    private final Persona persona;
    private final Rol rol;
    
    // Constructor privado para usar Builder
    private PersonaRol(Builder builder) {
        this.idPersonaRol = builder.idPersonaRol;
        this.idPersona = builder.idPersona;
        this.idRol = builder.idRol;
        this.persona = builder.persona;
        this.rol = builder.rol;
    }
    
    // Getters
    public Long getIdPersonaRol() { return idPersonaRol; }
    public Long getIdPersona() { return idPersona; }
    public Long getIdRol() { return idRol; }
    public Persona getPersona() { return persona; }
    public Rol getRol() { return rol; }
    
    /**
     * Verifica si la relación es válida
     */
    public boolean esValida() {
        return idPersona != null && idRol != null;
    }
    
    /**
     * Verifica si la persona tiene permisos de administrador
     */
    public boolean esAdministrador() {
        return rol != null && rol.esAdministrador();
    }
    
    /**
     * Verifica si la persona tiene permisos de escritura
     */
    public boolean tienePermisosEscritura() {
        return rol != null && rol.tienePermisosEscritura();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaRol that = (PersonaRol) o;
        return Objects.equals(idPersonaRol, that.idPersonaRol) &&
               Objects.equals(idPersona, that.idPersona) &&
               Objects.equals(idRol, that.idRol);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idPersonaRol, idPersona, idRol);
    }
    
    @Override
    public String toString() {
        return "PersonaRol{" +
                "idPersonaRol=" + idPersonaRol +
                ", idPersona=" + idPersona +
                ", idRol=" + idRol +
                ", rol=" + (rol != null ? rol.getNombre() : "null") +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long idPersonaRol;
        private Long idPersona;
        private Long idRol;
        private Persona persona;
        private Rol rol;
        
        public Builder idPersonaRol(Long idPersonaRol) {
            this.idPersonaRol = idPersonaRol;
            return this;
        }
        
        public Builder idPersona(Long idPersona) {
            this.idPersona = idPersona;
            return this;
        }
        
        public Builder idRol(Long idRol) {
            this.idRol = idRol;
            return this;
        }
        
        public Builder persona(Persona persona) {
            this.persona = persona;
            return this;
        }
        
        public Builder rol(Rol rol) {
            this.rol = rol;
            return this;
        }
        
        public PersonaRol build() {
            return new PersonaRol(this);
        }
    }
}

