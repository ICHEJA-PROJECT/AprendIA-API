package com.icheha.aprendia_api.preferences.impairments.data.repositories;

import com.icheha.aprendia_api.preferences.impairments.data.entities.ResourceImpairment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceImpairmentRepository extends JpaRepository<ResourceImpairment, ResourceImpairment.ResourceImpairmentId> {
    
    @Query("SELECT ri FROM ResourceImpairment ri WHERE ri.resourceId = :resourceId")
    List<ResourceImpairment> findByResourceId(@Param("resourceId") Long resourceId);
    
    @Query("SELECT ri FROM ResourceImpairment ri WHERE ri.impairmentId = :impairmentId")
    List<ResourceImpairment> findByImpairmentId(@Param("impairmentId") Long impairmentId);
    
    @Query("SELECT ri FROM ResourceImpairment ri WHERE ri.resourceId IN " +
           "(SELECT DISTINCT r.idRecurso FROM ResourceEntity r " +
           "WHERE r.idTema IN " +
           "(SELECT DISTINCT ts.idTema FROM TopicSequenceEntity ts WHERE ts.idRuta = :learningPathId) " +
           "OR r.idTema IN " +
           "(SELECT DISTINCT ts.idTemaSiguiente FROM TopicSequenceEntity ts WHERE ts.idRuta = :learningPathId))")
    List<ResourceImpairment> findByLearningPathId(@Param("learningPathId") Long learningPathId);
    
    boolean existsByResourceIdAndImpairmentId(Long resourceId, Long impairmentId);
}

