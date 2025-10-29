package com.icheha.aprendia_api.preferences.region.domain.repositories;

import com.icheha.aprendia_api.preferences.region.domain.entities.StudentRegion;

import java.util.List;

/**
 * Interface del repositorio de dominio para StudentRegion
 * Define los métodos de acceso a datos desde la capa de dominio
 */
public interface IStudentRegionRepository {
    
    /**
     * Guardar una nueva relación estudiante-región
     * @param studentRegion Relación a guardar
     * @return Relación guardada
     */
    StudentRegion save(StudentRegion studentRegion);
    
    /**
     * Buscar todas las regiones de un estudiante específico
     * @param studentId ID del estudiante
     * @return Lista de relaciones estudiante-región
     */
    List<StudentRegion> findByStudentId(Long studentId);
    
    /**
     * Buscar todos los estudiantes de una región específica
     * @param regionId ID de la región
     * @return Lista de relaciones estudiante-región
     */
    List<StudentRegion> findByRegionId(Long regionId);
    
    /**
     * Verificar si existe la relación entre estudiante y región
     * @param studentId ID del estudiante
     * @param regionId ID de la región
     * @return true si existe, false en caso contrario
     */
    boolean existsByStudentIdAndRegionId(Long studentId, Long regionId);
    
    /**
     * Eliminar relación por IDs
     * @param studentId ID del estudiante
     * @param regionId ID de la región
     */
    void deleteByStudentIdAndRegionId(Long studentId, Long regionId);
    
    /**
     * Eliminar todas las relaciones de un estudiante específico
     * @param studentId ID del estudiante
     */
    void deleteByStudentId(Long studentId);
    
    /**
     * Eliminar todas las relaciones de una región específica
     * @param regionId ID de la región
     */
    void deleteByRegionId(Long regionId);
    
    /**
     * Contar estudiantes por región
     * @param regionId ID de la región
     * @return Número de estudiantes asociados
     */
    Long countByRegionId(Long regionId);
    
    /**
     * Contar regiones por estudiante
     * @param studentId ID del estudiante
     * @return Número de regiones asociadas
     */
    Long countByStudentId(Long studentId);
}

