package com.icheha.aprendia_api.users.cell.services.impl;

import com.icheha.aprendia_api.users.cell.data.dtos.CreateTeacherCellDto;
import com.icheha.aprendia_api.users.cell.data.dtos.TeacherCellResponseDto;
import com.icheha.aprendia_api.users.cell.domain.entities.TeacherCell;
import com.icheha.aprendia_api.users.cell.domain.repositories.ITeacherCellRepository;
import com.icheha.aprendia_api.users.cell.services.ITeacherCellService;
import com.icheha.aprendia_api.users.role.services.IRolePersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherCellServiceImpl implements ITeacherCellService {
    
    private final ITeacherCellRepository teacherCellRepository;
    private final IRolePersonService rolePersonService;
    
    public TeacherCellServiceImpl(ITeacherCellRepository teacherCellRepository,
                                 IRolePersonService rolePersonService) {
        this.teacherCellRepository = teacherCellRepository;
        this.rolePersonService = rolePersonService;
    }
    
    @Override
    @Transactional
    public TeacherCellResponseDto create(CreateTeacherCellDto createTeacherCellDto) {
        // Validar que la persona tenga el rol de educador (rol ID 2)
        var teacherRoles = rolePersonService.findByPersonId(createTeacherCellDto.getTeacherId());
        boolean hasTeacherRole = teacherRoles.stream()
                .anyMatch(rp -> rp.getRoleId() != null && rp.getRoleId().equals(2L));
        
        if (!hasTeacherRole) {
            throw new IllegalArgumentException("La persona seleccionada como educador no cuenta con el rol de educador (rol ID 2).");
        }
        
        TeacherCell teacherCell = new TeacherCell.Builder()
                .teacherId(createTeacherCellDto.getTeacherId())
                .cellId(createTeacherCellDto.getCellId())
                .build();
        
        TeacherCell saved = teacherCellRepository.create(
                teacherCell,
                createTeacherCellDto.getTeacherId(),
                createTeacherCellDto.getCellId());
        
        return toResponseDto(saved);
    }
    
    private TeacherCellResponseDto toResponseDto(TeacherCell teacherCell) {
        TeacherCellResponseDto dto = new TeacherCellResponseDto();
        dto.setTeacherId(teacherCell.getTeacherId());
        dto.setCellId(teacherCell.getCellId());
        
        if (teacherCell.getTeacher() != null) {
            dto.setTeacherName(teacherCell.getTeacher().getPrimerNombre() + " " + 
                    teacherCell.getTeacher().getApellidoPaterno());
        }
        
        return dto;
    }
}

