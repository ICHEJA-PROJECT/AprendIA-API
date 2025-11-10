package com.icheha.aprendia_api.exercises.templates.services;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.UpdateTemplateInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateInstructionMediaResponseDto;

import java.util.List;
import java.util.Optional;

public interface ITemplateInstructionMediaService {
    
    TemplateInstructionMediaResponseDto createTemplateInstructionMedia(CreateTemplateInstructionMediaDto createTemplateInstructionMediaDto);
    
    List<TemplateInstructionMediaResponseDto> getAllTemplateInstructionMedias();
    
    Optional<TemplateInstructionMediaResponseDto> findById(Long templateId, Long typeMediaId);
    
    TemplateInstructionMediaResponseDto update(Long templateId, Long typeMediaId, UpdateTemplateInstructionMediaDto updateTemplateInstructionMediaDto);
    
    void delete(Long templateId, Long typeMediaId);
}
