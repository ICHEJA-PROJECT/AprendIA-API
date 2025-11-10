package com.icheha.aprendia_api.exercises.topics.data.repositories;

import com.icheha.aprendia_api.exercises.topics.data.entities.TopicSequenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicSequenceRepository extends JpaRepository<TopicSequenceEntity, TopicSequenceEntity.TopicSequenceEntityId> {
    
    @Query("SELECT ts FROM TopicSequenceEntity ts JOIN FETCH ts.tema JOIN FETCH ts.temaSiguiente JOIN FETCH ts.learningPath")
    List<TopicSequenceEntity> findAllWithRelations();
    
    @Query("SELECT ts FROM TopicSequenceEntity ts JOIN FETCH ts.tema JOIN FETCH ts.temaSiguiente JOIN FETCH ts.learningPath WHERE ts.idTema = :topicId")
    List<TopicSequenceEntity> findByTopicId(@Param("topicId") Long topicId);
    
    @Query("SELECT ts FROM TopicSequenceEntity ts JOIN FETCH ts.tema JOIN FETCH ts.temaSiguiente JOIN FETCH ts.learningPath WHERE ts.idRuta = :learningPathId")
    List<TopicSequenceEntity> findByLearningPathId(@Param("learningPathId") Long learningPathId);
    
    @Query("SELECT ts FROM TopicSequenceEntity ts JOIN FETCH ts.tema JOIN FETCH ts.temaSiguiente JOIN FETCH ts.learningPath WHERE ts.idTema = :topicId AND ts.idTemaSiguiente = :nextTopicId")
    Optional<TopicSequenceEntity> findByTopicIdAndNextTopicId(@Param("topicId") Long topicId, @Param("nextTopicId") Long nextTopicId);
}

