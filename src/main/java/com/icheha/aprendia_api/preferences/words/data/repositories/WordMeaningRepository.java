package com.icheha.aprendia_api.preferences.words.data.repositories;

import com.icheha.aprendia_api.preferences.words.data.entities.WordMeaningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordMeaningRepository extends JpaRepository<WordMeaningEntity, Long> {
    
    /**
     * Buscar todos los significados de una palabra específica
     */
    @Query("SELECT wm FROM WordMeaningEntity wm WHERE wm.word.id = :wordId")
    List<WordMeaningEntity> findByWordId(@Param("wordId") Long wordId);
    
    /**
     * Buscar significados que contengan el texto dado
     */
    @Query("SELECT wm FROM WordMeaningEntity wm WHERE wm.meaning LIKE %:meaning%")
    List<WordMeaningEntity> findByMeaningContaining(@Param("meaning") String meaning);
    
    /**
     * Buscar significados con la palabra cargada
     */
    @Query("SELECT wm FROM WordMeaningEntity wm JOIN FETCH wm.word WHERE wm.id = :id")
    Optional<WordMeaningEntity> findByIdWithWord(@Param("id") Long id);
    
    /**
     * Contar significados de una palabra
     */
    @Query("SELECT COUNT(wm) FROM WordMeaningEntity wm WHERE wm.word.id = :wordId")
    Long countByWordId(@Param("wordId") Long wordId);
    
    /**
     * Eliminar todos los significados de una palabra
     */
    void deleteByWordId(Long wordId);
}
