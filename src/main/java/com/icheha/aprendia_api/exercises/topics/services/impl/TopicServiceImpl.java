package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.LearningPathResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.TopicEntity;
import com.icheha.aprendia_api.exercises.topics.data.entities.UnitEntity;
import com.icheha.aprendia_api.exercises.topics.data.repositories.TopicRepository;
import com.icheha.aprendia_api.exercises.topics.data.repositories.UnitRepository;
import com.icheha.aprendia_api.exercises.topics.services.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public TopicResponseDto createTopic(CreateTopicDto createTopicDto) {
        // Verificar que la unidad existe
        Optional<UnitEntity> unitOpt = unitRepository.findById(createTopicDto.getUnitId());
        if (unitOpt.isEmpty()) {
            throw new RuntimeException("Unidad no encontrada con ID: " + createTopicDto.getUnitId());
        }
        
        TopicEntity entity = new TopicEntity();
        entity.setNombre(createTopicDto.getName());
        entity.setIdUnidad(createTopicDto.getUnitId());
        
        TopicEntity savedEntity = topicRepository.save(entity);
        
        return toResponseDto(savedEntity, createTopicDto.getUnitId());
    }
    
    @Override
    public List<TopicResponseDto> getAllTopics() {
        List<TopicEntity> entities = topicRepository.findAll();
        return entities.stream()
                .map(entity -> toResponseDto(entity, null))
                .collect(Collectors.toList());
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
    public List<LearningPathResponseDto> getLearningPathsByTopicId(Integer id) {
        // TODO: Implementar lógica para obtener rutas de aprendizaje por tema
        // Por ahora, retornamos datos mock
        return List.of(
            new LearningPathResponseDto(1L, "Learning Path 1", "Descripción del learning path 1")
        );
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
        dto.setUnitId(unitId != null ? unitId : entity.getIdUnidad());
        
        // Obtener el nombre de la unidad si está disponible
        if (entity.getUnidad() != null) {
            dto.setUnitName(entity.getUnidad().getNombre());
        } else if (unitId != null) {
            Optional<UnitEntity> unitOpt = unitRepository.findById(unitId);
            dto.setUnitName(unitOpt.map(UnitEntity::getNombre).orElse("Unit " + unitId));
        } else {
            dto.setUnitName("Unit " + entity.getIdUnidad());
        }
        
        return dto;
    }
}
