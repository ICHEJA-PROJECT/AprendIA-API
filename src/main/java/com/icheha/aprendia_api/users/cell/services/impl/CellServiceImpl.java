package com.icheha.aprendia_api.users.cell.services.impl;

import com.icheha.aprendia_api.users.cell.data.dtos.CellResponseDto;
import com.icheha.aprendia_api.users.cell.data.dtos.CreateCellDto;
import com.icheha.aprendia_api.users.cell.data.dtos.UpdateCellDto;
import com.icheha.aprendia_api.users.cell.domain.entities.Cell;
import com.icheha.aprendia_api.users.cell.domain.repositories.ICellRepository;
import com.icheha.aprendia_api.users.cell.domain.repositories.IInstitutionRepository;
import com.icheha.aprendia_api.users.cell.services.ICellService;
import com.icheha.aprendia_api.auth.data.repositories.UserRepository;
import com.icheha.aprendia_api.users.person.domain.repositories.IPersonaRepository;
import com.icheha.aprendia_api.users.role.services.IRolePersonService;
import jakarta.persistence.EntityNotFoundException;
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
    private final IInstitutionRepository institutionRepository;
    private final IPersonaRepository personaRepository;
    private final IRolePersonService rolePersonService;
    private final UserRepository userRepository;
    
    public CellServiceImpl(ICellRepository cellRepository,
                         IInstitutionRepository institutionRepository,
                         @Qualifier("userPersonaRepositoryImpl") IPersonaRepository personaRepository,
                         IRolePersonService rolePersonService,
                         UserRepository userRepository) {
        this.cellRepository = cellRepository;
        this.institutionRepository = institutionRepository;
        this.personaRepository = personaRepository;
        this.rolePersonService = rolePersonService;
        this.userRepository = userRepository;
    }
    
    @Override
    @Transactional
    public CellResponseDto create(CreateCellDto createCellDto) {
        // Validar que el coordinador existe
        personaRepository.findById(createCellDto.getCoordinatorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Coordinador no encontrado con ID: " + createCellDto.getCoordinatorId()));
        
        // Validar que el coordinador tiene el rol de coordinador (rol ID 3)
        Long userId = userRepository.findByIdPersona(createCellDto.getCoordinatorId())
                .map(u -> u.getIdUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Usuario no encontrado para la persona con ID: " + createCellDto.getCoordinatorId()));
        var coordinatorRoles = rolePersonService.findByUserId(userId);
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
    @Transactional(readOnly = true)
    public Optional<CellResponseDto> findById(Long id) {
        return cellRepository.findById(id)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional
    public CellResponseDto update(Long id, UpdateCellDto updateCellDto) {
        Cell cell = cellRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Célula no encontrada con ID: " + id));
        
        // Validar coordinador si se está actualizando
        if (updateCellDto.getCoordinatorId() != null) {
            personaRepository.findById(updateCellDto.getCoordinatorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                            "Coordinador no encontrado con ID: " + updateCellDto.getCoordinatorId()));
            
            Long userId = userRepository.findByIdPersona(updateCellDto.getCoordinatorId())
                    .map(u -> u.getIdUser())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                            "Usuario no encontrado para la persona con ID: " + updateCellDto.getCoordinatorId()));
            var coordinatorRoles = rolePersonService.findByUserId(userId);
            boolean hasCoordinatorRole = coordinatorRoles.stream()
                    .anyMatch(role -> role.getRoleId() != null && role.getRoleId() == 3L);
            
            if (!hasCoordinatorRole) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, 
                        "La persona seleccionada como coordinador no cuenta con ese rol");
            }
        }
        
        Cell.Builder builder = new Cell.Builder()
                .id(cell.getId())
                .institution(updateCellDto.getInstitutionId() != null ? 
                        institutionRepository.findById(updateCellDto.getInstitutionId())
                                .orElse(cell.getInstitution()) : cell.getInstitution())
                .coordinator(updateCellDto.getCoordinatorId() != null ?
                        personaRepository.findById(updateCellDto.getCoordinatorId())
                                .orElse(cell.getCoordinator()) : cell.getCoordinator())
                .startDate(updateCellDto.getStartDate() != null ? updateCellDto.getStartDate() : cell.getStartDate())
                .endDate(updateCellDto.getEndDate() != null ? updateCellDto.getEndDate() : cell.getEndDate())
                .teachers(cell.getTeachers());
        
        Cell updated = cellRepository.update(
                builder.build(),
                updateCellDto.getInstitutionId() != null ? updateCellDto.getInstitutionId() : cell.getInstitution().getId(),
                updateCellDto.getCoordinatorId() != null ? updateCellDto.getCoordinatorId() : cell.getCoordinator().getIdPersona());
        
        return toResponseDto(updated);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        cellRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Célula no encontrada con ID: " + id));
        cellRepository.delete(id);
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

