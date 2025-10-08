package com.icheha.aprendia_api.exercises.templates.services.impl;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateInstructionMediaResponseDto;
import com.icheha.aprendia_api.exercises.templates.services.ITemplateInstructionMediaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateInstructionMediaServiceImpl implements ITemplateInstructionMediaService {
    
    @Override
    public TemplateInstructionMediaResponseDto createTemplateInstructionMedia(CreateTemplateInstructionMediaDto createTemplateInstructionMediaDto) {
        TemplateInstructionMediaResponseDto templateInstructionMedia = new TemplateInstructionMediaResponseDto();
        templateInstructionMedia.setId(1L);
        templateInstructionMedia.setTemplateId(createTemplateInstructionMediaDto.getTemplateId());
        templateInstructionMedia.setInstructionMediaId(createTemplateInstructionMediaDto.getInstructionMediaId());
        templateInstructionMedia.setMediaUrl(createTemplateInstructionMediaDto.getMediaUrl());
        templateInstructionMedia.setOrder(createTemplateInstructionMediaDto.getOrder());
        return templateInstructionMedia;
    }
    
    @Override
    public List<TemplateInstructionMediaResponseDto> getAllTemplateInstructionMedias() {
        List<TemplateInstructionMediaResponseDto> templateInstructionMedias = new ArrayList<>();
        
        TemplateInstructionMediaResponseDto templateInstructionMedia1 = new TemplateInstructionMediaResponseDto();
        templateInstructionMedia1.setId(1L);
        templateInstructionMedia1.setTemplateId(1L);
        templateInstructionMedia1.setInstructionMediaId(1L);
        templateInstructionMedia1.setMediaUrl("http://example.com/media1");
        templateInstructionMedia1.setOrder(1);
        templateInstructionMedias.add(templateInstructionMedia1);
        
        return templateInstructionMedias;
    }
}
