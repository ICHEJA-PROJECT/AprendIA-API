package com.icheha.aprendia_api.preferences.occupation.domain.repositories;

import com.icheha.aprendia_api.preferences.occupation.domain.entities.ExerciseOccupation;

import java.util.List;

/**
 * Interface del repositorio de dominio para ExerciseOccupation
 * Define los métodos de acceso a datos desde la capa de dominio
 */
public interface IExerciseOccupationRepository {
    
    /**
     * Guardar una nueva relación ejercicio-ocupación
     * @param exerciseOccupation Relación a guardar
     * @return Relación guardada
     */
    ExerciseOccupation save(ExerciseOccupation exerciseOccupation);
    
    /**
     * Buscar todos los ejercicios de una ocupación específica
     * @param occupationId ID de la ocupación
     * @return Lista de relaciones ejercicio-ocupación
     */
    List<ExerciseOccupation> findByOccupationId(Long occupationId);
    
    /**
     * Buscar todas las ocupaciones de un ejercicio específico
     * @param exerciseId ID del ejercicio
     * @return Lista de relaciones ejercicio-ocupación
     */
    List<ExerciseOccupation> findByExerciseId(Long exerciseId);
    
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
