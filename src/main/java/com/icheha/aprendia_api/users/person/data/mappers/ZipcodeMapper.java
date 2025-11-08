package com.icheha.aprendia_api.users.person.data.mappers;

import com.icheha.aprendia_api.users.person.data.entities.ZipcodeEntity;
import com.icheha.aprendia_api.users.person.domain.entities.Zipcode;
import org.springframework.stereotype.Component;

@Component
public class ZipcodeMapper {
    
    public Zipcode toDomain(ZipcodeEntity entity) {
        if (entity == null) return null;
        
        return new Zipcode.Builder()
                .id(entity.getId())
                .codigo(entity.getCodigo())
                .build();
    }
    
    public ZipcodeEntity toEntity(Zipcode domain) {
        if (domain == null) return null;
        
        ZipcodeEntity entity = new ZipcodeEntity();
        entity.setId(domain.getId());
        entity.setCodigo(domain.getCodigo());
        return entity;
    }
}

