package com.icheha.aprendia_api.preferences.region.data.repositories;

import com.icheha.aprendia_api.preferences.region.data.entities.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Long> {
    
    /**
     * Buscar región por nombre
     */
    Optional<RegionEntity> findByName(String name);
    
    /**
     * Verificar si existe una región con el nombre dado
     */
    boolean existsByName(String name);
    
    /**
     * Buscar regiones que contengan el nombre dado (búsqueda parcial)
     */
    @Query("SELECT r FROM RegionEntity r WHERE r.name LIKE CONCAT('%', :name, '%')")
    List<RegionEntity> findByNameContaining(@Param("name") String name);
    
    /**
     * Buscar región con sus relaciones cargadas
     */
    @Query("SELECT r FROM RegionEntity r LEFT JOIN FETCH r.students LEFT JOIN FETCH r.words LEFT JOIN FETCH r.exercises WHERE r.id = :id")
    Optional<RegionEntity> findByIdWithRelations(@Param("id") Long id);
    
    /**
     * Buscar todas las regiones con sus relaciones cargadas
     */
    @Query("SELECT r FROM RegionEntity r LEFT JOIN FETCH r.students LEFT JOIN FETCH r.words LEFT JOIN FETCH r.exercises")
    List<RegionEntity> findAllWithRelations();
    
    /**
     * Buscar regiones por estudiante
     */
    @Query("SELECT DISTINCT r FROM RegionEntity r JOIN r.students sr WHERE sr.studentId = :studentId")
    List<RegionEntity> findByStudentId(@Param("studentId") Long studentId);
    
    /**
     * Buscar regiones por palabra
     */
    @Query("SELECT DISTINCT r FROM RegionEntity r JOIN r.words wr WHERE wr.wordId = :wordId")
    List<RegionEntity> findByWordId(@Param("wordId") Long wordId);
    
    /**
     * Buscar regiones por ejercicio
     */
    @Query("SELECT DISTINCT r FROM RegionEntity r JOIN r.exercises er WHERE er.exerciseId = :exerciseId")
    List<RegionEntity> findByExerciseId(@Param("exerciseId") Long exerciseId);
}

