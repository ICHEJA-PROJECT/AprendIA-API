package com.icheha.aprendia_api.preferences.region.domain.repositories;

import com.icheha.aprendia_api.preferences.region.domain.entities.WordRegion;

import java.util.List;

/**
 * Interface del repositorio de dominio para WordRegion
 * Define los métodos de acceso a datos desde la capa de dominio
 */
public interface IWordRegionRepository {
    
    /**
     * Guardar una nueva relación palabra-región
     * @param wordRegion Relación a guardar
     * @return Relación guardada
     */
    WordRegion save(WordRegion wordRegion);
    
    /**
     * Buscar todas las regiones de una palabra específica
     * @param wordId ID de la palabra
     * @return Lista de relaciones palabra-región
     */
    List<WordRegion> findByWordId(Long wordId);
    
    /**
     * Buscar todas las palabras de una región específica
     * @param regionId ID de la región
     * @return Lista de relaciones palabra-región
     */
    List<WordRegion> findByRegionId(Long regionId);
    
    /**
     * Verificar si existe la relación entre palabra y región
     * @param wordId ID de la palabra
     * @param regionId ID de la región
     * @return true si existe, false en caso contrario
     */
    boolean existsByWordIdAndRegionId(Long wordId, Long regionId);
    
    /**
     * Eliminar relación por IDs
     * @param wordId ID de la palabra
     * @param regionId ID de la región
     */
    void deleteByWordIdAndRegionId(Long wordId, Long regionId);
    
    /**
     * Eliminar todas las relaciones de una palabra específica
     * @param wordId ID de la palabra
     */
    void deleteByWordId(Long wordId);
    
    /**
     * Eliminar todas las relaciones de una región específica
     * @param regionId ID de la región
     */
    void deleteByRegionId(Long regionId);
    
    /**
     * Contar palabras por región
     * @param regionId ID de la región
     * @return Número de palabras asociadas
     */
    Long countByRegionId(Long regionId);
    
    /**
     * Contar regiones por palabra
     * @param wordId ID de la palabra
     * @return Número de regiones asociadas
     */
    Long countByWordId(Long wordId);
}

