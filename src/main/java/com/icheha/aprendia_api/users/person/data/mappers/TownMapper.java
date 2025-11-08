package com.icheha.aprendia_api.users.person.data.mappers;

import com.icheha.aprendia_api.users.person.data.entities.TownEntity;
import com.icheha.aprendia_api.users.person.domain.entities.Town;
import com.icheha.aprendia_api.users.person.domain.entities.Municipality;
import org.springframework.stereotype.Component;

@Component
public class TownMapper {
    
    private final MunicipalityMapper municipalityMapper;
    
    public TownMapper(MunicipalityMapper municipalityMapper) {
        this.municipalityMapper = municipalityMapper;
    }
    
    // Método helper para evitar dependencia circular en MunicipalityMapper
    public Town toDomainWithoutMunicipality(TownEntity entity) {
        if (entity == null) return null;
        
        return new Town.Builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .municipio(null) // Se establecerá desde fuera
                .build();
    }
    
    public Town toDomain(TownEntity entity) {
        if (entity == null) return null;
        
        return new Town.Builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .municipio(municipalityMapper.toDomain(entity.getMunicipio()))
                .build();
    }
    
    public TownEntity toEntity(Town domain) {
        if (domain == null) return null;
        
        TownEntity entity = new TownEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setMunicipio(municipalityMapper.toEntity(domain.getMunicipio()));
        return entity;
    }
}

