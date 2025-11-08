package com.icheha.aprendia_api.users.person.data.mappers;

import com.icheha.aprendia_api.users.person.data.entities.MunicipalityEntity;
import com.icheha.aprendia_api.users.person.domain.entities.Municipality;
import com.icheha.aprendia_api.users.person.domain.entities.State;
import org.springframework.stereotype.Component;

@Component
public class MunicipalityMapper {
    
    private final StateMapper stateMapper;
    
    public MunicipalityMapper(StateMapper stateMapper) {
        this.stateMapper = stateMapper;
    }
    
    // Método helper para evitar dependencia circular en TownMapper
    public Municipality toDomainWithoutState(MunicipalityEntity entity) {
        if (entity == null) return null;
        
        return new Municipality.Builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .estado(null) // Se establecerá desde fuera
                .build();
    }
    
    public Municipality toDomain(MunicipalityEntity entity) {
        if (entity == null) return null;
        
        // Cargar estado de forma segura evitando ciclos
        State estado = null;
        try {
            if (entity.getEstado() != null) {
                // Solo acceder a campos básicos sin inicializar relaciones bidireccionales
                com.icheha.aprendia_api.users.person.data.entities.StateEntity estadoEntity = entity.getEstado();
                estado = new State.Builder()
                        .id(estadoEntity.getId())
                        .nombre(estadoEntity.getNombre())
                        .build();
            }
        } catch (org.hibernate.LazyInitializationException e) {
            estado = null;
        } catch (Exception e) {
            estado = null;
        }
        
        return new Municipality.Builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .estado(estado)
                .build();
    }
    
    public MunicipalityEntity toEntity(Municipality domain) {
        if (domain == null) return null;
        
        MunicipalityEntity entity = new MunicipalityEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setEstado(stateMapper.toEntity(domain.getEstado()));
        return entity;
    }
}

