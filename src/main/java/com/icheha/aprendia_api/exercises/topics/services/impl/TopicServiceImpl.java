package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateTopicDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.LearningPathResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.TopicEntity;
import com.icheha.aprendia_api.exercises.topics.data.repositories.TopicRepository;
import com.icheha.aprendia_api.exercises.topics.data.repositories.UnitRepository;
import com.icheha.aprendia_api.exercises.topics.services.ITopicService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements ITopicService {
    
    @Autowired
    private TopicRepository topicRepository;
    
    @Autowired
    private UnitRepository unitRepository;
    
    @Override
    @Transactional
    public TopicResponseDto createTopic(CreateTopicDto createTopicDto) {
        // Validar que la unidad existe
        unitRepository.findById(createTopicDto.getUnitId())
                .orElseThrow(() -> new EntityNotFoundException("Unidad no encontrada con ID: " + createTopicDto.getUnitId()));
        
        TopicEntity entity = new TopicEntity();
        entity.setNombre(createTopicDto.getName());
        entity.setDescripcion(createTopicDto.getDescription());
        entity.setUrlImagen(createTopicDto.getUrlImagen());
        entity.setIdUnidad(createTopicDto.getUnitId());
        
        TopicEntity savedEntity = topicRepository.save(entity);
        
        return toResponseDto(savedEntity, null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TopicResponseDto> getAllTopics() {
        List<TopicEntity> entities = topicRepository.findAllWithRelations();
        return entities.stream()
                .map(entity -> toResponseDto(entity, null))
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TopicResponseDto> findById(Long id) {
        return topicRepository.findByIdWithRelations(id)
                .map(entity -> toResponseDto(entity, null));
    }
    
    @Override
    @Transactional
    public TopicResponseDto update(Long id, UpdateTopicDto updateTopicDto) {
        TopicEntity entity = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tema no encontrado con ID: " + id));
        
        if (updateTopicDto.getName() != null && !updateTopicDto.getName().trim().isEmpty()) {
            entity.setNombre(updateTopicDto.getName());
        }
        
        if (updateTopicDto.getUnitId() != null) {
            unitRepository.findById(updateTopicDto.getUnitId())
                    .orElseThrow(() -> new EntityNotFoundException("Unidad no encontrada con ID: " + updateTopicDto.getUnitId()));
            entity.setIdUnidad(updateTopicDto.getUnitId());
        }
        
        if (updateTopicDto.getDescripcion() != null) {
            entity.setDescripcion(updateTopicDto.getDescripcion());
        }
        
        if (updateTopicDto.getUrlImagen() != null) {
            entity.setUrlImagen(updateTopicDto.getUrlImagen());
        }
        
        TopicEntity updatedEntity = topicRepository.save(entity);
        return toResponseDto(updatedEntity, null);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        TopicEntity entity = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tema no encontrado con ID: " + id));
        topicRepository.delete(entity);
    }
    
    @Override
    public List<TopicResponseDto> getTopicsByPupilLearningPath(Integer id, Integer learningPathId) {
        // TODO: Implementar lógica específica para obtener temas por alumno y ruta de aprendizaje
        // Por ahora, retornamos todos los temas
        return getAllTopics();
    }
    
    @Override
    public List<TopicResponseDto> getTopicsByPupil(Integer id) {
        // TODO: Implementar lógica específica para obtener temas por alumno
        // Por ahora, retornamos todos los temas
        return getAllTopics();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TopicResponseDto> getTopicsByUnit(Long unitId) {
        List<TopicEntity> entities = topicRepository.findByUnidadId(unitId);
        return entities.stream()
                .map(entity -> toResponseDto(entity, null))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<LearningPathResponseDto> getLearningPathsByTopicId(Integer id) {
        // TODO: Implementar lógica para obtener rutas de aprendizaje por tema
        // Por ahora, retornamos datos mock
        LearningPathResponseDto mockDto = new LearningPathResponseDto();
        mockDto.setId(1L);
        mockDto.setNombre("Learning Path 1");
        mockDto.setDescripcion("Descripción del learning path 1");
        return List.of(mockDto);
    }
    
    @Override
    public List<LearningPathResponseDto> getLearningPathByTopic(Integer id) {
        return getLearningPathsByTopicId(id);
    }
    
    // Método helper para convertir entidad a DTO
    private TopicResponseDto toResponseDto(TopicEntity entity, Long unitId) {
        TopicResponseDto dto = new TopicResponseDto();
        dto.setId(entity.getIdTema());
        dto.setName(entity.getNombre());
        dto.setDescription(entity.getDescripcion());
        dto.setUrlImagen(entity.getUrlImagen());
        dto.setUnitId(unitId != null ? unitId : entity.getIdUnidad());
        
        // Obtener el nombre de la unidad si está disponible
        if (entity.getUnidad() != null) {
            dto.setUnitName(entity.getUnidad().getNombre());
        } else if (unitId != null) {
            dto.setUnitName("Unidad " + unitId);
        } else {
            dto.setUnitName("Unidad " + entity.getIdUnidad());
        }
        
        return dto;
    }
}
