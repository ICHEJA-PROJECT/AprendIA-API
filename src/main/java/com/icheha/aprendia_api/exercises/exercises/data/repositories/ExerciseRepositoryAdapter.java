package com.icheha.aprendia_api.exercises.exercises.data.repositories;

import com.icheha.aprendia_api.exercises.exercises.data.entities.ExerciseEntity;
import com.icheha.aprendia_api.exercises.exercises.data.mappers.ExerciseMapper;
import com.icheha.aprendia_api.exercises.exercises.domain.entities.Exercise;
import com.icheha.aprendia_api.exercises.exercises.domain.repositories.IExerciseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ExerciseRepositoryAdapter implements IExerciseRepository {
    
    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;
    
    public ExerciseRepositoryAdapter(ExerciseRepository exerciseRepository, 
                                   @org.springframework.beans.factory.annotation.Qualifier("exerciseDataMapper") ExerciseMapper exerciseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }
    
    @Override
    public Exercise save(Exercise exercise) {
        ExerciseEntity entity = exerciseMapper.toEntity(exercise);
        ExerciseEntity savedEntity = exerciseRepository.save(entity);
        return exerciseMapper.toDomain(savedEntity);
    }
    
    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll().stream()
                .map(exerciseMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Exercise> findById(Long id) {
        return exerciseRepository.findById(id)
                .map(exerciseMapper::toDomain);
    }
    
    @Override
    public List<Exercise> findByTemplateId(Long templateId) {
        return exerciseRepository.findByTemplateId(templateId).stream()
                .map(exerciseMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Exercise> findRandomByTemplateId(Long templateId) {
        return exerciseRepository.findRandomByTemplateId(templateId)
                .map(exerciseMapper::toDomain);
    }
    
    @Override
    public List<Exercise> findByTopicId(Long topicId) {
        return exerciseRepository.findByTopicId(topicId).stream()
                .map(exerciseMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Exercise> findByTopicIds(List<Long> topicIds) {
        return exerciseRepository.findByTopicIds(topicIds).stream()
                .map(exerciseMapper::toDomain)
                .collect(Collectors.toList());
    }
}
