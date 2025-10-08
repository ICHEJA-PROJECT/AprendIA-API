package com.icheha.aprendia_api.preferences.region.data.repositories;

import com.icheha.aprendia_api.preferences.region.data.entities.ExerciseRegionEntity;
import com.icheha.aprendia_api.preferences.region.domain.ExerciseRegionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRegionRepository extends JpaRepository<ExerciseRegionEntity, ExerciseRegionId> {
    
    /**
     * Buscar todas las regiones de un ejercicio específico
     */
    @Query("SELECT er FROM ExerciseRegionEntity er WHERE er.exerciseId = :exerciseId")
    List<ExerciseRegionEntity> findByExerciseId(@Param("exerciseId") Long exerciseId);
    
    /**
     * Buscar todos los ejercicios de una región específica
     */
    @Query("SELECT er FROM ExerciseRegionEntity er WHERE er.regionId = :regionId")
    List<ExerciseRegionEntity> findByRegionId(@Param("regionId") Long regionId);
    
    /**
     * Verificar si existe la relación entre ejercicio y región
     */
    boolean existsByExerciseIdAndRegionId(Long exerciseId, Long regionId);
    
    /**
     * Eliminar todas las relaciones de un ejercicio específico
     */
    void deleteByExerciseId(Long exerciseId);
    
    /**
     * Eliminar todas las relaciones de una región específica
     */
    void deleteByRegionId(Long regionId);
    
    /**
     * Contar ejercicios por región
     */
    @Query("SELECT COUNT(er) FROM ExerciseRegionEntity er WHERE er.regionId = :regionId")
    Long countByRegionId(@Param("regionId") Long regionId);
    
    /**
     * Contar regiones por ejercicio
     */
    @Query("SELECT COUNT(er) FROM ExerciseRegionEntity er WHERE er.exerciseId = :exerciseId")
    Long countByExerciseId(@Param("exerciseId") Long exerciseId);
}

