package com.icheha.aprendia_api.users.cell.data.mappers;

import com.icheha.aprendia_api.users.person.data.mappers.PersonaMapper;
import com.icheha.aprendia_api.users.cell.data.entities.TeacherCellEntity;
import com.icheha.aprendia_api.users.cell.domain.entities.TeacherCell;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class TeacherCellMapper {
    
    private final PersonaMapper personaMapper;
    private final CellMapper cellMapper;
    
    public TeacherCellMapper(@Qualifier("userPersonaMapper") PersonaMapper personaMapper, @Lazy CellMapper cellMapper) {
        this.personaMapper = personaMapper;
        this.cellMapper = cellMapper;
    }
    
    public TeacherCell toDomain(TeacherCellEntity entity) {
        if (entity == null) return null;
        
        return new TeacherCell.Builder()
                .teacherId(entity.getTeacherId())
                .cellId(entity.getCellId())
                .teacher(entity.getTeacher() != null ? 
                        personaMapper.toDomain(entity.getTeacher()) : null)
                .cell(entity.getCell() != null ? 
                        cellMapper.toDomainWithoutInstitution(entity.getCell()) : null)
                .build();
    }
    
    public TeacherCellEntity toEntity(TeacherCell domain) {
        if (domain == null) return null;
        
        TeacherCellEntity entity = new TeacherCellEntity();
        entity.setTeacherId(domain.getTeacherId());
        entity.setCellId(domain.getCellId());
        return entity;
    }
}

