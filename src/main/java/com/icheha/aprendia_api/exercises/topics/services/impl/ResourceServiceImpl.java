package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.ResourceResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.ResourceEntity;
import com.icheha.aprendia_api.exercises.topics.data.repositories.ResourceRepository;
import com.icheha.aprendia_api.exercises.topics.services.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements IResourceService {
    
    @Autowired
    private ResourceRepository resourceRepository;
    
    @Override
    public ResourceResponseDto createResource(CreateResourceDto createResourceDto) {
        // Crear nueva entidad
        ResourceEntity entity = new ResourceEntity();
        entity.setNombre(createResourceDto.getTitle()); // Usar nombre en lugar de titulo
        entity.setContenido(createResourceDto.getContent());
        entity.setIdTema(createResourceDto.getLayoutId()); // Temporalmente usar layoutId como temaId
        
        // Guardar en la base de datos
        ResourceEntity savedEntity = resourceRepository.save(entity);
        
        // Convertir a DTO de respuesta
        return toResponseDto(savedEntity);
    }
    
    @Override
    public List<ResourceResponseDto> getAllResources() {
        List<ResourceEntity> entities = resourceRepository.findAll();
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<ResourceResponseDto> getResourcesByPupilLearningPath(Integer id, Integer learningPathId) {
        // TODO: Implementar lógica específica para obtener recursos por alumno y ruta de aprendizaje
        // Por ahora, retornamos todos los recursos
        return getAllResources();
    }
    
    @Override
    public List<ResourceResponseDto> getResourcesByTopicLearningPath(Integer id, Integer learningPathId) {
        // TODO: Implementar lógica específica para obtener recursos por tema y ruta de aprendizaje
        // Por ahora, retornamos todos los recursos
        return getAllResources();
    }
    
    @Override
    public List<ResourceResponseDto> getResourcesByPupil(Integer id) {
        // TODO: Implementar lógica específica para obtener recursos por alumno
        // Por ahora, retornamos todos los recursos
        return getAllResources();
    }
    
    @Override
    public List<ResourceResponseDto> getResourcesByTopic(Integer id) {
        // TODO: Implementar lógica específica para obtener recursos por tema
        // Por ahora, retornamos todos los recursos
        return getAllResources();
    }
    
    @Override
    public ResourceResponseDto getResourceById(Integer id) {
        Optional<ResourceEntity> entityOpt = resourceRepository.findById(id.longValue());
        if (entityOpt.isEmpty()) {
            throw new RuntimeException("Recurso no encontrado con ID: " + id);
        }
        return toResponseDto(entityOpt.get());
    }
    
    // Método helper para convertir entidad a DTO
    private ResourceResponseDto toResponseDto(ResourceEntity entity) {
        ResourceResponseDto dto = new ResourceResponseDto();
        dto.setId(entity.getIdRecurso());
        dto.setTitle(entity.getNombre()); // Usar nombre
        dto.setContent(entity.getContenido());
        dto.setLayoutId(entity.getIdTema()); // Temporalmente usar idTema como layoutId
        
        // Obtener nombre del tema si está disponible
        if (entity.getTema() != null) {
            dto.setLayoutName(entity.getTema().getNombre());
        }
        
        // Extraer IDs de temas asociados
        if (entity.getTopicResources() != null) {
            dto.setTopicIds(entity.getTopicResources().stream()
                    .map(topicResource -> topicResource.getIdTema())
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }
}
