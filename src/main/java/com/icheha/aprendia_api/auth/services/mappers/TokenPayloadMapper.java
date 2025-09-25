package com.icheha.aprendia_api.auth.services.mappers;

import com.icheha.aprendia_api.auth.data.dtos.response.TokenPayloadDto;
import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre entidades de dominio y DTOs de token
 */
@Component
public class TokenPayloadMapper {
    
    // Default values
    private static final String DEFAULT_DISABILITY_NAME = "Sin discapacidad";
    private static final Long DEFAULT_DISABILITY_ID = 0L;
    private static final Long DEFAULT_LEARNING_PATH_ID = 2L;
    
    /**
     * Convierte una Persona y PersonaRol a TokenPayloadDto
     */
    public TokenPayloadDto toDto(Persona persona, PersonaRol personaRol) {
        if (persona == null) {
            return null;
        }
        
        TokenPayloadDto dto = new TokenPayloadDto();
        dto.setIdPersona(persona.getIdPersona());
        dto.setNombre(persona.getNombreCompleto());
        
        if (personaRol != null && personaRol.getRol() != null) {
            dto.setRoleName(personaRol.getRol().getNombre());
        }
        
        // Valores por defecto - en el futuro se pueden obtener de un servicio externo
        dto.setDisabilityName(DEFAULT_DISABILITY_NAME);
        dto.setDisabilityId(DEFAULT_DISABILITY_ID);
        dto.setLearningPathId(DEFAULT_LEARNING_PATH_ID);
        
        return dto;
    }
    
    /**
     * Convierte una Persona a TokenPayloadDto usando su primer rol
     */
    public TokenPayloadDto toDto(Persona persona) {
        if (persona == null) {
            return null;
        }
        
        PersonaRol primerRol = null;
        if (persona.getPersonaRoles() != null && !persona.getPersonaRoles().isEmpty()) {
            primerRol = persona.getPersonaRoles().get(0);
        }
        
        return toDto(persona, primerRol);
    }
}

