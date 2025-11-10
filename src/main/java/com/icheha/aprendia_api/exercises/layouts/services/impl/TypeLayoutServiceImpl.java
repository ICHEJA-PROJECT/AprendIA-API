package com.icheha.aprendia_api.exercises.layouts.services.impl;

import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.CreateTypeLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.UpdateTypeLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.response.TypeLayoutResponseDto;
import com.icheha.aprendia_api.exercises.layouts.data.entities.TypeLayoutEntity;
import com.icheha.aprendia_api.exercises.layouts.data.repositories.TypeLayoutRepository;
import com.icheha.aprendia_api.exercises.layouts.services.ITypeLayoutService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypeLayoutServiceImpl implements ITypeLayoutService {
    
    @Autowired
    private TypeLayoutRepository typeLayoutRepository;
    
    @Override
    public TypeLayoutResponseDto createTypeLayout(CreateTypeLayoutDto createTypeLayoutDto) {
        // Verificar si ya existe un tipo de layout con ese nombre
        if (typeLayoutRepository.existsByNombre(createTypeLayoutDto.getName())) {
            throw new RuntimeException("Ya existe un tipo de layout con el nombre: " + createTypeLayoutDto.getName());
        }
        
        // Crear nueva entidad
        TypeLayoutEntity entity = new TypeLayoutEntity();
        entity.setNombre(createTypeLayoutDto.getName());
        // Nota: La entidad no tiene campo description, solo nombre
        
        // Guardar en la base de datos
        TypeLayoutEntity savedEntity = typeLayoutRepository.save(entity);
        
        // Convertir a DTO de respuesta
        return toResponseDto(savedEntity, createTypeLayoutDto.getDescription());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TypeLayoutResponseDto> getAllTypeLayouts() {
        List<TypeLayoutEntity> entities = typeLayoutRepository.findAll();
        return entities.stream()
                .map(entity -> toResponseDto(entity, null))
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeLayoutResponseDto> findById(Long id) {
        return typeLayoutRepository.findById(id)
                .map(entity -> toResponseDto(entity, null));
    }
    
    @Override
    @Transactional
    public TypeLayoutResponseDto update(Long id, UpdateTypeLayoutDto updateTypeLayoutDto) {
        TypeLayoutEntity entity = typeLayoutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de layout no encontrado con ID: " + id));
        
        if (updateTypeLayoutDto.getName() != null && !updateTypeLayoutDto.getName().trim().isEmpty()) {
            // Verificar si el nuevo nombre ya existe en otro tipo de layout
            typeLayoutRepository.findByNombre(updateTypeLayoutDto.getName())
                    .ifPresent(existing -> {
                        if (!existing.getIdTipoLayout().equals(id)) {
                            throw new IllegalArgumentException("Ya existe un tipo de layout con el nombre: " + updateTypeLayoutDto.getName());
                        }
                    });
            entity.setNombre(updateTypeLayoutDto.getName());
        }
        
        TypeLayoutEntity updatedEntity = typeLayoutRepository.save(entity);
        return toResponseDto(updatedEntity, null);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        TypeLayoutEntity entity = typeLayoutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de layout no encontrado con ID: " + id));
        typeLayoutRepository.delete(entity);
    }
    
    // Método helper para convertir entidad a DTO
    private TypeLayoutResponseDto toResponseDto(TypeLayoutEntity entity, String description) {
        TypeLayoutResponseDto dto = new TypeLayoutResponseDto();
        dto.setId(entity.getIdTipoLayout());
        dto.setName(entity.getNombre());
        dto.setDescription(description != null ? description : "Tipo de layout: " + entity.getNombre());
        return dto;
    }
}
