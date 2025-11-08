package com.icheha.aprendia_api.preferences.words.data.repositories;

import com.icheha.aprendia_api.preferences.words.data.entities.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, Long> {
    
    /**
     * Buscar palabra por texto exacto
     */
    Optional<WordEntity> findByWord(String word);
    
    /**
     * Verificar si existe una palabra con el texto dado
     */
    boolean existsByWord(String word);
    
    /**
     * Buscar palabras que contengan el texto dado (búsqueda parcial)
     */
    @Query("SELECT w FROM WordEntity w WHERE w.word LIKE CONCAT('%', :word, '%')")
    List<WordEntity> findByWordContaining(@Param("word") String word);
    
    /**
     * Buscar palabra con sus significados cargados
     */
    @Query("SELECT w FROM WordEntity w LEFT JOIN FETCH w.meanings WHERE w.id = :id")
    Optional<WordEntity> findByIdWithMeanings(@Param("id") Long id);
    
    /**
     * Buscar todas las palabras con sus significados cargados
     */
    @Query("SELECT w FROM WordEntity w LEFT JOIN FETCH w.meanings")
    List<WordEntity> findAllWithMeanings();
    
    /**
     * Buscar palabras por ocupación
     */
    @Query("SELECT DISTINCT w FROM WordEntity w JOIN w.occupations wo WHERE wo.occupationId = :occupationId")
    List<WordEntity> findByOccupationId(@Param("occupationId") Long occupationId);
    
    /**
     * Buscar palabras por región
     */
    @Query("SELECT DISTINCT w FROM WordEntity w JOIN w.regions wr WHERE wr.regionId = :regionId")
    List<WordEntity> findByRegionId(@Param("regionId") Long regionId);
}

