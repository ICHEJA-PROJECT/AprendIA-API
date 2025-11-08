package com.icheha.aprendia_api.preferences.impairments.data.repositories;

import com.icheha.aprendia_api.preferences.impairments.data.entities.ReactiveImpairment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactiveImpairmentRepository extends JpaRepository<ReactiveImpairment, ReactiveImpairment.ReactiveImpairmentId> {
    
    @Query("SELECT ri FROM ReactiveImpairment ri WHERE ri.reactiveId = :reactiveId")
    List<ReactiveImpairment> findByReactiveId(@Param("reactiveId") Long reactiveId);
    
    @Query("SELECT ri FROM ReactiveImpairment ri WHERE ri.impairmentId = :impairmentId")
    List<ReactiveImpairment> findByImpairmentId(@Param("impairmentId") Long impairmentId);
    
    @Query("SELECT ri FROM ReactiveImpairment ri WHERE ri.reactiveId IN " +
           "(SELECT DISTINCT t.id FROM TemplateEntity t " +
           "WHERE t.topicId IN " +
           "(SELECT DISTINCT ts.idTema FROM TopicSequenceEntity ts WHERE ts.idRuta = :learningPathId) " +
           "OR t.topicId IN " +
           "(SELECT DISTINCT ts.idTemaSiguiente FROM TopicSequenceEntity ts WHERE ts.idRuta = :learningPathId))")
    List<ReactiveImpairment> findByLearningPathId(@Param("learningPathId") Long learningPathId);
    
    boolean existsByReactiveIdAndImpairmentId(Long reactiveId, Long impairmentId);
}

