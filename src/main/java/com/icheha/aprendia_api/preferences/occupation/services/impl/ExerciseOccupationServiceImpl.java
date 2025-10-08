package com.icheha.aprendia_api.preferences.occupation.services.impl;

import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.CreateExerciseOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.response.ExerciseOccupationResponseDto;
import com.icheha.aprendia_api.preferences.occupation.services.IExerciseOccupationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseOccupationServiceImpl implements IExerciseOccupationService {
    
    @Override
    public List<Object> getAllExerciseOccupations() {
        List<Object> exerciseOccupations = new ArrayList<>();
        exerciseOccupations.add("Exercise Occupation 1");
        exerciseOccupations.add("Exercise Occupation 2");
        return exerciseOccupations;
    }
    
    @Override
    public ExerciseOccupationResponseDto create(CreateExerciseOccupationDto dto) {
        ExerciseOccupationResponseDto response = new ExerciseOccupationResponseDto();
        response.setExerciseId(dto.getExerciseId());
        response.setOccupationId(dto.getOccupationId());
        response.setOccupationName("Occupation " + dto.getOccupationId());
        return response;
    }
    
    @Override
    public List<ExerciseOccupationResponseDto> findByOccupationId(Long occupationId) {
        return new ArrayList<>();
    }
    
    @Override
    public List<ExerciseOccupationResponseDto> findByExerciseId(Long exerciseId) {
        return new ArrayList<>();
    }
    
    @Override
    public void deleteByExerciseIdAndOccupationId(Long exerciseId, Long occupationId) {
        // Implementation placeholder
    }
    
    @Override
    public boolean existsByExerciseIdAndOccupationId(Long exerciseId, Long occupationId) {
        return false;
    }
}
