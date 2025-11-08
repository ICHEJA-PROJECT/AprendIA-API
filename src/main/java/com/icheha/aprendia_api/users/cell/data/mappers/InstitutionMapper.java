package com.icheha.aprendia_api.users.cell.data.mappers;

import com.icheha.aprendia_api.users.cell.data.entities.InstitutionEntity;
import com.icheha.aprendia_api.users.cell.domain.entities.Institution;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class InstitutionMapper {
    
    private final CellMapper cellMapper;
    
    public InstitutionMapper(@Lazy CellMapper cellMapper) {
        this.cellMapper = cellMapper;
    }
    
    public Institution toDomain(InstitutionEntity entity) {
        if (entity == null) return null;
        
        return new Institution.Builder()
                .id(entity.getId())
                .rfc(entity.getRfc())
                .rct(entity.getRct())
                .name(entity.getName())
                .cells(entity.getCells() != null ? 
                        entity.getCells().stream()
                                .map(cellMapper::toDomainWithoutInstitution)
                                .collect(Collectors.toList()) : null)
                .build();
    }
    
    public InstitutionEntity toEntity(Institution domain) {
        if (domain == null) return null;
        
        InstitutionEntity entity = new InstitutionEntity();
        entity.setId(domain.getId());
        entity.setRfc(domain.getRfc());
        entity.setRct(domain.getRct());
        entity.setName(domain.getName());
        return entity;
    }
}

