package com.icheha.aprendia_api.preferences.region.data.repositories;

import com.icheha.aprendia_api.preferences.region.data.entities.WordRegionEntity;
import com.icheha.aprendia_api.preferences.region.domain.WordRegionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRegionRepository extends JpaRepository<WordRegionEntity, WordRegionId> {
    
    @Query("SELECT wr FROM WordRegionEntity wr WHERE wr.wordId = :wordId")
    List<WordRegionEntity> findByWordId(@Param("wordId") Long wordId);
    
    @Query("SELECT wr FROM WordRegionEntity wr WHERE wr.regionId = :regionId")
    List<WordRegionEntity> findByRegionId(@Param("regionId") Long regionId);
    
    boolean existsByWordIdAndRegionId(Long wordId, Long regionId);
    
    void deleteByWordId(Long wordId);
    
    void deleteByRegionId(Long regionId);
    
    @Query("SELECT COUNT(wr) FROM WordRegionEntity wr WHERE wr.regionId = :regionId")
    Long countByRegionId(@Param("regionId") Long regionId);
    
    @Query("SELECT COUNT(wr) FROM WordRegionEntity wr WHERE wr.wordId = :wordId")
    Long countByWordId(@Param("wordId") Long wordId);
}

