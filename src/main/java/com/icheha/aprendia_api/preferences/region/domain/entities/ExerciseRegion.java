package com.icheha.aprendia_api.preferences.region.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio ExerciseRegion
 * Representa la relación entre un ejercicio y una región
 */
public class ExerciseRegion {
    
    private final Long exerciseId;
    private final Long regionId;
    private final Region region;
    
    // Constructor privado para usar Builder
    private ExerciseRegion(Builder builder) {
        this.exerciseId = builder.exerciseId;
        this.regionId = builder.regionId;
        this.region = builder.region;
    }
    
    // Getters
    public Long getExerciseId() { return exerciseId; }
    public Long getRegionId() { return regionId; }
    public Region getRegion() { return region; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseRegion that = (ExerciseRegion) o;
        return Objects.equals(exerciseId, that.exerciseId) &&
               Objects.equals(regionId, that.regionId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, regionId);
    }
    
    @Override
    public String toString() {
        return "ExerciseRegion{" +
                "exerciseId=" + exerciseId +
                ", regionId=" + regionId +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long exerciseId;
        private Long regionId;
        private Region region;
        
        public Builder exerciseId(Long exerciseId) {
            this.exerciseId = exerciseId;
            return this;
        }
        
        public Builder regionId(Long regionId) {
            this.regionId = regionId;
            return this;
        }
        
        public Builder region(Region region) {
            this.region = region;
            return this;
        }
        
        public ExerciseRegion build() {
            return new ExerciseRegion(this);
        }
    }
}
