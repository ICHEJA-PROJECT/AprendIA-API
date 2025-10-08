package com.icheha.aprendia_api.exercises.templates.services.impl;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.GetSkillsByTemplatesDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.SkillResponseDto;
import com.icheha.aprendia_api.exercises.templates.data.entities.SkillEntity;
import com.icheha.aprendia_api.exercises.templates.data.repositories.SkillRepository;
import com.icheha.aprendia_api.exercises.templates.services.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements ISkillService {
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Override
    public SkillResponseDto createSkill(CreateSkillDto createSkillDto) {
        // Crear nueva entidad
        SkillEntity entity = new SkillEntity();
        entity.setNombre(createSkillDto.getName());
        
        // Guardar en la base de datos
        SkillEntity savedEntity = skillRepository.save(entity);
        
        // Convertir a DTO de respuesta
        return toResponseDto(savedEntity);
    }
    
    @Override
    public List<SkillResponseDto> getAllSkills() {
        List<SkillEntity> entities = skillRepository.findAll();
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
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
        return dto;
    }
}
