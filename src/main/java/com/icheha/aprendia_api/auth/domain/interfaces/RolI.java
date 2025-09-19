package com.icheha.aprendia_api.auth.domain.interfaces;

import java.util.List;

/**
 * Interface de dominio para Rol
 * Define los roles disponibles en el sistema
 */
public interface RolI {
    
    Long getIdRol();
    void setIdRol(Long idRol);
    
    String getNombre();
    void setNombre(String nombre);
    
    String getDescripcion();
    void setDescripcion(String descripcion);
    
    List<? extends PersonaRolI> getPersonaRoles();
    void setPersonaRoles(List<? extends PersonaRolI> personaRoles);
}
