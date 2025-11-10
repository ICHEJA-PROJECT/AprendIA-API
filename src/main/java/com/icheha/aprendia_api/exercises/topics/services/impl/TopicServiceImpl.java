package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateTopicDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.LearningPathResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.TopicEntity;
import com.icheha.aprendia_api.exercises.topics.data.repositories.TopicRepository;
import com.icheha.aprendia_api.exercises.topics.data.repositories.CuadernilloRepository;
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
    private CuadernilloRepository cuadernilloRepository;
    
    @Override
    @Transactional
    public TopicResponseDto createTopic(CreateTopicDto createTopicDto) {
        // Validar que el cuadernillo existe
        cuadernilloRepository.findById(createTopicDto.getUnitId())
                .orElseThrow(() -> new EntityNotFoundException("Cuadernillo no encontrado con ID: " + createTopicDto.getUnitId()));
        
        TopicEntity entity = new TopicEntity();
        entity.setNombre(createTopicDto.getName());
        entity.setIdCuadernillo(createTopicDto.getUnitId());
        
        TopicEntity savedEntity = topicRepository.save(entity);
        
        return toResponseDto(savedEntity, null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TopicResponseDto> getAllTopics() {
        List<TopicEntity> entities = topicRepository.findAll();
        return entities.stream()
                .map(entity -> toResponseDto(entity, null))
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TopicResponseDto> findById(Long id) {
        return topicRepository.findById(id)
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
        
        if (updateTopicDto.getCuadernilloId() != null) {
            cuadernilloRepository.findById(updateTopicDto.getCuadernilloId())
                    .orElseThrow(() -> new EntityNotFoundException("Cuadernillo no encontrado con ID: " + updateTopicDto.getCuadernilloId()));
            entity.setIdCuadernillo(updateTopicDto.getCuadernilloId());
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
