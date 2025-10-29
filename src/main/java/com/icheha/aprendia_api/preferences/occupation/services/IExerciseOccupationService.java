package com.icheha.aprendia_api.preferences.occupation.services;

import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.CreateExerciseOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.response.ExerciseOccupationResponseDto;

import java.util.List;

public interface IExerciseOccupationService {
    
    List<Object> getAllExerciseOccupations();
    
    ExerciseOccupationResponseDto create(CreateExerciseOccupationDto dto);
    
    List<ExerciseOccupationResponseDto> findByOccupationId(Long occupationId);
    
    List<ExerciseOccupationResponseDto> findByExerciseId(Long exerciseId);
    
    void deleteByExerciseIdAndOccupationId(Long exerciseId, Long occupationId);
    
    boolean existsByExerciseIdAndOccupationId(Long exerciseId, Long occupationId);
}
