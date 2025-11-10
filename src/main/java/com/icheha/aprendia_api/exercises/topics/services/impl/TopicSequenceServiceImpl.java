package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicSequenceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateTopicSequenceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicSequenceResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.TopicSequenceEntity;
import com.icheha.aprendia_api.exercises.topics.data.repositories.TopicSequenceRepository;
import com.icheha.aprendia_api.exercises.topics.data.repositories.TopicRepository;
import com.icheha.aprendia_api.exercises.topics.data.repositories.LearningPathRepository;
import com.icheha.aprendia_api.exercises.topics.services.ITopicSequenceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicSequenceServiceImpl implements ITopicSequenceService {
    
    @Autowired
    private TopicSequenceRepository topicSequenceRepository;
    
    @Autowired
    private TopicRepository topicRepository;
    
    @Autowired
    private LearningPathRepository learningPathRepository;
    
    @Override
    @Transactional
    public TopicSequenceResponseDto createTopicSequence(CreateTopicSequenceDto createTopicSequenceDto) {
        // Validar que el tema existe
        topicRepository.findById(createTopicSequenceDto.getTopicId())
                .orElseThrow(() -> new EntityNotFoundException("Tema no encontrado con ID: " + createTopicSequenceDto.getTopicId()));
        
        // Validar que el tema siguiente existe
        topicRepository.findById(createTopicSequenceDto.getNextTopicId())
                .orElseThrow(() -> new EntityNotFoundException("Tema siguiente no encontrado con ID: " + createTopicSequenceDto.getNextTopicId()));
        
        // Validar que la ruta de aprendizaje existe
        learningPathRepository.findById(createTopicSequenceDto.getLearningPathId())
                .orElseThrow(() -> new EntityNotFoundException("Ruta de aprendizaje no encontrada con ID: " + createTopicSequenceDto.getLearningPathId()));
        
        // Verificar si ya existe la relación
        Optional<TopicSequenceEntity> existing = topicSequenceRepository.findByTopicIdAndNextTopicId(
                createTopicSequenceDto.getTopicId(), createTopicSequenceDto.getNextTopicId());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("La secuencia entre estos temas ya existe");
        }
        
        // Crear nueva entidad
        TopicSequenceEntity entity = new TopicSequenceEntity();
        entity.setIdTema(createTopicSequenceDto.getTopicId());
        entity.setIdTemaSiguiente(createTopicSequenceDto.getNextTopicId());
        entity.setIdRuta(createTopicSequenceDto.getLearningPathId());
        
        // Guardar en la base de datos
        TopicSequenceEntity savedEntity = topicSequenceRepository.save(entity);
        
        // Cargar relaciones para obtener nombres
        TopicSequenceEntity entityWithRelations = topicSequenceRepository.findByTopicIdAndNextTopicId(
                savedEntity.getIdTema(), savedEntity.getIdTemaSiguiente())
                .orElse(savedEntity);
        
        return toResponseDto(entityWithRelations);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TopicSequenceResponseDto> getAllTopicSequences() {
        List<TopicSequenceEntity> entities = topicSequenceRepository.findAllWithRelations();
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TopicSequenceResponseDto> findById(Long topicId, Long nextTopicId) {
        return topicSequenceRepository.findByTopicIdAndNextTopicId(topicId, nextTopicId)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional
    public TopicSequenceResponseDto update(Long topicId, Long nextTopicId, UpdateTopicSequenceDto updateTopicSequenceDto) {
        TopicSequenceEntity entity = topicSequenceRepository.findByTopicIdAndNextTopicId(topicId, nextTopicId)
                .orElseThrow(() -> new EntityNotFoundException("Secuencia de temas no encontrada"));
        
        boolean needsRecreate = false;
        Long newTopicId = topicId;
        Long newNextTopicId = nextTopicId;
        
        // Si se actualiza el tema, validar que existe
        if (updateTopicSequenceDto.getTopicId() != null && !updateTopicSequenceDto.getTopicId().equals(topicId)) {
            topicRepository.findById(updateTopicSequenceDto.getTopicId())
                    .orElseThrow(() -> new EntityNotFoundException("Tema no encontrado con ID: " + updateTopicSequenceDto.getTopicId()));
            newTopicId = updateTopicSequenceDto.getTopicId();
            needsRecreate = true;
        }
        
        // Si se actualiza el tema siguiente, validar que existe
        if (updateTopicSequenceDto.getNextTopicId() != null && !updateTopicSequenceDto.getNextTopicId().equals(nextTopicId)) {
            topicRepository.findById(updateTopicSequenceDto.getNextTopicId())
                    .orElseThrow(() -> new EntityNotFoundException("Tema siguiente no encontrado con ID: " + updateTopicSequenceDto.getNextTopicId()));
            newNextTopicId = updateTopicSequenceDto.getNextTopicId();
            needsRecreate = true;
        }
        
        // Si se actualiza la ruta de aprendizaje, validar que existe
        if (updateTopicSequenceDto.getLearningPathId() != null) {
            learningPathRepository.findById(updateTopicSequenceDto.getLearningPathId())
                    .orElseThrow(() -> new EntityNotFoundException("Ruta de aprendizaje no encontrada con ID: " + updateTopicSequenceDto.getLearningPathId()));
            entity.setIdRuta(updateTopicSequenceDto.getLearningPathId());
        }
        
        // Si se cambió algún ID de la clave compuesta, necesitamos recrear la entidad
        if (needsRecreate) {
            // Verificar si la nueva relación ya existe
            Optional<TopicSequenceEntity> existing = topicSequenceRepository.findByTopicIdAndNextTopicId(
                    newTopicId, newNextTopicId);
            if (existing.isPresent()) {
                throw new IllegalArgumentException("La secuencia entre estos temas ya existe");
            }
            
            // Eliminar la relación antigua
            topicSequenceRepository.delete(entity);
            
            // Crear nueva relación
            TopicSequenceEntity newEntity = new TopicSequenceEntity();
            newEntity.setIdTema(newTopicId);
            newEntity.setIdTemaSiguiente(newNextTopicId);
            newEntity.setIdRuta(entity.getIdRuta());
            entity = topicSequenceRepository.save(newEntity);
        } else {
            entity = topicSequenceRepository.save(entity);
        }
        
        // Cargar relaciones para obtener nombres
        TopicSequenceEntity entityWithRelations = topicSequenceRepository.findByTopicIdAndNextTopicId(
                entity.getIdTema(), entity.getIdTemaSiguiente())
                .orElse(entity);
        
        return toResponseDto(entityWithRelations);
    }
    
    @Override
    @Transactional
    public void delete(Long topicId, Long nextTopicId) {
        TopicSequenceEntity entity = topicSequenceRepository.findByTopicIdAndNextTopicId(topicId, nextTopicId)
                .orElseThrow(() -> new EntityNotFoundException("Secuencia de temas no encontrada"));
        topicSequenceRepository.delete(entity);
    }
    
    private TopicSequenceResponseDto toResponseDto(TopicSequenceEntity entity) {
        TopicSequenceResponseDto dto = new TopicSequenceResponseDto();
        dto.setId(entity.getIdTema() * 1000L + entity.getIdTemaSiguiente()); // ID compuesto para compatibilidad
        dto.setTopicId(entity.getIdTema());
        dto.setSequenceId(entity.getIdTemaSiguiente());
        dto.setTopicName(entity.getTema() != null ? entity.getTema().getNombre() : null);
        dto.setSequenceName(entity.getTemaSiguiente() != null ? entity.getTemaSiguiente().getNombre() : null);
        dto.setOrder(null); // No hay campo order en la entidad
        return dto;
    }
}
