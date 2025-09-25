package com.icheha.aprendia_api.preferences.words.data.repositories;

import com.icheha.aprendia_api.preferences.words.data.entities.WordOccupationEntity;
import com.icheha.aprendia_api.preferences.words.data.entities.WordOccupationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordOccupationRepository extends JpaRepository<WordOccupationEntity, WordOccupationId> {
    
    /**
     * Buscar todas las ocupaciones de una palabra específica
     */
    @Query("SELECT wo FROM WordOccupationEntity wo WHERE wo.wordId = :wordId")
    List<WordOccupationEntity> findByWordId(@Param("wordId") Long wordId);
    
    /**
     * Buscar todas las palabras de una ocupación específica
     */
    @Query("SELECT wo FROM WordOccupationEntity wo WHERE wo.occupationId = :occupationId")
    List<WordOccupationEntity> findByOccupationId(@Param("occupationId") Long occupationId);
    
    /**
     * Verificar si existe la relación entre palabra y ocupación
     */
    boolean existsByWordIdAndOccupationId(Long wordId, Long occupationId);
    
    /**
     * Eliminar todas las relaciones de una palabra específica
     */
    void deleteByWordId(Long wordId);
    
    /**
     * Eliminar todas las relaciones de una ocupación específica
     */
    void deleteByOccupationId(Long occupationId);
    
    /**
     * Contar palabras por ocupación
     */
    @Query("SELECT COUNT(wo) FROM WordOccupationEntity wo WHERE wo.occupationId = :occupationId")
    Long countByOccupationId(@Param("occupationId") Long occupationId);
    
    /**
     * Contar ocupaciones por palabra
     */
    @Query("SELECT COUNT(wo) FROM WordOccupationEntity wo WHERE wo.wordId = :wordId")
    Long countByWordId(@Param("wordId") Long wordId);
}
