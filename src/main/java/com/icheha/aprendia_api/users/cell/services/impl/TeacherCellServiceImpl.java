package com.icheha.aprendia_api.users.cell.services.impl;

import com.icheha.aprendia_api.users.cell.data.dtos.CreateTeacherCellDto;
import com.icheha.aprendia_api.users.cell.data.dtos.TeacherCellResponseDto;
import com.icheha.aprendia_api.users.cell.domain.entities.TeacherCell;
import com.icheha.aprendia_api.users.cell.domain.repositories.ITeacherCellRepository;
import com.icheha.aprendia_api.auth.data.repositories.UserRepository;
import com.icheha.aprendia_api.users.cell.services.ITeacherCellService;
import com.icheha.aprendia_api.users.role.services.IRolePersonService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherCellServiceImpl implements ITeacherCellService {
    
    private final ITeacherCellRepository teacherCellRepository;
    private final IRolePersonService rolePersonService;
    private final UserRepository userRepository;
    
    public TeacherCellServiceImpl(ITeacherCellRepository teacherCellRepository,
                                 IRolePersonService rolePersonService,
                                 UserRepository userRepository) {
        this.teacherCellRepository = teacherCellRepository;
        this.rolePersonService = rolePersonService;
        this.userRepository = userRepository;
    }
    
    @Override
    @Transactional
    public TeacherCellResponseDto create(CreateTeacherCellDto createTeacherCellDto) {
        // Validar que la persona tenga el rol de educador (rol ID 1)
        Long userId = userRepository.findByIdPersona(createTeacherCellDto.getTeacherId())
                .map(u -> u.getIdUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Usuario no encontrado para la persona con ID: " + createTeacherCellDto.getTeacherId()));
        var teacherRoles = rolePersonService.findByUserId(userId);
        boolean hasTeacherRole = teacherRoles.stream()
                .anyMatch(rp -> rp.getRoleId() != null && rp.getRoleId().equals(1L));
        
        if (!hasTeacherRole) {
            throw new IllegalArgumentException("La persona seleccionada como educador no cuenta con el rol de educador (rol ID 1).");
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

