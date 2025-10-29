package com.icheha.aprendia_api.exercises.templates.services;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.GetTemplatesByTopicsDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateResponseDto;

import java.util.List;

public interface ITemplateService {
    
    TemplateResponseDto createTemplate(CreateTemplateDto createTemplateDto);
    
    List<TemplateResponseDto> getAllTemplates();
    
    List<TemplateResponseDto> getTemplatesByTopicId(Integer topicId);
    
    List<TemplateResponseDto> getTemplatesByTopics(GetTemplatesByTopicsDto getTemplatesByTopicsDto);
    
    TemplateResponseDto getTemplateById(Integer id);
}