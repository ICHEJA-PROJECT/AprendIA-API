package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateUnitDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateUnitDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.UnitResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.UnitEntity;
import com.icheha.aprendia_api.exercises.topics.data.repositories.UnitRepository;
import com.icheha.aprendia_api.exercises.topics.services.IUnitService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements IUnitService {
    
    @Autowired
    private UnitRepository unitRepository;
    
    @Override
    public UnitResponseDto createUnit(CreateUnitDto createUnitDto) {
        // Verificar si ya existe una unidad con ese nombre
        if (unitRepository.existsByNombre(createUnitDto.getName())) {
            throw new RuntimeException("Ya existe una unidad con el nombre: " + createUnitDto.getName());
        }
        
        // Crear nueva entidad
        UnitEntity entity = new UnitEntity();
        entity.setNombre(createUnitDto.getName());
        entity.setDescripcion(createUnitDto.getDescription());
        
        // Guardar en la base de datos
        UnitEntity savedEntity = unitRepository.save(entity);
        
        // Convertir a DTO de respuesta
        return toResponseDto(savedEntity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<UnitResponseDto> getAllUnits() {
        List<UnitEntity> entities = unitRepository.findAll();
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<UnitResponseDto> findById(Long id) {
        return unitRepository.findById(id)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional
    public UnitResponseDto update(Long id, UpdateUnitDto updateUnitDto) {
        UnitEntity entity = unitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unidad no encontrada con ID: " + id));
        
        if (updateUnitDto.getName() != null && !updateUnitDto.getName().trim().isEmpty()) {
            entity.setNombre(updateUnitDto.getName());
        }
        
        if (updateUnitDto.getDescription() != null) {
            entity.setDescripcion(updateUnitDto.getDescription());
        }
        
        UnitEntity updatedEntity = unitRepository.save(entity);
        return toResponseDto(updatedEntity);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        UnitEntity entity = unitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unidad no encontrada con ID: " + id));
        unitRepository.delete(entity);
    }
    
    // Método helper para convertir entidad a DTO
    private UnitResponseDto toResponseDto(UnitEntity entity) {
        UnitResponseDto dto = new UnitResponseDto();
        dto.setId(entity.getIdUnidad());
        dto.setName(entity.getNombre());
        dto.setDescription(entity.getDescripcion());
        return dto;
    }
}
