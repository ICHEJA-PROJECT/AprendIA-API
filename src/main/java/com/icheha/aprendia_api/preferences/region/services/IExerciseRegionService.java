package com.icheha.aprendia_api.preferences.region.services;

import com.icheha.aprendia_api.preferences.region.data.dtos.request.CreateExerciseRegionDto;
import com.icheha.aprendia_api.preferences.region.data.dtos.response.ExerciseRegionResponseDto;

import java.util.List;

public interface IExerciseRegionService {
    ExerciseRegionResponseDto create(CreateExerciseRegionDto dto);
    List<ExerciseRegionResponseDto> findByExercise(Long exerciseId);
    List<Long> findByRegionOnlyIds(Long regionId);
    List<ExerciseRegionResponseDto> findByRegion(Long regionId);
}

