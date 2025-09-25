package com.icheha.aprendia_api.preferences.occupation.data.repositories;

import com.icheha.aprendia_api.preferences.occupation.data.entities.StudentOccupationEntity;
import com.icheha.aprendia_api.preferences.occupation.data.entities.StudentOccupationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentOccupationRepository extends JpaRepository<StudentOccupationEntity, StudentOccupationId> {
    
    /**
     * Buscar todas las ocupaciones de un estudiante específico
     */
    @Query("SELECT so FROM StudentOccupationEntity so WHERE so.studentId = :studentId")
    List<StudentOccupationEntity> findByStudentId(@Param("studentId") Long studentId);
    
    /**
     * Buscar todos los estudiantes de una ocupación específica
     */
    @Query("SELECT so FROM StudentOccupationEntity so WHERE so.occupationId = :occupationId")
    List<StudentOccupationEntity> findByOccupationId(@Param("occupationId") Long occupationId);
    
    /**
     * Verificar si existe la relación entre estudiante y ocupación
     */
    boolean existsByStudentIdAndOccupationId(Long studentId, Long occupationId);
    
    /**
     * Eliminar todas las relaciones de un estudiante específico
     */
    void deleteByStudentId(Long studentId);
    
    /**
     * Eliminar todas las relaciones de una ocupación específica
     */
    void deleteByOccupationId(Long occupationId);
}
