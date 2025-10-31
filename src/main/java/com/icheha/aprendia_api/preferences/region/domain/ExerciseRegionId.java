package com.icheha.aprendia_api.preferences.region.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase de ID compuesto para ExerciseRegionEntity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseRegionId implements Serializable {
    
    private Long exerciseId;
    private Long regionId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseRegionId that = (ExerciseRegionId) o;
        return Objects.equals(exerciseId, that.exerciseId) &&
               Objects.equals(regionId, that.regionId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, regionId);
    }
}

