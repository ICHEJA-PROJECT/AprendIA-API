package com.icheha.aprendia_api.exercises.topics.data.repositories;

import com.icheha.aprendia_api.exercises.topics.data.entities.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Long> {
    
    @Query("SELECT t FROM TopicEntity t JOIN FETCH t.templates WHERE t.idTema = :id")
    TopicEntity findByIdWithTemplates(@Param("id") Long id);
}
