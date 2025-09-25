package com.icheha.aprendia_api.preferences.occupation.services;

import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.CreateStudentOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.response.StudentOccupationResponseDto;

import java.util.List;

/**
 * Interface del servicio de StudentOccupation
 * Define los métodos principales para gestión de relaciones estudiante-ocupación
 */
public interface IStudentOccupationService {
    
    /**
     * Crear una nueva relación estudiante-ocupación
     * @param createDto Datos para crear la relación
     * @return Relación creada
     */
    StudentOccupationResponseDto create(CreateStudentOccupationDto createDto);
    
    /**
     * Buscar todas las ocupaciones de un estudiante específico
     * @param studentId ID del estudiante
     * @return Lista de relaciones estudiante-ocupación
     */
    List<StudentOccupationResponseDto> findByStudentId(Long studentId);
    
    /**
     * Buscar todos los estudiantes de una ocupación específica
     * @param occupationId ID de la ocupación
     * @return Lista de relaciones estudiante-ocupación
     */
    List<StudentOccupationResponseDto> findByOccupationId(Long occupationId);
    
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
