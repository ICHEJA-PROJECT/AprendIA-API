package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateUnitDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.UnitResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.UnitEntity;
import com.icheha.aprendia_api.exercises.topics.data.repositories.UnitRepository;
import com.icheha.aprendia_api.exercises.topics.services.IUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<UnitResponseDto> getAllUnits() {
        List<UnitEntity> entities = unitRepository.findAll();
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
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
