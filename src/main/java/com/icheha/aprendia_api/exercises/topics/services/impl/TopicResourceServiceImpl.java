package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateTopicResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicResourceResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.pivots.TopicResourceEntity;
import com.icheha.aprendia_api.exercises.topics.data.repositories.TopicResourceRepository;
import com.icheha.aprendia_api.exercises.topics.data.repositories.TopicRepository;
import com.icheha.aprendia_api.exercises.topics.data.repositories.ResourceRepository;
import com.icheha.aprendia_api.exercises.topics.services.ITopicResourceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicResourceServiceImpl implements ITopicResourceService {
    
    @Autowired
    private TopicResourceRepository topicResourceRepository;
    
    @Autowired
    private TopicRepository topicRepository;
    
    @Autowired
    private ResourceRepository resourceRepository;
    
    @Override
    @Transactional
    public TopicResourceResponseDto createTopicResource(CreateTopicResourceDto createTopicResourceDto) {
        // Validar que el tema existe
        topicRepository.findById(createTopicResourceDto.getTopicId())
                .orElseThrow(() -> new EntityNotFoundException("Tema no encontrado con ID: " + createTopicResourceDto.getTopicId()));
        
        // Validar que el recurso existe
        resourceRepository.findById(createTopicResourceDto.getResourceId())
                .orElseThrow(() -> new EntityNotFoundException("Recurso no encontrado con ID: " + createTopicResourceDto.getResourceId()));
        
        // Verificar si ya existe la relación
        Optional<TopicResourceEntity> existing = topicResourceRepository.findByTopicIdAndResourceId(
                createTopicResourceDto.getTopicId(), createTopicResourceDto.getResourceId());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("La relación entre el tema y el recurso ya existe");
        }
        
        // Crear nueva entidad
        TopicResourceEntity entity = new TopicResourceEntity();
        entity.setIdTema(createTopicResourceDto.getTopicId());
        entity.setIdRecurso(createTopicResourceDto.getResourceId());
        
        // Guardar en la base de datos
        TopicResourceEntity savedEntity = topicResourceRepository.save(entity);
        
        // Cargar relaciones para obtener nombres
        TopicResourceEntity entityWithRelations = topicResourceRepository.findByTopicIdAndResourceId(
                savedEntity.getIdTema(), savedEntity.getIdRecurso())
                .orElse(savedEntity);
        
        return toResponseDto(entityWithRelations);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TopicResourceResponseDto> getAllTopicResources() {
        List<TopicResourceEntity> entities = topicResourceRepository.findAllWithRelations();
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TopicResourceResponseDto> findById(Long topicId, Long resourceId) {
        return topicResourceRepository.findByTopicIdAndResourceId(topicId, resourceId)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional
    public TopicResourceResponseDto update(Long topicId, Long resourceId, UpdateTopicResourceDto updateTopicResourceDto) {
        TopicResourceEntity entity = topicResourceRepository.findByTopicIdAndResourceId(topicId, resourceId)
                .orElseThrow(() -> new EntityNotFoundException("Relación tema-recurso no encontrada"));
        
        // Si se actualiza el tema, validar que existe
        if (updateTopicResourceDto.getTopicId() != null && !updateTopicResourceDto.getTopicId().equals(topicId)) {
            topicRepository.findById(updateTopicResourceDto.getTopicId())
                    .orElseThrow(() -> new EntityNotFoundException("Tema no encontrado con ID: " + updateTopicResourceDto.getTopicId()));
            
            // Verificar si la nueva relación ya existe
            Optional<TopicResourceEntity> existing = topicResourceRepository.findByTopicIdAndResourceId(
                    updateTopicResourceDto.getTopicId(), resourceId);
            if (existing.isPresent()) {
                throw new IllegalArgumentException("La relación entre el nuevo tema y el recurso ya existe");
            }
            
            // Eliminar la relación antigua
            topicResourceRepository.delete(entity);
            
            // Crear nueva relación
            TopicResourceEntity newEntity = new TopicResourceEntity();
            newEntity.setIdTema(updateTopicResourceDto.getTopicId());
            newEntity.setIdRecurso(resourceId);
            entity = topicResourceRepository.save(newEntity);
        }
        
        // Si se actualiza el recurso, validar que existe
        if (updateTopicResourceDto.getResourceId() != null && !updateTopicResourceDto.getResourceId().equals(resourceId)) {
            resourceRepository.findById(updateTopicResourceDto.getResourceId())
                    .orElseThrow(() -> new EntityNotFoundException("Recurso no encontrado con ID: " + updateTopicResourceDto.getResourceId()));
            
            // Verificar si la nueva relación ya existe
            Optional<TopicResourceEntity> existing = topicResourceRepository.findByTopicIdAndResourceId(
                    topicId, updateTopicResourceDto.getResourceId());
            if (existing.isPresent()) {
                throw new IllegalArgumentException("La relación entre el tema y el nuevo recurso ya existe");
            }
            
            // Eliminar la relación antigua
            topicResourceRepository.delete(entity);
            
            // Crear nueva relación
            TopicResourceEntity newEntity = new TopicResourceEntity();
            newEntity.setIdTema(topicId);
            newEntity.setIdRecurso(updateTopicResourceDto.getResourceId());
            entity = topicResourceRepository.save(newEntity);
        }
        
        // Cargar relaciones para obtener nombres
        TopicResourceEntity entityWithRelations = topicResourceRepository.findByTopicIdAndResourceId(
                entity.getIdTema(), entity.getIdRecurso())
                .orElse(entity);
        
        return toResponseDto(entityWithRelations);
    }
    
    @Override
    @Transactional
    public void delete(Long topicId, Long resourceId) {
        TopicResourceEntity entity = topicResourceRepository.findByTopicIdAndResourceId(topicId, resourceId)
                .orElseThrow(() -> new EntityNotFoundException("Relación tema-recurso no encontrada"));
        topicResourceRepository.delete(entity);
    }
    
    private TopicResourceResponseDto toResponseDto(TopicResourceEntity entity) {
        TopicResourceResponseDto dto = new TopicResourceResponseDto();
        dto.setId(entity.getIdTema() * 1000L + entity.getIdRecurso()); // ID compuesto para compatibilidad
        dto.setTopicId(entity.getIdTema());
        dto.setResourceId(entity.getIdRecurso());
        dto.setTopicName(entity.getTema() != null ? entity.getTema().getNombre() : null);
        dto.setResourceName(entity.getRecurso() != null ? entity.getRecurso().getNombre() : null);
        return dto;
    }
}
