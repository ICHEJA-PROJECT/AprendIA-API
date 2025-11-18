package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateUnitDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateUnitDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.UnitResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.UnitEntity;
import com.icheha.aprendia_api.exercises.topics.data.repositories.UnitRepository;
import com.icheha.aprendia_api.exercises.topics.data.repositories.CuadernilloRepository;
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
    
    @Autowired
    private CuadernilloRepository cuadernilloRepository;
    
    @Override
    @Transactional
    public UnitResponseDto createUnit(CreateUnitDto createUnitDto) {
        // Validar que el cuadernillo existe
        cuadernilloRepository.findById(createUnitDto.getCuadernilloId())
                .orElseThrow(() -> new EntityNotFoundException("Cuadernillo no encontrado con ID: " + createUnitDto.getCuadernilloId()));
        
        // Crear nueva entidad
        UnitEntity entity = new UnitEntity();
        entity.setNombre(createUnitDto.getName());
        entity.setDescripcion(createUnitDto.getDescription());
        entity.setIdCuadernillo(createUnitDto.getCuadernilloId());
        
        // Guardar en la base de datos
        UnitEntity savedEntity = unitRepository.save(entity);
        
        // Convertir a DTO de respuesta
        return toResponseDto(savedEntity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<UnitResponseDto> getAllUnits() {
        List<UnitEntity> entities = unitRepository.findAllWithRelations();
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<UnitResponseDto> findById(Long id) {
        return unitRepository.findByIdWithRelations(id)
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
        
        if (updateUnitDto.getCuadernilloId() != null) {
            cuadernilloRepository.findById(updateUnitDto.getCuadernilloId())
                    .orElseThrow(() -> new EntityNotFoundException("Cuadernillo no encontrado con ID: " + updateUnitDto.getCuadernilloId()));
            entity.setIdCuadernillo(updateUnitDto.getCuadernilloId());
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
    
    @Override
    @Transactional(readOnly = true)
    public List<UnitResponseDto> getUnitsByCuadernillo(Long cuadernilloId) {
        List<UnitEntity> entities = unitRepository.findByCuadernilloId(cuadernilloId);
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
        dto.setCuadernilloId(entity.getIdCuadernillo());
        
        // Obtener el nombre del cuadernillo si está disponible
        if (entity.getCuadernillo() != null) {
            dto.setCuadernilloNombre(entity.getCuadernillo().getNombre());
        } else {
            dto.setCuadernilloNombre("Cuadernillo " + entity.getIdCuadernillo());
        }
        
        return dto;
    }
}
