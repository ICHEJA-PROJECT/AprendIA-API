package com.icheha.aprendia_api.preferences.occupation.domain.repositories;

import com.icheha.aprendia_api.preferences.occupation.domain.entities.StudentOccupation;

import java.util.List;

/**
 * Interface del repositorio de dominio para StudentOccupation
 * Define los métodos de acceso a datos desde la capa de dominio
 */
public interface IStudentOccupationRepository {
    
    /**
     * Guardar una nueva relación estudiante-ocupación
     * @param studentOccupation Relación a guardar
     * @return Relación guardada
     */
    StudentOccupation save(StudentOccupation studentOccupation);
    
    /**
     * Buscar todas las ocupaciones de un estudiante específico
     * @param studentId ID del estudiante
     * @return Lista de relaciones estudiante-ocupación
     */
    List<StudentOccupation> findByStudentId(Long studentId);
    
    /**
     * Buscar todos los estudiantes de una ocupación específica
     * @param occupationId ID de la ocupación
     * @return Lista de relaciones estudiante-ocupación
     */
    List<StudentOccupation> findByOccupationId(Long occupationId);
    
    /**
     * Verificar si existe la relación entre estudiante y ocupación
     * @param studentId ID del estudiante
     * @param occupationId ID de la ocupación
     * @return true si existe, false en caso contrario
     */
    boolean existsByStudentIdAndOccupationId(Long studentId, Long occupationId);
    
    /**
     * Eliminar relación por IDs
     * @param studentId ID del estudiante
     * @param occupationId ID de la ocupación
     */
    void deleteByStudentIdAndOccupationId(Long studentId, Long occupationId);
    
    /**
     * Eliminar todas las relaciones de un estudiante específico
     * @param studentId ID del estudiante
     */
    void deleteByStudentId(Long studentId);
    
    /**
     * Eliminar todas las relaciones de una ocupación específica
     * @param occupationId ID de la ocupación
     */
    void deleteByOccupationId(Long occupationId);
}
