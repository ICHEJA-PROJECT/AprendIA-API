package com.icheha.aprendia_api.auth.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio Rol
 * Define los roles disponibles en el sistema
 * Contiene la lógica de negocio relacionada con roles
 */
public class Rol {
    
    private final Long idRol;
    private final String nombre;
    
    // Constructor privado para usar Builder
    private Rol(Builder builder) {
        this.idRol = builder.idRol;
        this.nombre = builder.nombre;
    }
    
    // Getters
    public Long getIdRol() { return idRol; }
    public String getNombre() { return nombre; }
    
    /**
     * Verifica si el rol es de administrador
     */
    public boolean esAdministrador() {
        return "ADMIN".equalsIgnoreCase(nombre);
    }
    
    /**
     * Verifica si el rol es de estudiante
     */
    public boolean esEstudiante() {
        return "ESTUDIANTE".equalsIgnoreCase(nombre);
    }
    
    /**
     * Verifica si el rol es de profesor
     */
    public boolean esProfesor() {
        return "PROFESOR".equalsIgnoreCase(nombre);
    }
    
    /**
     * Verifica si el rol tiene permisos de escritura
     */
    public boolean tienePermisosEscritura() {
        return esAdministrador() || esProfesor();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;
        return Objects.equals(idRol, rol.idRol) &&
               Objects.equals(nombre, rol.nombre);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idRol, nombre);
    }
    
    @Override
    public String toString() {
        return "Rol{" +
                "idRol=" + idRol +
                ", nombre='" + nombre + '\'' +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long idRol;
        private String nombre;
        
        public Builder idRol(Long idRol) {
            this.idRol = idRol;
            return this;
        }
        
        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }
        
        public Rol build() {
            return new Rol(this);
        }
    }
}

