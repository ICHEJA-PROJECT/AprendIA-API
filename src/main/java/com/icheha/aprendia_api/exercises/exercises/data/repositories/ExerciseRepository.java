package com.icheha.aprendia_api.exercises.exercises.data.repositories;

import com.icheha.aprendia_api.exercises.exercises.data.entities.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
    
    @Query("SELECT e FROM ExerciseEntity e WHERE e.template.id = :templateId")
    List<ExerciseEntity> findByTemplateId(@Param("templateId") Long templateId);
    
    @Query(value = "SELECT * FROM ejercicio e WHERE e.id_reactivo = :templateId ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<ExerciseEntity> findRandomByTemplateId(@Param("templateId") Long templateId);
    
    @Query("SELECT e FROM ExerciseEntity e JOIN e.template t WHERE t.tema.idTema = :topicId")
    List<ExerciseEntity> findByTopicId(@Param("topicId") Long topicId);
    
    @Query("SELECT e FROM ExerciseEntity e JOIN e.template t WHERE t.tema.idTema IN :topicIds")
    List<ExerciseEntity> findByTopicIds(@Param("topicIds") List<Long> topicIds);
}
