package com.icheha.aprendia_api.preferences.impairments.data.repositories;

import com.icheha.aprendia_api.preferences.impairments.data.entities.LearningPathImpairment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningPathImpairmentRepository extends JpaRepository<LearningPathImpairment, LearningPathImpairment.LearningPathImpairmentId> {
    
    @Query("SELECT lpi FROM LearningPathImpairment lpi WHERE lpi.learningPathId = :learningPathId")
    List<LearningPathImpairment> findByLearningPathId(@Param("learningPathId") Long learningPathId);
    
    @Query("SELECT lpi FROM LearningPathImpairment lpi WHERE lpi.impairmentId = :impairmentId")
    List<LearningPathImpairment> findByImpairmentId(@Param("impairmentId") Long impairmentId);
    
    @Query("SELECT lpi FROM LearningPathImpairment lpi " +
           "JOIN FETCH lpi.learningPath " +
           "WHERE lpi.impairmentId = :impairmentId")
    List<LearningPathImpairment> findByImpairmentIdWithLearningPath(@Param("impairmentId") Long impairmentId);
    
    @Query("SELECT lpi FROM LearningPathImpairment lpi " +
           "JOIN FETCH lpi.learningPath " +
           "WHERE lpi.impairmentId = :impairmentId " +
           "ORDER BY lpi.learningPathId DESC")
    List<LearningPathImpairment> findFirstByImpairmentIdWithLearningPath(@Param("impairmentId") Long impairmentId);
    
    boolean existsByLearningPathIdAndImpairmentId(Long learningPathId, Long impairmentId);
}

