package com.icheha.aprendia_api.users.person.data.mappers;

import com.icheha.aprendia_api.users.person.data.entities.StateEntity;
import com.icheha.aprendia_api.users.person.domain.entities.State;
import org.springframework.stereotype.Component;

@Component
public class StateMapper {
    
    public State toDomain(StateEntity entity) {
        if (entity == null) return null;
        
        return new State.Builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }
    
    public StateEntity toEntity(State domain) {
        if (domain == null) return null;
        
        StateEntity entity = new StateEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        return entity;
    }
}

