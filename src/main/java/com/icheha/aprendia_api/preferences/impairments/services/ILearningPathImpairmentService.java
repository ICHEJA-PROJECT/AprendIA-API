package com.icheha.aprendia_api.preferences.impairments.services;

import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.CreateLearningPathImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.LearningPathImpairmentResponseDto;

import java.util.List;

public interface ILearningPathImpairmentService {
    LearningPathImpairmentResponseDto create(CreateLearningPathImpairmentDto dto);
    List<LearningPathImpairmentResponseDto> findAll();
    List<LearningPathImpairmentResponseDto> findByLearningPath(Long learningPathId);
    List<LearningPathImpairmentResponseDto> findByImpairment(Long impairmentId);
}

