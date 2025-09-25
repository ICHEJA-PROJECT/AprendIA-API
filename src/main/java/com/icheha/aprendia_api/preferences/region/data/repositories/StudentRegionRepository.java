package com.icheha.aprendia_api.preferences.region.data.repositories;

import com.icheha.aprendia_api.preferences.region.data.entities.StudentRegionEntity;
import com.icheha.aprendia_api.preferences.region.data.entities.StudentRegionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRegionRepository extends JpaRepository<StudentRegionEntity, StudentRegionId> {
    
    /**
     * Buscar todas las regiones de un estudiante específico
     */
    @Query("SELECT sr FROM StudentRegionEntity sr WHERE sr.studentId = :studentId")
    List<StudentRegionEntity> findByStudentId(@Param("studentId") Long studentId);
    
    /**
     * Buscar todos los estudiantes de una región específica
     */
    @Query("SELECT sr FROM StudentRegionEntity sr WHERE sr.regionId = :regionId")
    List<StudentRegionEntity> findByRegionId(@Param("regionId") Long regionId);
    
    /**
     * Verificar si existe la relación entre estudiante y región
     */
    boolean existsByStudentIdAndRegionId(Long studentId, Long regionId);
    
    /**
     * Eliminar todas las relaciones de un estudiante específico
     */
    void deleteByStudentId(Long studentId);
    
    /**
     * Eliminar todas las relaciones de una región específica
     */
    void deleteByRegionId(Long regionId);
    
    /**
     * Contar estudiantes por región
     */
    @Query("SELECT COUNT(sr) FROM StudentRegionEntity sr WHERE sr.regionId = :regionId")
    Long countByRegionId(@Param("regionId") Long regionId);
    
    /**
     * Contar regiones por estudiante
     */
    @Query("SELECT COUNT(sr) FROM StudentRegionEntity sr WHERE sr.studentId = :studentId")
    Long countByStudentId(@Param("studentId") Long studentId);
}
