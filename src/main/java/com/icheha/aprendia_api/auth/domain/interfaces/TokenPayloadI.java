package com.icheha.aprendia_api.auth.domain.interfaces;

/**
 * Interface de dominio para TokenPayload
 * Estructura del payload del token JWT
 */
public interface TokenPayloadI {
    
    Long getIdPersona();
    void setIdPersona(Long idPersona);
    
    String getNombre();
    void setNombre(String nombre);
    
    String getRoleName();
    void setRoleName(String roleName);
    
    String getDisabilityName();
    void setDisabilityName(String disabilityName);
    
    Long getDisabilityId();
    void setDisabilityId(Long disabilityId);
    
    Long getLearningPathId();
    void setLearningPathId(Long learningPathId);
    
    Long getIat();
    void setIat(Long iat);
    
    Long getExp();
    void setExp(Long exp);
}
