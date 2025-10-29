package com.icheha.aprendia_api.exercises.templates.services;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateInstructionMediaResponseDto;

import java.util.List;

public interface ITemplateInstructionMediaService {
    
    TemplateInstructionMediaResponseDto createTemplateInstructionMedia(CreateTemplateInstructionMediaDto createTemplateInstructionMediaDto);
    
    List<TemplateInstructionMediaResponseDto> getAllTemplateInstructionMedias();
}
