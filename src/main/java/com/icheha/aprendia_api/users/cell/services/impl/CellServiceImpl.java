package com.icheha.aprendia_api.users.cell.services.impl;

import com.icheha.aprendia_api.users.cell.data.dtos.CellResponseDto;
import com.icheha.aprendia_api.users.cell.data.dtos.CreateCellDto;
import com.icheha.aprendia_api.users.cell.domain.entities.Cell;
import com.icheha.aprendia_api.users.cell.domain.repositories.ICellRepository;
import com.icheha.aprendia_api.users.cell.services.ICellService;
import com.icheha.aprendia_api.users.person.domain.repositories.IPersonaRepository;
import com.icheha.aprendia_api.users.role.services.IRolePersonService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CellServiceImpl implements ICellService {
    
    private final ICellRepository cellRepository;
    private final IPersonaRepository personaRepository;
    private final IRolePersonService rolePersonService;
    
    public CellServiceImpl(ICellRepository cellRepository,
                         @Qualifier("userPersonaRepositoryImpl") IPersonaRepository personaRepository,
                         IRolePersonService rolePersonService) {
        this.cellRepository = cellRepository;
        this.personaRepository = personaRepository;
        this.rolePersonService = rolePersonService;
    }
    
    @Override
    @Transactional
    public CellResponseDto create(CreateCellDto createCellDto) {
        // Validar que el coordinador existe
        personaRepository.findById(createCellDto.getCoordinatorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Coordinador no encontrado con ID: " + createCellDto.getCoordinatorId()));
        
        // Validar que el coordinador tiene el rol de coordinador (rol ID 3)
        var coordinatorRoles = rolePersonService.findByPersonId(createCellDto.getCoordinatorId());
        boolean hasCoordinatorRole = coordinatorRoles.stream()
                .anyMatch(role -> role.getRoleId() != null && role.getRoleId() == 3L);
        
        if (!hasCoordinatorRole) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, 
                    "La persona seleccionada como coordinador no cuenta con ese rol");
        }
        
        // Crear la célula
        Cell cell = new Cell.Builder()
                .startDate(createCellDto.getStartDate())
                .endDate(createCellDto.getEndDate())
                .build();
        
        Cell saved = cellRepository.create(
                cell,
                createCellDto.getInstitutionId(),
                createCellDto.getCoordinatorId());
        
        return toResponseDto(saved);
    }
    
    @Override
    public List<CellResponseDto> findAll() {
        return cellRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CellResponseDto> findByInstitution(Long institutionId) {
        return cellRepository.findByInstitution(institutionId).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CellResponseDto> findByCoordinator(Long coordinatorId) {
        return cellRepository.findByCoordinator(coordinatorId).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<CellResponseDto> findById(Long id) {
        return cellRepository.findById(id)
                .map(this::toResponseDto);
    }
    
    private CellResponseDto toResponseDto(Cell cell) {
        CellResponseDto dto = new CellResponseDto();
        dto.setId(cell.getId());
        dto.setStartDate(cell.getStartDate());
        dto.setEndDate(cell.getEndDate());
        
        if (cell.getInstitution() != null) {
            dto.setInstitutionId(cell.getInstitution().getId());
            dto.setInstitutionName(cell.getInstitution().getName());
        }
        
        if (cell.getCoordinator() != null) {
            dto.setCoordinatorId(cell.getCoordinator().getIdPersona());
            dto.setCoordinatorName(cell.getCoordinator().getPrimerNombre() + " " + 
                    cell.getCoordinator().getApellidoPaterno());
        }
        
        return dto;
    }
}

