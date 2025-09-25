package com.icheha.aprendia_api.preferences.occupation.services;

import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.CreateOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.response.OccupationResponseDto;

import java.util.List;

/**
 * Interface del servicio de Occupation
 * Define los métodos principales para gestión de ocupaciones
 */
public interface IOccupationService {
    
    /**
     * Crear una nueva ocupación
     * @param createOccupationDto Datos para crear la ocupación
     * @return Ocupación creada
     */
    OccupationResponseDto create(CreateOccupationDto createOccupationDto);
    
    /**
     * Buscar todas las ocupaciones
     * @return Lista de todas las ocupaciones
     */
    List<OccupationResponseDto> findAll();
    
    /**
     * Buscar ocupación por ID
     * @param id ID de la ocupación
     * @return Ocupación encontrada
     */
    OccupationResponseDto findById(Long id);
    
    /**
     * Buscar ocupación por nombre
     * @param name Nombre de la ocupación
     * @return Ocupación encontrada
     */
    OccupationResponseDto findByName(String name);
    
    /**
     * Buscar ocupaciones que contengan el nombre dado
     * @param name Nombre parcial a buscar
     * @return Lista de ocupaciones que coincidan
     */
    List<OccupationResponseDto> findByNameContaining(String name);
    
    /**
     * Eliminar ocupación por ID
     * @param id ID de la ocupación a eliminar
     */
    void deleteById(Long id);
    
    /**
     * Verificar si existe una ocupación con el nombre dado
     * @param name Nombre a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByName(String name);
}
