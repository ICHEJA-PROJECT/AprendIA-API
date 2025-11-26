package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.ResourceResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.ResourceEntity;
import com.icheha.aprendia_api.exercises.topics.data.repositories.ResourceRepository;
import com.icheha.aprendia_api.exercises.topics.data.repositories.TopicRepository;
import com.icheha.aprendia_api.exercises.layouts.data.repositories.LayoutRepository;
import com.icheha.aprendia_api.exercises.topics.services.IResourceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements IResourceService {
    
    @Autowired
    private ResourceRepository resourceRepository;
    
    @Autowired
    private TopicRepository topicRepository;
    
    @Autowired
    private LayoutRepository layoutRepository;
    
    @Override
    @Transactional
    public ResourceResponseDto createResource(CreateResourceDto createResourceDto) {
        // Validar que el layout existe
        layoutRepository.findById(createResourceDto.getLayoutId())
                .orElseThrow(() -> new EntityNotFoundException("Layout no encontrado con ID: " + createResourceDto.getLayoutId()));
        
        // Crear nueva entidad
        ResourceEntity entity = new ResourceEntity();
        entity.setNombre(createResourceDto.getTitle());
        entity.setContenido(createResourceDto.getContent());
        entity.setIdLayout(createResourceDto.getLayoutId());
        entity.setIdTema(createResourceDto.getTopicId());
        entity.setUrlImagen(createResourceDto.getUrlImagen());
        
        // Guardar en la base de datos
        ResourceEntity savedEntity = resourceRepository.save(entity);
        
        // Convertir a DTO de respuesta
        return toResponseDto(savedEntity);
    }
    
    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public ResourceResponseDto getResourceById(Long id) {
        ResourceEntity entity = resourceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recurso no encontrado con ID: " + id));
        return toResponseDto(entity);
    }
    
    @Override
    @Transactional
    public ResourceResponseDto update(Long id, UpdateResourceDto updateResourceDto) {
        ResourceEntity entity = resourceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recurso no encontrado con ID: " + id));
        
        if (updateResourceDto.getTitle() != null && !updateResourceDto.getTitle().trim().isEmpty()) {
            entity.setNombre(updateResourceDto.getTitle());
        }
        
        if (updateResourceDto.getContent() != null) {
            entity.setContenido(updateResourceDto.getContent());
        }
        
        if (updateResourceDto.getTopicId() != null) {
            topicRepository.findById(updateResourceDto.getTopicId())
                    .orElseThrow(() -> new EntityNotFoundException("Tema no encontrado con ID: " + updateResourceDto.getTopicId()));
            entity.setIdTema(updateResourceDto.getTopicId());
        }
        
        if (updateResourceDto.getLayoutId() != null) {
            layoutRepository.findById(updateResourceDto.getLayoutId())
                    .orElseThrow(() -> new EntityNotFoundException("Layout no encontrado con ID: " + updateResourceDto.getLayoutId()));
            entity.setIdLayout(updateResourceDto.getLayoutId());
        }
        
        if (updateResourceDto.getUrlImagen() != null) {
            entity.setUrlImagen(updateResourceDto.getUrlImagen());
        }
        
        ResourceEntity updatedEntity = resourceRepository.save(entity);
        return toResponseDto(updatedEntity);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        ResourceEntity entity = resourceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recurso no encontrado con ID: " + id));
        resourceRepository.delete(entity);
    }
    
    // Método helper para convertir entidad a DTO
    private ResourceResponseDto toResponseDto(ResourceEntity entity) {
        ResourceResponseDto dto = new ResourceResponseDto();
        dto.setId(entity.getIdRecurso());
        dto.setTitle(entity.getNombre());
        dto.setContent(entity.getContenido());
        dto.setLayoutId(entity.getIdLayout());
        dto.setTopicId(entity.getIdTema());
        dto.setUrlImagen(entity.getUrlImagen());
        
        // Obtener nombre del layout si está disponible
        if (entity.getLayout() != null) {
            dto.setLayoutName(entity.getLayout().getNombre());
        }
        
        // Extraer IDs de temas asociados
        if (entity.getTopicResources() != null && !entity.getTopicResources().isEmpty()) {
            dto.setTopicIds(entity.getTopicResources().stream()
                    .map(topicResource -> topicResource.getIdTema())
                    .collect(Collectors.toList()));
        } else if (entity.getIdTema() != null) {
            // Si no hay relaciones en topicResources pero hay idTema directo
            dto.setTopicIds(List.of(entity.getIdTema()));
        }
        
        return dto;
    }
}
