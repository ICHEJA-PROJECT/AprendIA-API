package com.icheha.aprendia_api.exercises.templates.services.impl;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.UpdateTemplateInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateInstructionMediaResponseDto;
import com.icheha.aprendia_api.exercises.templates.data.entities.pivots.TemplateInstructionMediaEntity;
import com.icheha.aprendia_api.exercises.templates.data.repositories.TemplateInstructionMediaRepository;
import com.icheha.aprendia_api.exercises.templates.data.repositories.TemplateRepository;
import com.icheha.aprendia_api.exercises.templates.data.repositories.TypeInstructionMediaRepository;
import com.icheha.aprendia_api.exercises.templates.services.ITemplateInstructionMediaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TemplateInstructionMediaServiceImpl implements ITemplateInstructionMediaService {
    
    @Autowired
    private TemplateInstructionMediaRepository templateInstructionMediaRepository;
    
    @Autowired
    private TemplateRepository templateRepository;
    
    @Autowired
    private TypeInstructionMediaRepository typeInstructionMediaRepository;
    
    @Override
    @Transactional
    public TemplateInstructionMediaResponseDto createTemplateInstructionMedia(CreateTemplateInstructionMediaDto createTemplateInstructionMediaDto) {
        // Validar que el template existe
        templateRepository.findById(createTemplateInstructionMediaDto.getTemplateId())
                .orElseThrow(() -> new EntityNotFoundException("Template no encontrado con ID: " + createTemplateInstructionMediaDto.getTemplateId()));
        
        // Validar que el tipo de media existe
        typeInstructionMediaRepository.findById(createTemplateInstructionMediaDto.getInstructionMediaId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de instrucción media no encontrado con ID: " + createTemplateInstructionMediaDto.getInstructionMediaId()));
        
        // Verificar si ya existe la relación
        Optional<TemplateInstructionMediaEntity> existing = templateInstructionMediaRepository.findByTemplateIdAndTypeMediaId(
                createTemplateInstructionMediaDto.getTemplateId(), createTemplateInstructionMediaDto.getInstructionMediaId());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("La relación entre el template y el tipo de media ya existe");
        }
        
        // Crear nueva entidad
        TemplateInstructionMediaEntity entity = new TemplateInstructionMediaEntity();
        entity.setIdReactivo(createTemplateInstructionMediaDto.getTemplateId());
        entity.setIdTipoMedia(createTemplateInstructionMediaDto.getInstructionMediaId());
        entity.setRutaMedia(createTemplateInstructionMediaDto.getMediaUrl());
        
        // Guardar en la base de datos
        TemplateInstructionMediaEntity savedEntity = templateInstructionMediaRepository.save(entity);
        
        // Cargar relaciones para obtener nombres
        TemplateInstructionMediaEntity entityWithRelations = templateInstructionMediaRepository.findByTemplateIdAndTypeMediaId(
                savedEntity.getIdReactivo(), savedEntity.getIdTipoMedia())
                .orElse(savedEntity);
        
        return toResponseDto(entityWithRelations);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TemplateInstructionMediaResponseDto> getAllTemplateInstructionMedias() {
        List<TemplateInstructionMediaEntity> entities = templateInstructionMediaRepository.findAllWithRelations();
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TemplateInstructionMediaResponseDto> findById(Long templateId, Long typeMediaId) {
        return templateInstructionMediaRepository.findByTemplateIdAndTypeMediaId(templateId, typeMediaId)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional
    public TemplateInstructionMediaResponseDto update(Long templateId, Long typeMediaId, UpdateTemplateInstructionMediaDto updateTemplateInstructionMediaDto) {
        TemplateInstructionMediaEntity entity = templateInstructionMediaRepository.findByTemplateIdAndTypeMediaId(templateId, typeMediaId)
                .orElseThrow(() -> new EntityNotFoundException("Relación template-instruction-media no encontrada"));
        
        boolean needsRecreate = false;
        Long newTemplateId = templateId;
        Long newTypeMediaId = typeMediaId;
        
        // Si se actualiza el template, validar que existe
        if (updateTemplateInstructionMediaDto.getTemplateId() != null && !updateTemplateInstructionMediaDto.getTemplateId().equals(templateId)) {
            templateRepository.findById(updateTemplateInstructionMediaDto.getTemplateId())
                    .orElseThrow(() -> new EntityNotFoundException("Template no encontrado con ID: " + updateTemplateInstructionMediaDto.getTemplateId()));
            newTemplateId = updateTemplateInstructionMediaDto.getTemplateId();
            needsRecreate = true;
        }
        
        // Si se actualiza el tipo de media, validar que existe
        if (updateTemplateInstructionMediaDto.getTypeMediaId() != null && !updateTemplateInstructionMediaDto.getTypeMediaId().equals(typeMediaId)) {
            typeInstructionMediaRepository.findById(updateTemplateInstructionMediaDto.getTypeMediaId())
                    .orElseThrow(() -> new EntityNotFoundException("Tipo de instrucción media no encontrado con ID: " + updateTemplateInstructionMediaDto.getTypeMediaId()));
            newTypeMediaId = updateTemplateInstructionMediaDto.getTypeMediaId();
            needsRecreate = true;
        }
        
        // Si se actualiza la URL del media
        if (updateTemplateInstructionMediaDto.getMediaUrl() != null && !updateTemplateInstructionMediaDto.getMediaUrl().trim().isEmpty()) {
            entity.setRutaMedia(updateTemplateInstructionMediaDto.getMediaUrl());
        }
        
        // Si se cambió algún ID de la clave compuesta, necesitamos recrear la entidad
        if (needsRecreate) {
            // Verificar si la nueva relación ya existe
            Optional<TemplateInstructionMediaEntity> existing = templateInstructionMediaRepository.findByTemplateIdAndTypeMediaId(
                    newTemplateId, newTypeMediaId);
            if (existing.isPresent()) {
                throw new IllegalArgumentException("La relación entre el nuevo template y el tipo de media ya existe");
            }
            
            // Eliminar la relación antigua
            templateInstructionMediaRepository.delete(entity);
            
            // Crear nueva relación
            TemplateInstructionMediaEntity newEntity = new TemplateInstructionMediaEntity();
            newEntity.setIdReactivo(newTemplateId);
            newEntity.setIdTipoMedia(newTypeMediaId);
            newEntity.setRutaMedia(entity.getRutaMedia());
            entity = templateInstructionMediaRepository.save(newEntity);
        } else {
            entity = templateInstructionMediaRepository.save(entity);
        }
        
        // Cargar relaciones para obtener nombres
        TemplateInstructionMediaEntity entityWithRelations = templateInstructionMediaRepository.findByTemplateIdAndTypeMediaId(
                entity.getIdReactivo(), entity.getIdTipoMedia())
                .orElse(entity);
        
        return toResponseDto(entityWithRelations);
    }
    
    @Override
    @Transactional
    public void delete(Long templateId, Long typeMediaId) {
        TemplateInstructionMediaEntity entity = templateInstructionMediaRepository.findByTemplateIdAndTypeMediaId(templateId, typeMediaId)
                .orElseThrow(() -> new EntityNotFoundException("Relación template-instruction-media no encontrada"));
        templateInstructionMediaRepository.delete(entity);
    }
    
    private TemplateInstructionMediaResponseDto toResponseDto(TemplateInstructionMediaEntity entity) {
        TemplateInstructionMediaResponseDto dto = new TemplateInstructionMediaResponseDto();
        dto.setId(entity.getIdReactivo() * 1000L + entity.getIdTipoMedia()); // ID compuesto para compatibilidad
        dto.setTemplateId(entity.getIdReactivo());
        dto.setInstructionMediaId(entity.getIdTipoMedia());
        dto.setTemplateName(entity.getReactivo() != null ? entity.getReactivo().getTitulo() : null);
        dto.setInstructionMediaName(entity.getTipoInstruccionMedia() != null ? entity.getTipoInstruccionMedia().getNombre() : null);
        dto.setMediaUrl(entity.getRutaMedia());
        dto.setOrder(null); // No hay campo order en la entidad
        return dto;
    }
}
