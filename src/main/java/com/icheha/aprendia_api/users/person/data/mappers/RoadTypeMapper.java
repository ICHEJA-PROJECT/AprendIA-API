package com.icheha.aprendia_api.users.person.data.mappers;

import com.icheha.aprendia_api.users.person.data.entities.RoadTypeEntity;
import com.icheha.aprendia_api.users.person.domain.entities.RoadType;
import org.springframework.stereotype.Component;

@Component
public class RoadTypeMapper {
    
    public RoadType toDomain(RoadTypeEntity entity) {
        if (entity == null) return null;
        
        return new RoadType.Builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }
    
    public RoadTypeEntity toEntity(RoadType domain) {
        if (domain == null) return null;
        
        RoadTypeEntity entity = new RoadTypeEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        return entity;
    }
}

