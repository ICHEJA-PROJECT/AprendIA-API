package com.icheha.aprendia_api.preferences.region.domain.repositories;

import com.icheha.aprendia_api.preferences.region.domain.entities.ExerciseRegion;

import java.util.List;

/**
 * Interface del repositorio de dominio para ExerciseRegion
 * Define los métodos de acceso a datos desde la capa de dominio
 */
public interface IExerciseRegionRepository {
    
    /**
     * Guardar una nueva relación ejercicio-región
     * @param exerciseRegion Relación a guardar
     * @return Relación guardada
     */
    ExerciseRegion save(ExerciseRegion exerciseRegion);
    
    /**
     * Buscar todas las regiones de un ejercicio específico
     * @param exerciseId ID del ejercicio
     * @return Lista de relaciones ejercicio-región
     */
    List<ExerciseRegion> findByExerciseId(Long exerciseId);
    
    /**
     * Buscar todos los ejercicios de una región específica
     * @param regionId ID de la región
     * @return Lista de relaciones ejercicio-región
     */
    List<ExerciseRegion> findByRegionId(Long regionId);
    
    /**
     * Verificar si existe la relación entre ejercicio y región
     * @param exerciseId ID del ejercicio
     * @param regionId ID de la región
     * @return true si existe, false en caso contrario
     */
    boolean existsByExerciseIdAndRegionId(Long exerciseId, Long regionId);
    
    /**
     * Eliminar relación por IDs
     * @param exerciseId ID del ejercicio
     * @param regionId ID de la región
     */
    void deleteByExerciseIdAndRegionId(Long exerciseId, Long regionId);
    
    /**
     * Eliminar todas las relaciones de un ejercicio específico
     * @param exerciseId ID del ejercicio
     */
    void deleteByExerciseId(Long exerciseId);
    
    /**
     * Eliminar todas las relaciones de una región específica
     * @param regionId ID de la región
     */
    void deleteByRegionId(Long regionId);
    
    /**
     * Contar ejercicios por región
     * @param regionId ID de la región
     * @return Número de ejercicios asociados
     */
    Long countByRegionId(Long regionId);
    
    /**
     * Contar regiones por ejercicio
     * @param exerciseId ID del ejercicio
     * @return Número de regiones asociadas
     */
    Long countByExerciseId(Long exerciseId);
}
