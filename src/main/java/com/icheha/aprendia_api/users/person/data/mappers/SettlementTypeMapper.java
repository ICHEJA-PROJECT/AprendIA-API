package com.icheha.aprendia_api.users.person.data.mappers;

import com.icheha.aprendia_api.users.person.data.entities.SettlementTypeEntity;
import com.icheha.aprendia_api.users.person.domain.entities.SettlementType;
import org.springframework.stereotype.Component;

@Component
public class SettlementTypeMapper {
    
    public SettlementType toDomain(SettlementTypeEntity entity) {
        if (entity == null) return null;
        
        return new SettlementType.Builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }
    
    public SettlementTypeEntity toEntity(SettlementType domain) {
        if (domain == null) return null;
        
        SettlementTypeEntity entity = new SettlementTypeEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        return entity;
    }
}

