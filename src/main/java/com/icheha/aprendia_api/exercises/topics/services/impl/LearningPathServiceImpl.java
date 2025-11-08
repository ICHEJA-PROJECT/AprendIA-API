package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateLearningPathDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateLearningPathDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.LearningPathResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.LearningPath;
import com.icheha.aprendia_api.exercises.topics.data.repositories.LearningPathRepository;
import com.icheha.aprendia_api.exercises.topics.services.ILearningPathService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LearningPathServiceImpl implements ILearningPathService {
    
    @Autowired
    private LearningPathRepository learningPathRepository;
    
    @Override
    @Transactional
    public LearningPathResponseDto create(CreateLearningPathDto createLearningPathDto) {
        // Validar que la metodología existe (si hay repositorio disponible)
        // Por ahora asumimos que existe
        
        // Validar que el perfil existe (si hay repositorio disponible)
        // Por ahora asumimos que existe
        
        LearningPath entity = new LearningPath();
        entity.setIdMetodologia(createLearningPathDto.getIdMetodologia());
        entity.setIdPerfil(createLearningPathDto.getIdPerfil());
        entity.setNombre(createLearningPathDto.getNombre());
        entity.setDescripcion(createLearningPathDto.getDescripcion());
        entity.setUrlImagen(createLearningPathDto.getUrlImagen());
        
        LearningPath savedEntity = learningPathRepository.save(entity);
        
        return toResponseDto(savedEntity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LearningPathResponseDto> findAll() {
        List<LearningPath> entities = learningPathRepository.findAll();
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public LearningPathResponseDto findById(Long id) {
        Optional<LearningPath> entityOpt = learningPathRepository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new EntityNotFoundException("Ruta de aprendizaje no encontrada con ID: " + id);
        }
        return toResponseDto(entityOpt.get());
    }
    
    @Override
    @Transactional
    public LearningPathResponseDto update(Long id, UpdateLearningPathDto updateLearningPathDto) {
        Optional<LearningPath> entityOpt = learningPathRepository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new EntityNotFoundException("Ruta de aprendizaje no encontrada con ID: " + id);
        }
        
        LearningPath entity = entityOpt.get();
        
        // Actualizar campos
        if (updateLearningPathDto.getIdMetodologia() != null) {
            entity.setIdMetodologia(updateLearningPathDto.getIdMetodologia());
        }
        if (updateLearningPathDto.getIdPerfil() != null) {
            entity.setIdPerfil(updateLearningPathDto.getIdPerfil());
        }
        if (updateLearningPathDto.getNombre() != null && !updateLearningPathDto.getNombre().isEmpty()) {
            entity.setNombre(updateLearningPathDto.getNombre());
        }
        if (updateLearningPathDto.getDescripcion() != null) {
            entity.setDescripcion(updateLearningPathDto.getDescripcion());
        }
        if (updateLearningPathDto.getUrlImagen() != null) {
            entity.setUrlImagen(updateLearningPathDto.getUrlImagen());
        }
        
        LearningPath updatedEntity = learningPathRepository.save(entity);
        
        return toResponseDto(updatedEntity);
    }
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!learningPathRepository.existsById(id)) {
            throw new EntityNotFoundException("Ruta de aprendizaje no encontrada con ID: " + id);
        }
        learningPathRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LearningPathResponseDto> findByIdPerfil(Long idPerfil) {
        List<LearningPath> entities = learningPathRepository.findByIdPerfil(idPerfil);
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LearningPathResponseDto> findByIdMetodologia(Long idMetodologia) {
        List<LearningPath> entities = learningPathRepository.findByIdMetodologia(idMetodologia);
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LearningPathResponseDto> findByNombreContaining(String nombre) {
        List<LearningPath> entities = learningPathRepository.findByNombreContainingIgnoreCase(nombre);
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    // Método helper para convertir entidad a DTO
    private LearningPathResponseDto toResponseDto(LearningPath entity) {
        LearningPathResponseDto dto = new LearningPathResponseDto();
        dto.setId(entity.getIdRuta());
        dto.setIdMetodologia(entity.getIdMetodologia());
        dto.setIdPerfil(entity.getIdPerfil());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setUrlImagen(entity.getUrlImagen());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdateAt(entity.getUpdateAt());
        
        // Manejar relaciones lazy de forma segura
        try {
            if (entity.getMetodologia() != null) {
                dto.setNombreMetodologia(entity.getMetodologia().getNombre());
            } else {
                dto.setNombreMetodologia(null);
            }
        } catch (org.hibernate.LazyInitializationException e) {
            dto.setNombreMetodologia(null);
        }
        
        try {
            if (entity.getPerfil() != null) {
                dto.setNombrePerfil(entity.getPerfil().getNombre());
            } else {
                dto.setNombrePerfil(null);
            }
        } catch (org.hibernate.LazyInitializationException e) {
            dto.setNombrePerfil(null);
        }
        
        return dto;
    }
}

