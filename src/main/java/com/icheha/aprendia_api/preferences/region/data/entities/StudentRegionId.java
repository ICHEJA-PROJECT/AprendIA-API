package com.icheha.aprendia_api.preferences.region.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase de ID compuesto para StudentRegionEntity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegionId implements Serializable {
    
    private Long studentId;
    private Long regionId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentRegionId that = (StudentRegionId) o;
        return Objects.equals(studentId, that.studentId) &&
               Objects.equals(regionId, that.regionId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(studentId, regionId);
    }
}
