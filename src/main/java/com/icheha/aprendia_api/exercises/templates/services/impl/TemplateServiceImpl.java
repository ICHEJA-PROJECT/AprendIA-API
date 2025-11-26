package com.icheha.aprendia_api.exercises.templates.services.impl;

import com.icheha.aprendia_api.exercises.layouts.data.repositories.LayoutRepository;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.GetTemplatesByTopicsDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.UpdateTemplateDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateResponseDto;
import com.icheha.aprendia_api.exercises.templates.data.entities.TemplateEntity;
import com.icheha.aprendia_api.exercises.templates.data.repositories.ITemplateRepository;
import com.icheha.aprendia_api.exercises.templates.services.ITemplateService;
import com.icheha.aprendia_api.exercises.topics.data.repositories.TopicRepository;
import com.icheha.aprendia_api.exercises.topics.data.repositories.ResourceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemplateServiceImpl implements ITemplateService {
    
    @Autowired
    private ITemplateRepository templateRepository;
    
    @Autowired
    private LayoutRepository layoutRepository;
    
    @Autowired
    private TopicRepository topicRepository;
    
    @Autowired
    private ResourceRepository resourceRepository;
    
    @Override
    @Transactional
    public TemplateResponseDto createTemplate(CreateTemplateDto createTemplateDto) {
        // Validar que el layout existe
        layoutRepository.findById(createTemplateDto.getLayout())
                .orElseThrow(() -> new EntityNotFoundException("Layout no encontrado con ID: " + createTemplateDto.getLayout()));
        
        // Validar que el topic existe
        topicRepository.findById(createTemplateDto.getTopic())
                .orElseThrow(() -> new EntityNotFoundException("Topic no encontrado con ID: " + createTemplateDto.getTopic()));
        
        // Crear nueva entidad
        TemplateEntity entity = new TemplateEntity();
        entity.setTitulo(createTemplateDto.getTitle());
        entity.setInstrucciones(createTemplateDto.getInstructions());
        // Convertir tiempo sugerido de String a Integer si es posible
        try {
            if (createTemplateDto.getSuggestTime() != null && !createTemplateDto.getSuggestTime().isEmpty()) {
                entity.setTiempoSugerido(Integer.parseInt(createTemplateDto.getSuggestTime().replaceAll("[^0-9]", "")));
            }
        } catch (NumberFormatException e) {
            entity.setTiempoSugerido(null);
        }
        entity.setTopicId(createTemplateDto.getTopic());
        entity.setLayoutId(createTemplateDto.getLayout());
        entity.setUrlImagen(createTemplateDto.getUrlImagen());
        
        // Guardar en la base de datos
        TemplateEntity savedEntity = templateRepository.save(entity);
        
        // Convertir a DTO de respuesta
        return toResponseDto(savedEntity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TemplateResponseDto> getAllTemplates() {
        List<TemplateEntity> entities = templateRepository.findAll();
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<TemplateResponseDto> getTemplatesByTopicId(Integer topicId) {
        List<TemplateEntity> entities = templateRepository.findByTopicId(topicId.longValue());
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<TemplateResponseDto> getTemplatesByTopics(GetTemplatesByTopicsDto getTemplatesByTopicsDto) {
        List<Long> topicIds = getTemplatesByTopicsDto.getTopicIds().stream()
                .map(id -> id.longValue())
                .collect(Collectors.toList());
        
        List<TemplateEntity> entities = templateRepository.findByTopicIds(topicIds);
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public TemplateResponseDto getTemplateById(Long id) {
        TemplateEntity entity = templateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Template no encontrado con ID: " + id));
        return toResponseDto(entity);
    }
    
    @Override
    @Transactional
    public TemplateResponseDto update(Long id, UpdateTemplateDto updateTemplateDto) {
        TemplateEntity entity = templateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Template no encontrado con ID: " + id));
        
        if (updateTemplateDto.getTitle() != null && !updateTemplateDto.getTitle().trim().isEmpty()) {
            entity.setTitulo(updateTemplateDto.getTitle());
        }
        
        if (updateTemplateDto.getInstructions() != null) {
            entity.setInstrucciones(updateTemplateDto.getInstructions());
        }
        
        if (updateTemplateDto.getSuggestTime() != null) {
            entity.setTiempoSugerido(updateTemplateDto.getSuggestTime());
        }
        
        if (updateTemplateDto.getLayoutId() != null) {
            layoutRepository.findById(updateTemplateDto.getLayoutId())
                    .orElseThrow(() -> new EntityNotFoundException("Layout no encontrado con ID: " + updateTemplateDto.getLayoutId()));
            entity.setLayoutId(updateTemplateDto.getLayoutId());
        }
        
        if (updateTemplateDto.getTopicId() != null) {
            topicRepository.findById(updateTemplateDto.getTopicId())
                    .orElseThrow(() -> new EntityNotFoundException("Tema no encontrado con ID: " + updateTemplateDto.getTopicId()));
            entity.setTopicId(updateTemplateDto.getTopicId());
        }
        
        if (updateTemplateDto.getResourceId() != null) {
            resourceRepository.findById(updateTemplateDto.getResourceId())
                    .orElseThrow(() -> new EntityNotFoundException("Recurso no encontrado con ID: " + updateTemplateDto.getResourceId()));
            entity.setIdRecurso(updateTemplateDto.getResourceId());
        }
        
        if (updateTemplateDto.getUrlImagen() != null) {
            entity.setUrlImagen(updateTemplateDto.getUrlImagen());
        }
        
        TemplateEntity updatedEntity = templateRepository.save(entity);
        return toResponseDto(updatedEntity);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        TemplateEntity entity = templateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Template no encontrado con ID: " + id));
        templateRepository.delete(entity);
    }
    
    // Método helper para convertir entidad a DTO
    private TemplateResponseDto toResponseDto(TemplateEntity entity) {
        TemplateResponseDto dto = new TemplateResponseDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitulo());
        dto.setInstructions(entity.getInstrucciones());
        dto.setSuggestTime(entity.getTiempoSugerido() != null ? entity.getTiempoSugerido().toString() : null);
        dto.setLayoutId(entity.getLayoutId());
        dto.setTopicId(entity.getTopicId());
        dto.setUrlImagen(entity.getUrlImagen());
        
        // Obtener nombres de layout y topic si están disponibles
        if (entity.getLayout() != null) {
            dto.setLayoutName(entity.getLayout().getNombre());
        }
        
        if (entity.getTema() != null) {
            dto.setTopicName(entity.getTema().getNombre());
        }
        
        // TODO: Implementar attributes cuando esté disponible en la entidad
        dto.setAttributes(java.util.Map.of());
        
        return dto;
    }
}