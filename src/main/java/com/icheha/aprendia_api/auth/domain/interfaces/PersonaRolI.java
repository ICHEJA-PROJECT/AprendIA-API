package com.icheha.aprendia_api.auth.domain.interfaces;

/**
 * Interface de dominio para PersonaRol
 * Tabla de relación entre personas y roles (Many-to-Many)
 */
public interface PersonaRolI {
    
    Long getIdPersonaRol();
    void setIdPersonaRol(Long idPersonaRol);
    
    Long getIdPersona();
    void setIdPersona(Long idPersona);
    
    Long getIdRol();
    void setIdRol(Long idRol);
    
    PersonaI getPersona();
    void setPersona(PersonaI persona);
    
    RolI getRol();
    void setRol(RolI rol);
}
