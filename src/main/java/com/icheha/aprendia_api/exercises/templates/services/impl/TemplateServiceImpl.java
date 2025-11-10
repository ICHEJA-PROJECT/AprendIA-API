package com.icheha.aprendia_api.exercises.templates.services.impl;

import com.icheha.aprendia_api.exercises.layouts.data.entities.LayoutEntity;
import com.icheha.aprendia_api.exercises.layouts.data.repositories.LayoutRepository;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.GetTemplatesByTopicsDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateResponseDto;
import com.icheha.aprendia_api.exercises.templates.data.entities.TemplateEntity;
import com.icheha.aprendia_api.exercises.templates.data.repositories.ITemplateRepository;
import com.icheha.aprendia_api.exercises.templates.services.ITemplateService;
import com.icheha.aprendia_api.exercises.topics.data.entities.TopicEntity;
import com.icheha.aprendia_api.exercises.topics.data.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TemplateServiceImpl implements ITemplateService {
    
    @Autowired
    private ITemplateRepository templateRepository;
    
    @Autowired
    private LayoutRepository layoutRepository;
    
    @Autowired
    private TopicRepository topicRepository;
    
    @Override
    public TemplateResponseDto createTemplate(CreateTemplateDto createTemplateDto) {
        // Validar que el layout existe
        Optional<LayoutEntity> layoutOpt = layoutRepository.findById(createTemplateDto.getLayout());
        if (layoutOpt.isEmpty()) {
            throw new RuntimeException("Layout no encontrado con ID: " + createTemplateDto.getLayout());
        }
        
        // Validar que el topic existe
        Optional<TopicEntity> topicOpt = topicRepository.findById(createTemplateDto.getTopic());
        if (topicOpt.isEmpty()) {
            throw new RuntimeException("Topic no encontrado con ID: " + createTemplateDto.getTopic());
        }
        
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
        
        // Guardar en la base de datos
        TemplateEntity savedEntity = templateRepository.save(entity);
        
        // Convertir a DTO de respuesta
        return toResponseDto(savedEntity);
    }
    
    @Override
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
    public TemplateResponseDto getTemplateById(Integer id) {
        Optional<TemplateEntity> entityOpt = templateRepository.findById(id.longValue());
        if (entityOpt.isEmpty()) {
            throw new RuntimeException("Template no encontrado con ID: " + id);
        }
        return toResponseDto(entityOpt.get());
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