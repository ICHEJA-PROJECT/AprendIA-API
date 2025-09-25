package com.icheha.aprendia_api.preferences.occupation.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio ExerciseOccupation
 * Representa la relación entre un ejercicio y una ocupación
 */
public class ExerciseOccupation {
    
    private final Long exerciseId;
    private final Long occupationId;
    private final Occupation occupation;
    
    // Constructor privado para usar Builder
    private ExerciseOccupation(Builder builder) {
        this.exerciseId = builder.exerciseId;
        this.occupationId = builder.occupationId;
        this.occupation = builder.occupation;
    }
    
    // Getters
    public Long getExerciseId() { return exerciseId; }
    public Long getOccupationId() { return occupationId; }
    public Occupation getOccupation() { return occupation; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseOccupation that = (ExerciseOccupation) o;
        return Objects.equals(exerciseId, that.exerciseId) &&
               Objects.equals(occupationId, that.occupationId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, occupationId);
    }
    
    @Override
    public String toString() {
        return "ExerciseOccupation{" +
                "exerciseId=" + exerciseId +
                ", occupationId=" + occupationId +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long exerciseId;
        private Long occupationId;
        private Occupation occupation;
        
        public Builder exerciseId(Long exerciseId) {
            this.exerciseId = exerciseId;
            return this;
        }
        
        public Builder occupationId(Long occupationId) {
            this.occupationId = occupationId;
            return this;
        }
        
        public Builder occupation(Occupation occupation) {
            this.occupation = occupation;
            return this;
        }
        
        public ExerciseOccupation build() {
            return new ExerciseOccupation(this);
        }
    }
}
