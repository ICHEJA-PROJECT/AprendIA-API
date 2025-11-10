package com.icheha.aprendia_api.exercises.topics.data.repositories;

import com.icheha.aprendia_api.exercises.topics.data.entities.pivots.TopicResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicResourceRepository extends JpaRepository<TopicResourceEntity, TopicResourceEntity.TopicResourceEntityId> {
    
    @Query("SELECT tr FROM TopicResourceEntity tr JOIN FETCH tr.tema JOIN FETCH tr.recurso")
    List<TopicResourceEntity> findAllWithRelations();
    
    @Query("SELECT tr FROM TopicResourceEntity tr JOIN FETCH tr.tema JOIN FETCH tr.recurso WHERE tr.idTema = :topicId")
    List<TopicResourceEntity> findByTopicId(@Param("topicId") Long topicId);
    
    @Query("SELECT tr FROM TopicResourceEntity tr JOIN FETCH tr.tema JOIN FETCH tr.recurso WHERE tr.idRecurso = :resourceId")
    List<TopicResourceEntity> findByResourceId(@Param("resourceId") Long resourceId);
    
    @Query("SELECT tr FROM TopicResourceEntity tr JOIN FETCH tr.tema JOIN FETCH tr.recurso WHERE tr.idTema = :topicId AND tr.idRecurso = :resourceId")
    Optional<TopicResourceEntity> findByTopicIdAndResourceId(@Param("topicId") Long topicId, @Param("resourceId") Long resourceId);
}

