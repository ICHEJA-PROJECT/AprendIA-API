package com.icheha.aprendia_api.users.role.data.mappers;

import com.icheha.aprendia_api.auth.data.entities.RolEntity;
import com.icheha.aprendia_api.auth.domain.entities.Rol;
import com.icheha.aprendia_api.users.role.data.dtos.RoleResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    
    public RoleResponseDto toResponseDto(Rol rol) {
        if (rol == null) return null;
        
        RoleResponseDto dto = new RoleResponseDto();
        dto.setId(rol.getIdRol());
        dto.setName(rol.getNombre());
        // La descripción no está en la entidad de dominio Rol, se maneja directamente desde la entidad JPA
        return dto;
    }
    
    public RoleResponseDto toResponseDto(RolEntity entity) {
        if (entity == null) return null;
        
        RoleResponseDto dto = new RoleResponseDto();
        dto.setId(entity.getIdRol());
        dto.setName(entity.getNombre());
        dto.setDescription(entity.getDescripcion());
        return dto;
    }
    
    public Rol toDomain(RolEntity entity) {
        if (entity == null) return null;
        
        return new Rol.Builder()
                .idRol(entity.getIdRol())
                .nombre(entity.getNombre())
                .build();
    }
    
    public RolEntity toEntity(Rol domain) {
        if (domain == null) return null;
        
        RolEntity entity = new RolEntity();
        entity.setIdRol(domain.getIdRol());
        entity.setNombre(domain.getNombre());
        // La descripción se maneja por separado ya que no está en la entidad de dominio
        return entity;
    }
    
    public RolEntity toEntity(Rol domain, String descripcion) {
        if (domain == null) return null;
        
        RolEntity entity = new RolEntity();
        entity.setIdRol(domain.getIdRol());
        entity.setNombre(domain.getNombre());
        entity.setDescripcion(descripcion);
        return entity;
    }
}

