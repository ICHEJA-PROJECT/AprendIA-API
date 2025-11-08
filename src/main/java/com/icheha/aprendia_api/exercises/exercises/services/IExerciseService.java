package com.icheha.aprendia_api.exercises.exercises.services;

import com.icheha.aprendia_api.exercises.exercises.data.dtos.request.CreateExerciseDto;
import com.icheha.aprendia_api.exercises.exercises.data.dtos.response.ExerciseResponseDto;

import java.util.List;

public interface IExerciseService {
    List<ExerciseResponseDto> getAllExercises();
    ExerciseResponseDto createExercise(CreateExerciseDto createExerciseDto);
    ExerciseResponseDto getExerciseById(Long id);
    Double getPercentageByIdAndSkill(Integer exerciseId, Integer skillId);
    List<ExerciseResponseDto> getExercisesByPupil(Integer pupilId, Integer learningPathId);
    List<ExerciseResponseDto> getExercisesByTemplateId(Long templateId);
    ExerciseResponseDto getRandomExerciseByTemplate(Integer templateId);
    List<Double> getPorcentages(Integer exerciseId);
    List<ExerciseResponseDto> findByIds(List<Integer> ids);
}
