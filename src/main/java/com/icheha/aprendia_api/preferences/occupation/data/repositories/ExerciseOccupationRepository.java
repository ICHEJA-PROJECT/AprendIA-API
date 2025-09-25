package com.icheha.aprendia_api.preferences.occupation.data.repositories;

import com.icheha.aprendia_api.preferences.occupation.data.entities.ExerciseOccupationEntity;
import com.icheha.aprendia_api.preferences.occupation.data.entities.ExerciseOccupationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseOccupationRepository extends JpaRepository<ExerciseOccupationEntity, ExerciseOccupationId> {
    
    /**
     * Buscar todos los ejercicios de una ocupación específica
     */
    @Query("SELECT eo FROM ExerciseOccupationEntity eo WHERE eo.occupationId = :occupationId")
    List<ExerciseOccupationEntity> findByOccupationId(@Param("occupationId") Long occupationId);
    
    /**
     * Buscar todas las ocupaciones de un ejercicio específico
     */
    @Query("SELECT eo FROM ExerciseOccupationEntity eo WHERE eo.exerciseId = :exerciseId")
    List<ExerciseOccupationEntity> findByExerciseId(@Param("exerciseId") Long exerciseId);
    
    /**
     * Verificar si existe la relación entre ejercicio y ocupación
     */
    boolean existsByExerciseIdAndOccupationId(Long exerciseId, Long occupationId);
    
    /**
     * Eliminar todas las relaciones de un ejercicio específico
     */
    void deleteByExerciseId(Long exerciseId);
    
    /**
     * Eliminar todas las relaciones de una ocupación específica
     */
    void deleteByOccupationId(Long occupationId);
}
