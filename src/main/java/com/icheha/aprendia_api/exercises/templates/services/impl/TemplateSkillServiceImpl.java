package com.icheha.aprendia_api.exercises.templates.services.impl;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateSkillResponseDto;
import com.icheha.aprendia_api.exercises.templates.services.ITemplateSkillService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateSkillServiceImpl implements ITemplateSkillService {
    
    @Override
    public TemplateSkillResponseDto createTemplateSkill(CreateTemplateSkillDto createTemplateSkillDto) {
        TemplateSkillResponseDto templateSkill = new TemplateSkillResponseDto();
        templateSkill.setId(1L);
        templateSkill.setTemplateId(createTemplateSkillDto.getTemplateId());
        templateSkill.setSkillId(createTemplateSkillDto.getSkillId());
        return templateSkill;
    }
    
    @Override
    public List<TemplateSkillResponseDto> getAllTemplateSkills() {
        List<TemplateSkillResponseDto> templateSkills = new ArrayList<>();
        
        TemplateSkillResponseDto templateSkill1 = new TemplateSkillResponseDto();
        templateSkill1.setId(1L);
        templateSkill1.setTemplateId(1L);
        templateSkill1.setSkillId(1L);
        templateSkills.add(templateSkill1);
        
        return templateSkills;
    }
}
