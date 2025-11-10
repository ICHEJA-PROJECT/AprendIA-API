package com.icheha.aprendia_api.exercises.templates.services;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.UpdateTemplateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateSkillResponseDto;

import java.util.List;
import java.util.Optional;

public interface ITemplateSkillService {
    
    TemplateSkillResponseDto createTemplateSkill(CreateTemplateSkillDto createTemplateSkillDto);
    
    List<TemplateSkillResponseDto> getAllTemplateSkills();
    
    Optional<TemplateSkillResponseDto> findById(Long templateId, Long skillId);
    
    TemplateSkillResponseDto update(Long templateId, Long skillId, UpdateTemplateSkillDto updateTemplateSkillDto);
    
    void delete(Long templateId, Long skillId);
}
