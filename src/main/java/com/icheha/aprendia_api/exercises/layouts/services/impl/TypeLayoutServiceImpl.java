package com.icheha.aprendia_api.exercises.layouts.services.impl;

import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.CreateTypeLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.response.TypeLayoutResponseDto;
import com.icheha.aprendia_api.exercises.layouts.data.entities.TypeLayoutEntity;
import com.icheha.aprendia_api.exercises.layouts.data.repositories.TypeLayoutRepository;
import com.icheha.aprendia_api.exercises.layouts.services.ITypeLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<TypeLayoutResponseDto> getAllTypeLayouts() {
        List<TypeLayoutEntity> entities = typeLayoutRepository.findAll();
        return entities.stream()
                .map(entity -> toResponseDto(entity, null))
                .collect(Collectors.toList());
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
