package com.icheha.aprendia_api.preferences.impairments.services;

import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.CreateResourceImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.ResourceImpairmentResponseDto;

import java.util.List;

public interface IResourceImpairmentService {
    ResourceImpairmentResponseDto create(CreateResourceImpairmentDto dto);
    List<ResourceImpairmentResponseDto> findAll();
    List<ResourceImpairmentResponseDto> findByResource(Long resourceId);
    List<ResourceImpairmentResponseDto> findByImpairment(Long impairmentId);
    List<ResourceImpairmentResponseDto> findByLearningPath(Long learningPathId);
}

