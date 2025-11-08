package com.icheha.aprendia_api.exercises.topics.data.repositories;

import com.icheha.aprendia_api.exercises.topics.data.entities.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {
    
    // Usar relación directa id_tema (más eficiente que tabla pivot)
    @Query("SELECT r FROM ResourceEntity r WHERE r.idTema = :topicId")
    List<ResourceEntity> findByTopicId(@Param("topicId") Long topicId);
    
    @Query("SELECT r FROM ResourceEntity r WHERE r.idTema IN :topicIds")
    List<ResourceEntity> findByTopicIds(@Param("topicIds") List<Long> topicIds);
}
