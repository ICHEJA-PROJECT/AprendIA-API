package com.icheha.aprendia_api.exercises.exercises.data.mappers;

import com.icheha.aprendia_api.exercises.exercises.data.entities.ExerciseEntity;
import com.icheha.aprendia_api.exercises.exercises.domain.entities.Exercise;
import org.springframework.stereotype.Component;

@Component("exerciseDataMapper")
public class ExerciseMapper {
    
    public Exercise toDomain(ExerciseEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Exercise.builder()
                .id(entity.getId())
                .context(entity.getContext())
                .templateId(entity.getTemplate() != null ? entity.getTemplate().getId() : null)
                .build();
    }
    
    public ExerciseEntity toEntity(Exercise domain) {
        if (domain == null) {
            return null;
        }
        
        ExerciseEntity entity = new ExerciseEntity();
        entity.setId(domain.getId());
        entity.setContext(domain.getContext());
        // Note: template relationship should be set separately
        return entity;
    }
}
