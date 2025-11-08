package com.icheha.aprendia_api.users.cell.data.repositories.impl;

import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import com.icheha.aprendia_api.users.person.data.repositories.PersonaRepository;
import com.icheha.aprendia_api.users.cell.data.entities.CellEntity;
import com.icheha.aprendia_api.users.cell.data.entities.TeacherCellEntity;
import com.icheha.aprendia_api.users.cell.data.mappers.TeacherCellMapper;
import com.icheha.aprendia_api.users.cell.data.repositories.CellRepository;
import com.icheha.aprendia_api.users.cell.data.repositories.TeacherCellRepository;
import com.icheha.aprendia_api.users.cell.domain.entities.TeacherCell;
import com.icheha.aprendia_api.users.cell.domain.repositories.ITeacherCellRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherCellRepositoryImpl implements ITeacherCellRepository {
    
    private final TeacherCellRepository teacherCellRepository;
    private final CellRepository cellRepository;
    private final PersonaRepository personaRepository;
    private final TeacherCellMapper teacherCellMapper;
    
    public TeacherCellRepositoryImpl(@Lazy TeacherCellRepository teacherCellRepository,
                                    @Lazy CellRepository cellRepository,
                                    @Lazy @org.springframework.beans.factory.annotation.Qualifier("userPersonaRepository") PersonaRepository personaRepository,
                                    TeacherCellMapper teacherCellMapper) {
        this.teacherCellRepository = teacherCellRepository;
        this.cellRepository = cellRepository;
        this.personaRepository = personaRepository;
        this.teacherCellMapper = teacherCellMapper;
    }
    
    @Override
    public TeacherCell create(TeacherCell teacherCell, Long teacherId, Long cellId) {
        if (teacherCell == null) {
            throw new IllegalArgumentException("TeacherCell no puede ser nulo");
        }
        if (teacherId == null) {
            throw new IllegalArgumentException("ID del educador no puede ser nulo");
        }
        if (cellId == null) {
            throw new IllegalArgumentException("ID de la célula no puede ser nulo");
        }
        
        PersonaEntity teacher = personaRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Educador no encontrado con ID: " + teacherId));
        
        CellEntity cell = cellRepository.findById(cellId)
                .orElseThrow(() -> new IllegalArgumentException("Célula no encontrada con ID: " + cellId));
        
        TeacherCellEntity entity = teacherCellMapper.toEntity(teacherCell);
        entity.setTeacher(teacher);
        entity.setCell(cell);
        
        TeacherCellEntity savedEntity = teacherCellRepository.save(entity);
        return teacherCellMapper.toDomain(savedEntity);
    }
}

