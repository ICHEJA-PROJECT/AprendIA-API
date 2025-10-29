package com.icheha.aprendia_api.preferences.occupation.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase de ID compuesto para ExerciseOccupationEntity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseOccupationId implements Serializable {
    
    private Long exerciseId;
    private Long occupationId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseOccupationId that = (ExerciseOccupationId) o;
        return Objects.equals(exerciseId, that.exerciseId) &&
               Objects.equals(occupationId, that.occupationId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, occupationId);
    }
}

