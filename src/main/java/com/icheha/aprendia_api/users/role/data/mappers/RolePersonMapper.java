package com.icheha.aprendia_api.users.role.data.mappers;

import com.icheha.aprendia_api.auth.data.entities.PersonaRolEntity;
import com.icheha.aprendia_api.auth.data.mappers.PersonaRolMapper;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import com.icheha.aprendia_api.users.role.data.dtos.RolePersonResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RolePersonMapper {
    
    private final PersonaRolMapper personaRolMapper;
    
    public RolePersonMapper(PersonaRolMapper personaRolMapper) {
        this.personaRolMapper = personaRolMapper;
    }
    
    public RolePersonResponseDto toResponseDto(PersonaRol personaRol) {
        if (personaRol == null) return null;
        
        RolePersonResponseDto dto = new RolePersonResponseDto();
        dto.setId(personaRol.getIdPersonaRol());
        dto.setPersonId(personaRol.getIdPersona());
        dto.setRoleId(personaRol.getIdRol());
        dto.setRoleName(personaRol.getRol() != null ? personaRol.getRol().getNombre() : null);
        return dto;
    }
    
    public PersonaRol toDomain(PersonaRolEntity entity) {
        return personaRolMapper.toDomain(entity);
    }
    
    public PersonaRolEntity toEntity(PersonaRol domain) {
        return personaRolMapper.toEntity(domain);
    }
}

