package com.icheha.aprendia_api.exercises.templates.services.impl;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.UpdateTemplateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateSkillResponseDto;
import com.icheha.aprendia_api.exercises.templates.data.entities.pivots.TemplateSkillEntity;
import com.icheha.aprendia_api.exercises.templates.data.repositories.TemplateSkillRepository;
import com.icheha.aprendia_api.exercises.templates.data.repositories.TemplateRepository;
import com.icheha.aprendia_api.exercises.templates.data.repositories.SkillRepository;
import com.icheha.aprendia_api.exercises.templates.services.ITemplateSkillService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TemplateSkillServiceImpl implements ITemplateSkillService {
    
    @Autowired
    private TemplateSkillRepository templateSkillRepository;
    
    @Autowired
    private TemplateRepository templateRepository;
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Override
    @Transactional
    public TemplateSkillResponseDto createTemplateSkill(CreateTemplateSkillDto createTemplateSkillDto) {
        // Validar que el template existe
        templateRepository.findById(createTemplateSkillDto.getTemplateId())
                .orElseThrow(() -> new EntityNotFoundException("Template no encontrado con ID: " + createTemplateSkillDto.getTemplateId()));
        
        // Validar que la skill existe
        skillRepository.findById(createTemplateSkillDto.getSkillId())
                .orElseThrow(() -> new EntityNotFoundException("Skill no encontrada con ID: " + createTemplateSkillDto.getSkillId()));
        
        // Verificar si ya existe la relación
        Optional<TemplateSkillEntity> existing = templateSkillRepository.findByTemplateIdAndSkillId(
                createTemplateSkillDto.getTemplateId(), createTemplateSkillDto.getSkillId());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("La relación entre el template y la skill ya existe");
        }
        
        // Crear nueva entidad
        TemplateSkillEntity entity = new TemplateSkillEntity();
        entity.setTemplateId(createTemplateSkillDto.getTemplateId());
        entity.setSkillId(createTemplateSkillDto.getSkillId());
        entity.setPeso(createTemplateSkillDto.getPeso());
        
        // Guardar en la base de datos
        TemplateSkillEntity savedEntity = templateSkillRepository.save(entity);
        
        // Cargar relaciones para obtener nombres
        TemplateSkillEntity entityWithRelations = templateSkillRepository.findByTemplateIdAndSkillId(
                savedEntity.getTemplateId(), savedEntity.getSkillId())
                .orElse(savedEntity);
        
        return toResponseDto(entityWithRelations);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TemplateSkillResponseDto> getAllTemplateSkills() {
        List<TemplateSkillEntity> entities = templateSkillRepository.findAllWithRelations();
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TemplateSkillResponseDto> findById(Long templateId, Long skillId) {
        return templateSkillRepository.findByTemplateIdAndSkillId(templateId, skillId)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional
    public TemplateSkillResponseDto update(Long templateId, Long skillId, UpdateTemplateSkillDto updateTemplateSkillDto) {
        TemplateSkillEntity entity = templateSkillRepository.findByTemplateIdAndSkillId(templateId, skillId)
                .orElseThrow(() -> new EntityNotFoundException("Relación template-skill no encontrada"));
        
        boolean needsRecreate = false;
        Long newTemplateId = templateId;
        Long newSkillId = skillId;
        
        // Si se actualiza el template, validar que existe
        if (updateTemplateSkillDto.getTemplateId() != null && !updateTemplateSkillDto.getTemplateId().equals(templateId)) {
            templateRepository.findById(updateTemplateSkillDto.getTemplateId())
                    .orElseThrow(() -> new EntityNotFoundException("Template no encontrado con ID: " + updateTemplateSkillDto.getTemplateId()));
            newTemplateId = updateTemplateSkillDto.getTemplateId();
            needsRecreate = true;
        }
        
        // Si se actualiza la skill, validar que existe
        if (updateTemplateSkillDto.getSkillId() != null && !updateTemplateSkillDto.getSkillId().equals(skillId)) {
            skillRepository.findById(updateTemplateSkillDto.getSkillId())
                    .orElseThrow(() -> new EntityNotFoundException("Skill no encontrada con ID: " + updateTemplateSkillDto.getSkillId()));
            newSkillId = updateTemplateSkillDto.getSkillId();
            needsRecreate = true;
        }
        
        // Si se actualiza el peso
        if (updateTemplateSkillDto.getPeso() != null) {
            entity.setPeso(updateTemplateSkillDto.getPeso());
        }
        
        // Si se cambió algún ID de la clave compuesta, necesitamos recrear la entidad
        if (needsRecreate) {
            // Verificar si la nueva relación ya existe
            Optional<TemplateSkillEntity> existing = templateSkillRepository.findByTemplateIdAndSkillId(
                    newTemplateId, newSkillId);
            if (existing.isPresent()) {
                throw new IllegalArgumentException("La relación entre el nuevo template y la skill ya existe");
            }
            
            // Eliminar la relación antigua
            templateSkillRepository.delete(entity);
            
            // Crear nueva relación
            TemplateSkillEntity newEntity = new TemplateSkillEntity();
            newEntity.setTemplateId(newTemplateId);
            newEntity.setSkillId(newSkillId);
            newEntity.setPeso(entity.getPeso());
            entity = templateSkillRepository.save(newEntity);
        } else {
            entity = templateSkillRepository.save(entity);
        }
        
        // Cargar relaciones para obtener nombres
        TemplateSkillEntity entityWithRelations = templateSkillRepository.findByTemplateIdAndSkillId(
                entity.getTemplateId(), entity.getSkillId())
                .orElse(entity);
        
        return toResponseDto(entityWithRelations);
    }
    
    @Override
    @Transactional
    public void delete(Long templateId, Long skillId) {
        TemplateSkillEntity entity = templateSkillRepository.findByTemplateIdAndSkillId(templateId, skillId)
                .orElseThrow(() -> new EntityNotFoundException("Relación template-skill no encontrada"));
        templateSkillRepository.delete(entity);
    }
    
    private TemplateSkillResponseDto toResponseDto(TemplateSkillEntity entity) {
        TemplateSkillResponseDto dto = new TemplateSkillResponseDto();
        dto.setId(entity.getTemplateId() * 1000L + entity.getSkillId()); // ID compuesto para compatibilidad
        dto.setTemplateId(entity.getTemplateId());
        dto.setSkillId(entity.getSkillId());
        dto.setTemplateName(entity.getTemplate() != null ? entity.getTemplate().getTitulo() : null);
        dto.setSkillName(entity.getSkill() != null ? entity.getSkill().getNombre() : null);
        return dto;
    }
}
