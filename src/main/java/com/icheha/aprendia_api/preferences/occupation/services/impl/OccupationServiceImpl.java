package com.icheha.aprendia_api.preferences.occupation.services.impl;

import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.CreateOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.response.OccupationResponseDto;
import com.icheha.aprendia_api.preferences.occupation.data.entities.OccupationEntity;
import com.icheha.aprendia_api.preferences.occupation.data.repositories.OccupationRepository;
import com.icheha.aprendia_api.preferences.occupation.services.IOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OccupationServiceImpl implements IOccupationService {
    
    @Autowired
    private OccupationRepository occupationRepository;
    
    @Override
    public List<Object> getAllOccupations() {
        // Este método parece ser para compatibilidad, retornamos la lista de DTOs
        return findAll().stream()
                .map(occupation -> (Object) occupation)
                .collect(Collectors.toList());
    }
    
    @Override
    public OccupationResponseDto create(CreateOccupationDto dto) {
        // Verificar si ya existe una ocupación con ese nombre
        if (occupationRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Ya existe una ocupación con el nombre: " + dto.getName());
        }
        
        // Crear nueva entidad
        OccupationEntity entity = new OccupationEntity();
        entity.setName(dto.getName());
        
        // Guardar en la base de datos
        OccupationEntity savedEntity = occupationRepository.save(entity);
        
        // Convertir a DTO de respuesta (sin cargar relaciones lazy)
        return toResponseDto(savedEntity);
    }
    
    @Override
    public List<OccupationResponseDto> findAll() {
        List<OccupationEntity> entities = occupationRepository.findAll();
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public OccupationResponseDto findById(Long id) {
        Optional<OccupationEntity> entityOpt = occupationRepository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new IllegalArgumentException("Ocupación no encontrada con ID: " + id);
        }
        return toResponseDto(entityOpt.get());
    }
    
    @Override
    public List<OccupationResponseDto> findByNameContaining(String name) {
        List<OccupationEntity> entities = occupationRepository.findByNameContaining(name);
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(Long id) {
        if (!occupationRepository.existsById(id)) {
            throw new IllegalArgumentException("Ocupación no encontrada con ID: " + id);
        }
        occupationRepository.deleteById(id);
    }
    
    @Override
    public boolean existsByName(String name) {
        return occupationRepository.existsByName(name);
    }
    
    // Método helper para convertir entidad a DTO
    private OccupationResponseDto toResponseDto(OccupationEntity entity) {
        OccupationResponseDto dto = new OccupationResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        
        // Manejar relaciones lazy de forma segura
        try {
            // Intentar acceder a la relación, pero si no está cargada, usar valores por defecto
            if (entity.getStudents() != null) {
                dto.setStudentCount(entity.getStudents().size());
                dto.setStudentIds(entity.getStudents().stream()
                        .map(student -> student.getStudentId())
                        .collect(Collectors.toList()));
            } else {
                dto.setStudentCount(0);
                dto.setStudentIds(Collections.emptyList());
            }
        } catch (org.hibernate.LazyInitializationException e) {
            // Si la relación no está cargada, usar valores por defecto
            dto.setStudentCount(0);
            dto.setStudentIds(Collections.emptyList());
        }
        
        dto.setExerciseCount(0); // No hay relación con ejercicios definida
        dto.setExerciseIds(Collections.emptyList());
        
        return dto;
    }
}
