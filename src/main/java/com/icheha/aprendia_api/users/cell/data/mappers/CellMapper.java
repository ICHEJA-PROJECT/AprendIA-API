package com.icheha.aprendia_api.users.cell.data.mappers;

import com.icheha.aprendia_api.users.person.data.mappers.PersonaMapper;
import com.icheha.aprendia_api.users.cell.data.entities.CellEntity;
import com.icheha.aprendia_api.users.cell.domain.entities.Cell;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CellMapper {
    
    private final InstitutionMapper institutionMapper;
    private final PersonaMapper personaMapper;
    private final TeacherCellMapper teacherCellMapper;
    
    public CellMapper(InstitutionMapper institutionMapper, 
                     @Qualifier("userPersonaMapper") PersonaMapper personaMapper,
                     @Lazy TeacherCellMapper teacherCellMapper) {
        this.institutionMapper = institutionMapper;
        this.personaMapper = personaMapper;
        this.teacherCellMapper = teacherCellMapper;
    }
    
    public Cell toDomain(CellEntity entity) {
        if (entity == null) return null;
        
        return new Cell.Builder()
                .id(entity.getId())
                .institution(entity.getInstitution() != null ? 
                        institutionMapper.toDomain(entity.getInstitution()) : null)
                .coordinator(entity.getCoordinator() != null ? 
                        personaMapper.toDomain(entity.getCoordinator()) : null)
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .teachers(entity.getTeachers() != null ? 
                        entity.getTeachers().stream()
                                .map(teacherCellMapper::toDomain)
                                .collect(Collectors.toList()) : null)
                .build();
    }
    
    public Cell toDomainWithoutInstitution(CellEntity entity) {
        if (entity == null) return null;
        
        return new Cell.Builder()
                .id(entity.getId())
                .coordinator(entity.getCoordinator() != null ? 
                        personaMapper.toDomain(entity.getCoordinator()) : null)
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .teachers(entity.getTeachers() != null ? 
                        entity.getTeachers().stream()
                                .map(teacherCellMapper::toDomain)
                                .collect(Collectors.toList()) : null)
                .build();
    }
    
    public CellEntity toEntity(Cell domain) {
        if (domain == null) return null;
        
        CellEntity entity = new CellEntity();
        entity.setId(domain.getId());
        entity.setStartDate(domain.getStartDate());
        entity.setEndDate(domain.getEndDate());
        return entity;
    }
}

