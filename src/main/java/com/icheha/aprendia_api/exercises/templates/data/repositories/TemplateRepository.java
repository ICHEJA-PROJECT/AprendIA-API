package com.icheha.aprendia_api.exercises.templates.data.repositories;

import com.icheha.aprendia_api.exercises.templates.data.entities.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateEntity, Long> {
    
    @Query("SELECT t FROM TemplateEntity t WHERE t.tema.id = :topicId")
    List<TemplateEntity> findByTopicId(@Param("topicId") Long topicId);
    
    @Query("SELECT t FROM TemplateEntity t WHERE t.tema.id IN :topicIds")
    List<TemplateEntity> findByTopicIds(@Param("topicIds") List<Long> topicIds);
    
    @Query("SELECT t FROM TemplateEntity t JOIN FETCH t.tema WHERE t.id = :id")
    TemplateEntity findByIdWithTopic(@Param("id") Long id);
}
