package com.icheha.aprendia_api.preferences.impairments.services;

import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.CreateReactiveImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.ReactiveImpairmentResponseDto;

import java.util.List;

public interface IReactiveImpairmentService {
    ReactiveImpairmentResponseDto create(CreateReactiveImpairmentDto dto);
    List<ReactiveImpairmentResponseDto> findAll();
    List<ReactiveImpairmentResponseDto> findByReactive(Long reactiveId);
    List<ReactiveImpairmentResponseDto> findByImpairment(Long impairmentId);
    List<ReactiveImpairmentResponseDto> findByLearningPath(Long learningPathId);
}

