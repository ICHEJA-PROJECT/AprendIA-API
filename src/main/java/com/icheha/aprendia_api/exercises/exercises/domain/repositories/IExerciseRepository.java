package com.icheha.aprendia_api.exercises.exercises.domain.repositories;

import com.icheha.aprendia_api.exercises.exercises.domain.entities.Exercise;

import java.util.List;
import java.util.Optional;

public interface IExerciseRepository {
    
    Exercise save(Exercise exercise);
    
    List<Exercise> findAll();
    
    Optional<Exercise> findById(Long id);
    
    List<Exercise> findByTemplateId(Long templateId);
    
    Optional<Exercise> findRandomByTemplateId(Long templateId);
    
    List<Exercise> findByTopicId(Long topicId);
    
    List<Exercise> findByTopicIds(List<Long> topicIds);
}
