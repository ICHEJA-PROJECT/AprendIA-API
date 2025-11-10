package com.icheha.aprendia_api.exercises.templates.services.impl;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTypeInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.UpdateTypeInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TypeInstructionMediaResponseDto;
import com.icheha.aprendia_api.exercises.templates.data.entities.TypeInstructionMediaEntity;
import com.icheha.aprendia_api.exercises.templates.data.repositories.TypeInstructionMediaRepository;
import com.icheha.aprendia_api.exercises.templates.services.ITypeInstructionMediaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypeInstructionMediaServiceImpl implements ITypeInstructionMediaService {
    
    @Autowired
    private TypeInstructionMediaRepository typeInstructionMediaRepository;
    
    @Override
    @Transactional
    public TypeInstructionMediaResponseDto createTypeInstructionMedia(CreateTypeInstructionMediaDto createTypeInstructionMediaDto) {
        // Verificar si ya existe un tipo con el mismo nombre
        if (typeInstructionMediaRepository.existsByNombre(createTypeInstructionMediaDto.getName())) {
            throw new IllegalArgumentException("Ya existe un tipo de instrucción media con el nombre: " + createTypeInstructionMediaDto.getName());
        }
        
        // Crear nueva entidad
        TypeInstructionMediaEntity entity = new TypeInstructionMediaEntity();
        entity.setNombre(createTypeInstructionMediaDto.getName());
        
        // Guardar en la base de datos
        TypeInstructionMediaEntity savedEntity = typeInstructionMediaRepository.save(entity);
        
        // Convertir a DTO de respuesta
        return toResponseDto(savedEntity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TypeInstructionMediaResponseDto> getAllTypeInstructionMedias() {
        List<TypeInstructionMediaEntity> entities = typeInstructionMediaRepository.findAll();
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeInstructionMediaResponseDto> findById(Long id) {
        return typeInstructionMediaRepository.findById(id)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional
    public TypeInstructionMediaResponseDto update(Long id, UpdateTypeInstructionMediaDto updateTypeInstructionMediaDto) {
        TypeInstructionMediaEntity entity = typeInstructionMediaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de instrucción media no encontrado con ID: " + id));
        
        if (updateTypeInstructionMediaDto.getName() != null && !updateTypeInstructionMediaDto.getName().trim().isEmpty()) {
            // Verificar si el nuevo nombre ya existe en otro tipo
            typeInstructionMediaRepository.findByNombre(updateTypeInstructionMediaDto.getName())
                    .ifPresent(existing -> {
                        if (!existing.getIdTipoMedia().equals(id)) {
                            throw new IllegalArgumentException("Ya existe un tipo de instrucción media con el nombre: " + updateTypeInstructionMediaDto.getName());
                        }
                    });
            entity.setNombre(updateTypeInstructionMediaDto.getName());
        }
        
        TypeInstructionMediaEntity updatedEntity = typeInstructionMediaRepository.save(entity);
        return toResponseDto(updatedEntity);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        TypeInstructionMediaEntity entity = typeInstructionMediaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de instrucción media no encontrado con ID: " + id));
        typeInstructionMediaRepository.delete(entity);
    }
    
    private TypeInstructionMediaResponseDto toResponseDto(TypeInstructionMediaEntity entity) {
        TypeInstructionMediaResponseDto dto = new TypeInstructionMediaResponseDto();
        dto.setId(entity.getIdTipoMedia());
        dto.setName(entity.getNombre());
        dto.setDescription(null); // No hay campo description en la entidad
        return dto;
    }
}
