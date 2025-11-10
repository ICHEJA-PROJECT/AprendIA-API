package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.LearningPathResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.TopicEntity;
import com.icheha.aprendia_api.exercises.topics.data.repositories.TopicRepository;
import com.icheha.aprendia_api.exercises.topics.services.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements ITopicService {
    
    @Autowired
    private TopicRepository topicRepository;
    
    // TODO: unitRepository eliminado - usar CuadernilloRepository cuando esté disponible
    
    @Override
    public TopicResponseDto createTopic(CreateTopicDto createTopicDto) {
        // Verificar que el cuadernillo existe
        // TODO: Validar que el cuadernillo existe cuando se implemente CuadernilloRepository
        // Por ahora, asumimos que el cuadernillo existe
        
        TopicEntity entity = new TopicEntity();
        entity.setNombre(createTopicDto.getName());
        // TODO: Cambiar a idCuadernillo cuando se actualice el DTO
        // entity.setIdCuadernillo(createTopicDto.getCuadernilloId());
        
        TopicEntity savedEntity = topicRepository.save(entity);
        
        return toResponseDto(savedEntity, null);
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
    private TopicResponseDto toResponseDto(TopicEntity entity, Long cuadernilloId) {
        TopicResponseDto dto = new TopicResponseDto();
        dto.setId(entity.getIdTema());
        dto.setName(entity.getNombre());
        dto.setUnitId(cuadernilloId != null ? cuadernilloId : entity.getIdCuadernillo()); // Usar cuadernilloId como unitId temporalmente
        
        // Obtener el nombre del cuadernillo si está disponible
        if (entity.getCuadernillo() != null) {
            dto.setUnitName(entity.getCuadernillo().getNombre());
        } else if (cuadernilloId != null) {
            dto.setUnitName("Cuadernillo " + cuadernilloId);
        } else {
            dto.setUnitName("Cuadernillo " + entity.getIdCuadernillo());
        }
        
        return dto;
    }
}
