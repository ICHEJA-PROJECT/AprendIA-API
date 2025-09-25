package com.icheha.aprendia_api.preferences.occupation.domain.repositories;

import com.icheha.aprendia_api.preferences.occupation.domain.entities.Occupation;

import java.util.List;
import java.util.Optional;

/**
 * Interface del repositorio de dominio para Occupation
 * Define los métodos de acceso a datos desde la capa de dominio
 */
public interface IOccupationRepository {
    
    /**
     * Guardar una nueva ocupación
     * @param occupation Ocupación a guardar
     * @return Ocupación guardada con ID asignado
     */
    Occupation save(Occupation occupation);
    
    /**
     * Buscar ocupación por ID
     * @param id ID de la ocupación
     * @return Ocupación encontrada o vacío si no existe
     */
    Optional<Occupation> findById(Long id);
    
    /**
     * Buscar ocupación por nombre
     * @param name Nombre de la ocupación
     * @return Ocupación encontrada o vacío si no existe
     */
    Optional<Occupation> findByName(String name);
    
    /**
     * Buscar todas las ocupaciones
     * @return Lista de todas las ocupaciones
     */
    List<Occupation> findAll();
    
    /**
     * Buscar ocupaciones que contengan el nombre dado
     * @param name Nombre parcial a buscar
     * @return Lista de ocupaciones que coincidan
     */
    List<Occupation> findByNameContaining(String name);
    
    /**
     * Verificar si existe una ocupación con el nombre dado
     * @param name Nombre a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByName(String name);
    
    /**
     * Eliminar ocupación por ID
     * @param id ID de la ocupación a eliminar
     */
    void deleteById(Long id);
    
    /**
     * Verificar si existe una ocupación con el ID dado
     * @param id ID a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsById(Long id);
}
