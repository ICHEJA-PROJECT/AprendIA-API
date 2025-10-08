package com.icheha.aprendia_api.exercises.templates.data.repositories;

import com.icheha.aprendia_api.exercises.templates.data.entities.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITemplateRepository extends JpaRepository<TemplateEntity, Long> {
    
    // Buscar templates por ID de tema
    @Query("SELECT t FROM TemplateEntity t WHERE t.tema.idTema = :topicId")
    List<TemplateEntity> findByTopicId(@Param("topicId") Long topicId);
    
    // Buscar templates por IDs de temas
    @Query("SELECT t FROM TemplateEntity t WHERE t.tema.idTema IN :topicIds")
    List<TemplateEntity> findByTopicIds(@Param("topicIds") List<Long> topicIds);
    
    // Buscar template por ID
    @NonNull
    Optional<TemplateEntity> findById(@NonNull Long id);
}
