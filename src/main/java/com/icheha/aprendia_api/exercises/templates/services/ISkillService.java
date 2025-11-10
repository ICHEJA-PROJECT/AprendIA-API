package com.icheha.aprendia_api.exercises.templates.services;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.GetSkillsByTemplatesDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.SkillResponseDto;

import java.util.List;

public interface ISkillService {
    
    SkillResponseDto createSkill(CreateSkillDto createSkillDto);
    
    List<SkillResponseDto> getAllSkills();
    
    List<SkillResponseDto> getSkillsByTemplates(GetSkillsByTemplatesDto getSkillsByTemplatesDto);
}
