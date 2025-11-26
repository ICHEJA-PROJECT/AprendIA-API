package com.icheha.aprendia_api.exercises.templates.services.impl;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.GetSkillsByTemplatesDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.UpdateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.SkillResponseDto;
import com.icheha.aprendia_api.exercises.templates.data.entities.SkillEntity;
import com.icheha.aprendia_api.exercises.templates.data.repositories.SkillRepository;
import com.icheha.aprendia_api.exercises.templates.services.ISkillService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements ISkillService {
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Override
    @Transactional
    public SkillResponseDto createSkill(CreateSkillDto createSkillDto) {
        // Crear nueva entidad
        SkillEntity entity = new SkillEntity();
        entity.setNombre(createSkillDto.getName());
        entity.setIdAgente(createSkillDto.getIdAgente());
        entity.setIdCategoria(createSkillDto.getIdCategoria());
        entity.setDescripcion(createSkillDto.getDescription());
        
        // Guardar en la base de datos
        SkillEntity savedEntity = skillRepository.save(entity);
        
        // Convertir a DTO de respuesta
        return toResponseDto(savedEntity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SkillResponseDto> getAllSkills() {
        List<SkillEntity> entities = skillRepository.findAll();
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<SkillResponseDto> findById(Long id) {
        return skillRepository.findById(id)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional
    public SkillResponseDto update(Long id, UpdateSkillDto updateSkillDto) {
        SkillEntity entity = skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con ID: " + id));
        
        if (updateSkillDto.getName() != null && !updateSkillDto.getName().trim().isEmpty()) {
            entity.setNombre(updateSkillDto.getName());
        }
        
        if (updateSkillDto.getUrl() != null) {
            entity.setUrl(updateSkillDto.getUrl());
        }
        
        if (updateSkillDto.getTipo() != null) {
            try {
                entity.setTipo(SkillEntity.TipoHabilidadEnum.valueOf(updateSkillDto.getTipo()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Tipo de habilidad inválido: " + updateSkillDto.getTipo());
            }
        }
        
        if (updateSkillDto.getIdAgente() != null) {
            entity.setIdAgente(updateSkillDto.getIdAgente());
        }
        
        if (updateSkillDto.getIdCategoria() != null) {
            entity.setIdCategoria(updateSkillDto.getIdCategoria());
        }
        
        if (updateSkillDto.getDescription() != null) {
            entity.setDescripcion(updateSkillDto.getDescription());
        }
        
        SkillEntity updatedEntity = skillRepository.save(entity);
        return toResponseDto(updatedEntity);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        SkillEntity entity = skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con ID: " + id));
        skillRepository.delete(entity);
    }
    
    @Override
    public List<SkillResponseDto> getSkillsByTemplates(GetSkillsByTemplatesDto getSkillsByTemplatesDto) {
        List<Long> templateIds = getSkillsByTemplatesDto.getTemplateIds().stream()
                .map(id -> id.longValue())
                .collect(Collectors.toList());
        
        List<SkillEntity> entities = skillRepository.findByTemplateIds(templateIds);
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    // Método helper para convertir entidad a DTO
    private SkillResponseDto toResponseDto(SkillEntity entity) {
        SkillResponseDto dto = new SkillResponseDto();
        dto.setId(entity.getIdHabilidad());
        dto.setName(entity.getNombre());
        dto.setIdAgente(entity.getIdAgente());
        dto.setIdCategoria(entity.getIdCategoria());
        dto.setDescription(entity.getDescripcion());
        
        // Si no hay descripción directa pero hay categoría relacionada, usar su descripción
        if (dto.getDescription() == null && entity.getCategoria() != null && entity.getCategoria().getDescripcion() != null) {
            dto.setDescription(entity.getCategoria().getDescripcion());
        }
        
        return dto;
    }
}
