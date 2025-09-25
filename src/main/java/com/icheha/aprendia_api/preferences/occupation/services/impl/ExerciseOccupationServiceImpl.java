package com.icheha.aprendia_api.preferences.occupation.services.impl;

import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.CreateExerciseOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.response.ExerciseOccupationResponseDto;
import com.icheha.aprendia_api.preferences.occupation.services.IExerciseOccupationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseOccupationServiceImpl implements IExerciseOccupationService {
    
    @Override
    public ExerciseOccupationResponseDto create(CreateExerciseOccupationDto createDto) {
        // TODO: Implementar lógica de creación de relación ejercicio-ocupación
        throw new UnsupportedOperationException("Método no implementado aún");
    }
    
    @Override
    public List<ExerciseOccupationResponseDto> findByOccupationId(Long occupationId) {
        // TODO: Implementar lógica de búsqueda por ID de ocupación
        throw new UnsupportedOperationException("Método no implementado aún");
    }
    
    @Override
    public List<ExerciseOccupationResponseDto> findByExerciseId(Long exerciseId) {
        // TODO: Implementar lógica de búsqueda por ID de ejercicio
        throw new UnsupportedOperationException("Método no implementado aún");
    }
    
    @Override
    public boolean existsByExerciseIdAndOccupationId(Long exerciseId, Long occupationId) {
        // TODO: Implementar lógica de verificación de existencia
        throw new UnsupportedOperationException("Método no implementado aún");
    }
    
    @Override
    public void deleteByExerciseIdAndOccupationId(Long exerciseId, Long occupationId) {
        // TODO: Implementar lógica de eliminación por IDs
        throw new UnsupportedOperationException("Método no implementado aún");
    }
    
    @Override
    public void deleteByExerciseId(Long exerciseId) {
        // TODO: Implementar lógica de eliminación por ID de ejercicio
        throw new UnsupportedOperationException("Método no implementado aún");
    }
    
    @Override
    public void deleteByOccupationId(Long occupationId) {
        // TODO: Implementar lógica de eliminación por ID de ocupación
        throw new UnsupportedOperationException("Método no implementado aún");
    }
}
