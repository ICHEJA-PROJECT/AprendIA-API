package com.icheha.aprendia_api.auth.data.mappers;

import com.icheha.aprendia_api.auth.data.entities.PersonaRolEntity;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import org.springframework.stereotype.Component;

@Component
public class PersonaRolMapper {
    
    public PersonaRol toDomain(PersonaRolEntity entity) {
        if (entity == null) return null;
        
        return new PersonaRol.Builder()
                .idPersonaRol(entity.getIdPersonaRol())
                .idPersona(entity.getIdPersona())
                .idRol(entity.getIdRol())
                .build();
    }
    
    public PersonaRolEntity toEntity(PersonaRol domain) {
        if (domain == null) return null;
        
        PersonaRolEntity entity = new PersonaRolEntity();
        entity.setIdPersonaRol(domain.getIdPersonaRol());
        entity.setIdPersona(domain.getIdPersona());
        entity.setIdRol(domain.getIdRol());
        // Note: persona and rol relationships should be set separately
        return entity;
    }
}
