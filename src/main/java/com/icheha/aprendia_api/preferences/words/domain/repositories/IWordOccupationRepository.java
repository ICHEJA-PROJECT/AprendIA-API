package com.icheha.aprendia_api.preferences.words.domain.repositories;

import com.icheha.aprendia_api.preferences.words.domain.entities.WordOccupation;

import java.util.List;

/**
 * Interface del repositorio de dominio para WordOccupation
 * Define los métodos de acceso a datos desde la capa de dominio
 */
public interface IWordOccupationRepository {
    
    /**
     * Guardar una nueva relación palabra-ocupación
     * @param wordOccupation Relación a guardar
     * @return Relación guardada
     */
    WordOccupation save(WordOccupation wordOccupation);
    
    /**
     * Buscar todas las ocupaciones de una palabra específica
     * @param wordId ID de la palabra
     * @return Lista de relaciones palabra-ocupación
     */
    List<WordOccupation> findByWordId(Long wordId);
    
    /**
     * Buscar todas las palabras de una ocupación específica
     * @param occupationId ID de la ocupación
     * @return Lista de relaciones palabra-ocupación
     */
    List<WordOccupation> findByOccupationId(Long occupationId);
    
    /**
     * Verificar si existe la relación entre palabra y ocupación
     * @param wordId ID de la palabra
     * @param occupationId ID de la ocupación
     * @return true si existe, false en caso contrario
     */
    boolean existsByWordIdAndOccupationId(Long wordId, Long occupationId);
    
    /**
     * Eliminar relación por IDs
     * @param wordId ID de la palabra
     * @param occupationId ID de la ocupación
     */
    void deleteByWordIdAndOccupationId(Long wordId, Long occupationId);
    
    /**
     * Eliminar todas las relaciones de una palabra específica
     * @param wordId ID de la palabra
     */
    void deleteByWordId(Long wordId);
    
    /**
     * Eliminar todas las relaciones de una ocupación específica
     * @param occupationId ID de la ocupación
     */
    void deleteByOccupationId(Long occupationId);
    
    /**
     * Contar palabras por ocupación
     * @param occupationId ID de la ocupación
     * @return Número de palabras asociadas
     */
    Long countByOccupationId(Long occupationId);
    
    /**
     * Contar ocupaciones por palabra
     * @param wordId ID de la palabra
     * @return Número de ocupaciones asociadas
     */
    Long countByWordId(Long wordId);
}

