package com.icheha.aprendia_api.exercises.exercises.services.impl;

import com.icheha.aprendia_api.exercises.exercises.data.dtos.request.CreateExerciseDto;
import com.icheha.aprendia_api.exercises.exercises.data.dtos.response.ExerciseResponseDto;
import com.icheha.aprendia_api.exercises.exercises.services.IExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements IExerciseService {
    
    @Override
    public ExerciseResponseDto createExercise(CreateExerciseDto createExerciseDto) {
        // TODO: Implementar lógica de creación de ejercicio
        throw new UnsupportedOperationException("Método no implementado aún");
    }
    
    @Override
    public List<ExerciseResponseDto> getAllExercises() {
        // TODO: Implementar lógica para obtener todos los ejercicios
        throw new UnsupportedOperationException("Método no implementado aún");
    }
    
    @Override
    public Double getPercentageByIdAndSkill(Integer id, Integer skillId) {
        // TODO: Implementar lógica para obtener porcentaje por ID y habilidad
        throw new UnsupportedOperationException("Método no implementado aún");
    }
    
    @Override
    public List<ExerciseResponseDto> getExercisesByPupil(Integer id, Integer learningPathId) {
        // TODO: Implementar lógica para obtener ejercicios por estudiante
        throw new UnsupportedOperationException("Método no implementado aún");
    }
    
    @Override
    public ExerciseResponseDto getExerciseById(Integer id) {
        // TODO: Implementar lógica para obtener ejercicio por ID
        throw new UnsupportedOperationException("Método no implementado aún");
    }
    
    @Override
    public ExerciseResponseDto getRandomExerciseByTemplate(Integer id) {
        // TODO: Implementar lógica para obtener ejercicio aleatorio por plantilla
        throw new UnsupportedOperationException("Método no implementado aún");
    }
}
