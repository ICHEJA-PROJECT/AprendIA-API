package com.icheha.aprendia_api.preferences.occupation.services;

import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.CreateExerciseOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.response.ExerciseOccupationResponseDto;

import java.util.List;

/**
 * Interface del servicio de ExerciseOccupation
 * Define los métodos principales para gestión de relaciones ejercicio-ocupación
 */
public interface IExerciseOccupationService {
    
    /**
     * Crear una nueva relación ejercicio-ocupación
     * @param createDto Datos para crear la relación
     * @return Relación creada
     */
    ExerciseOccupationResponseDto create(CreateExerciseOccupationDto createDto);
    
    /**
     * Buscar todos los ejercicios de una ocupación específica
     * @param occupationId ID de la ocupación
     * @return Lista de relaciones ejercicio-ocupación
     */
    List<ExerciseOccupationResponseDto> findByOccupationId(Long occupationId);
    
    /**
     * Buscar todas las ocupaciones de un ejercicio específico
     * @param exerciseId ID del ejercicio
     * @return Lista de relaciones ejercicio-ocupación
     */
    List<ExerciseOccupationResponseDto> findByExerciseId(Long exerciseId);
    
    /**
     * Verificar si existe la relación entre ejercicio y ocupación
     * @param exerciseId ID del ejercicio
     * @param occupationId ID de la ocupación
     * @return true si existe, false en caso contrario
     */
    boolean existsByExerciseIdAndOccupationId(Long exerciseId, Long occupationId);
    
    /**
     * Eliminar relación por IDs
     * @param exerciseId ID del ejercicio
     * @param occupationId ID de la ocupación
     */
    void deleteByExerciseIdAndOccupationId(Long exerciseId, Long occupationId);
    
    /**
     * Eliminar todas las relaciones de un ejercicio específico
     * @param exerciseId ID del ejercicio
     */
    void deleteByExerciseId(Long exerciseId);
    
    /**
     * Eliminar todas las relaciones de una ocupación específica
     * @param occupationId ID de la ocupación
     */
    void deleteByOccupationId(Long occupationId);
}
