package com.icheha.aprendia_api.users.student.data.mappers;

import com.icheha.aprendia_api.users.student.data.entities.ProgenitorEntity;
import com.icheha.aprendia_api.users.student.domain.entities.Progenitor;
import org.springframework.stereotype.Component;

@Component
public class ProgenitorMapper {
    
    public Progenitor toDomain(ProgenitorEntity entity) {
        if (entity == null) return null;
        
        return new Progenitor.Builder()
                .id(entity.getId())
                .curp(entity.getCurp())
                .primerNombre(entity.getPrimerNombre())
                .segundoNombre(entity.getSegundoNombre())
                .primerApellido(entity.getPrimerApellido())
                .segundoApellido(entity.getSegundoApellido())
                .build();
    }
    
    public ProgenitorEntity toEntity(Progenitor domain) {
        if (domain == null) return null;
        
        ProgenitorEntity entity = new ProgenitorEntity();
        entity.setId(domain.getId());
        entity.setCurp(domain.getCurp());
        entity.setPrimerNombre(domain.getPrimerNombre());
        entity.setSegundoNombre(domain.getSegundoNombre());
        entity.setPrimerApellido(domain.getPrimerApellido());
        entity.setSegundoApellido(domain.getSegundoApellido());
        return entity;
    }
}

