package com.icheha.aprendia_api.exercises.templates.services;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.GetSkillsByTemplatesDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.UpdateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.SkillResponseDto;

import java.util.List;
import java.util.Optional;

public interface ISkillService {
    
    SkillResponseDto createSkill(CreateSkillDto createSkillDto);
    
    List<SkillResponseDto> getAllSkills();
    
    Optional<SkillResponseDto> findById(Long id);
    
    SkillResponseDto update(Long id, UpdateSkillDto updateSkillDto);
    
    void delete(Long id);
    
    List<SkillResponseDto> getSkillsByTemplates(GetSkillsByTemplatesDto getSkillsByTemplatesDto);
}
