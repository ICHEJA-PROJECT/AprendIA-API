package com.icheha.aprendia_api.users.role.data.mappers;

import com.icheha.aprendia_api.auth.data.entities.UserRolEntity;
import com.icheha.aprendia_api.auth.data.mappers.UserRolMapper;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import com.icheha.aprendia_api.users.role.data.dtos.RolePersonResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RolePersonMapper {
    
    private final UserRolMapper userRolMapper;
    
    public RolePersonMapper(UserRolMapper userRolMapper) {
        this.userRolMapper = userRolMapper;
    }
    
    public RolePersonResponseDto toResponseDto(PersonaRol personaRol) {
        if (personaRol == null) return null;
        
        RolePersonResponseDto dto = new RolePersonResponseDto();
        dto.setId(personaRol.getIdPersonaRol());
        dto.setPersonId(personaRol.getIdPersona()); // Este es el userId ahora
        dto.setRoleId(personaRol.getIdRol());
        dto.setRoleName(personaRol.getRol() != null ? personaRol.getRol().getNombre() : null);
        return dto;
    }
    
    public PersonaRol toDomain(UserRolEntity entity) {
        return userRolMapper.toDomain(entity);
    }
    
    public UserRolEntity toEntity(PersonaRol domain) {
        return userRolMapper.toEntity(domain);
    }
}

