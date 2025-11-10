package com.icheha.aprendia_api.exercises.templates.services;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateSkillResponseDto;

import java.util.List;

public interface ITemplateSkillService {
    
    TemplateSkillResponseDto createTemplateSkill(CreateTemplateSkillDto createTemplateSkillDto);
    
    List<TemplateSkillResponseDto> getAllTemplateSkills();
}
