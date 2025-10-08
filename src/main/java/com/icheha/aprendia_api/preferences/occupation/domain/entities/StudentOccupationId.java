package com.icheha.aprendia_api.preferences.occupation.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase de ID compuesto para StudentOccupationEntity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOccupationId implements Serializable {
    
    private Long studentId;
    private Long occupationId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentOccupationId that = (StudentOccupationId) o;
        return Objects.equals(studentId, that.studentId) &&
               Objects.equals(occupationId, that.occupationId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(studentId, occupationId);
    }
}

