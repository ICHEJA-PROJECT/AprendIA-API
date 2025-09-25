package com.icheha.aprendia_api.preferences.region.data.repositories;

import com.icheha.aprendia_api.preferences.region.data.entities.WordRegionEntity;
import com.icheha.aprendia_api.preferences.region.data.entities.WordRegionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRegionRepository extends JpaRepository<WordRegionEntity, WordRegionId> {
    
    /**
     * Buscar todas las regiones de una palabra específica
     */
    @Query("SELECT wr FROM WordRegionEntity wr WHERE wr.wordId = :wordId")
    List<WordRegionEntity> findByWordId(@Param("wordId") Long wordId);
    
    /**
     * Buscar todas las palabras de una región específica
     */
    @Query("SELECT wr FROM WordRegionEntity wr WHERE wr.regionId = :regionId")
    List<WordRegionEntity> findByRegionId(@Param("regionId") Long regionId);
    
    /**
     * Verificar si existe la relación entre palabra y región
     */
    boolean existsByWordIdAndRegionId(Long wordId, Long regionId);
    
    /**
     * Eliminar todas las relaciones de una palabra específica
     */
    void deleteByWordId(Long wordId);
    
    /**
     * Eliminar todas las relaciones de una región específica
     */
    void deleteByRegionId(Long regionId);
    
    /**
     * Contar palabras por región
     */
    @Query("SELECT COUNT(wr) FROM WordRegionEntity wr WHERE wr.regionId = :regionId")
    Long countByRegionId(@Param("regionId") Long regionId);
    
    /**
     * Contar regiones por palabra
     */
    @Query("SELECT COUNT(wr) FROM WordRegionEntity wr WHERE wr.wordId = :wordId")
    Long countByWordId(@Param("wordId") Long wordId);
}
