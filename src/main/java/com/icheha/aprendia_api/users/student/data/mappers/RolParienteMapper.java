package com.icheha.aprendia_api.users.student.data.mappers;

import com.icheha.aprendia_api.users.student.data.entities.RolParienteEntity;
import com.icheha.aprendia_api.users.student.domain.entities.RolPariente;
import org.springframework.stereotype.Component;

@Component
public class RolParienteMapper {
    
    public RolPariente toDomain(RolParienteEntity entity) {
        if (entity == null) return null;
        
        return new RolPariente.Builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }
    
    public RolParienteEntity toEntity(RolPariente domain) {
        if (domain == null) return null;
        
        RolParienteEntity entity = new RolParienteEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        return entity;
    }
}

