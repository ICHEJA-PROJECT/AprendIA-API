package com.icheha.aprendia_api.exercises.exercises.services;

import com.icheha.aprendia_api.exercises.exercises.data.dtos.request.CreateExerciseDto;
import com.icheha.aprendia_api.exercises.exercises.data.dtos.response.ExerciseResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IExerciseService {
    
    ExerciseResponseDto createExercise(CreateExerciseDto createExerciseDto);
    
    List<ExerciseResponseDto> getAllExercises();
    
    Double getPercentageByIdAndSkill(Integer id, Integer skillId);
    
    List<ExerciseResponseDto> getExercisesByPupil(Integer id, Integer learningPathId);
    
    ExerciseResponseDto getExerciseById(Integer id);
    
    ExerciseResponseDto getRandomExerciseByTemplate(Integer id);
}
