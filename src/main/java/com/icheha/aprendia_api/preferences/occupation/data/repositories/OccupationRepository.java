package com.icheha.aprendia_api.preferences.occupation.data.repositories;

import com.icheha.aprendia_api.preferences.occupation.data.entities.OccupationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OccupationRepository extends JpaRepository<OccupationEntity, Long> {
    
    /**
     * Buscar ocupación por nombre
     */
    Optional<OccupationEntity> findByName(String name);
    
    /**
     * Verificar si existe una ocupación con el nombre dado
     */
    boolean existsByName(String name);
    
    /**
     * Buscar ocupaciones que contengan el nombre dado (búsqueda parcial)
     */
    @Query("SELECT o FROM OccupationEntity o WHERE o.name LIKE %:name%")
    List<OccupationEntity> findByNameContaining(@Param("name") String name);
    
    /**
     * Buscar ocupación con sus relaciones cargadas
     */
    @Query("SELECT o FROM OccupationEntity o LEFT JOIN FETCH o.students LEFT JOIN FETCH o.exercises WHERE o.id = :id")
    Optional<OccupationEntity> findByIdWithRelations(@Param("id") Long id);
    
    /**
     * Buscar todas las ocupaciones con sus relaciones cargadas
     */
    @Query("SELECT o FROM OccupationEntity o LEFT JOIN FETCH o.students LEFT JOIN FETCH o.exercises")
    List<OccupationEntity> findAllWithRelations();
    
    /**
     * Buscar ocupaciones por estudiante
     */
    @Query("SELECT DISTINCT o FROM OccupationEntity o JOIN o.students so WHERE so.studentId = :studentId")
    List<OccupationEntity> findByStudentId(@Param("studentId") Long studentId);
    
    /**
     * Buscar ocupaciones por ejercicio
     */
    @Query("SELECT DISTINCT o FROM OccupationEntity o JOIN o.exercises eo WHERE eo.exerciseId = :exerciseId")
    List<OccupationEntity> findByExerciseId(@Param("exerciseId") Long exerciseId);
}
