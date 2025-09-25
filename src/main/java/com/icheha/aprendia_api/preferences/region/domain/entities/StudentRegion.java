package com.icheha.aprendia_api.preferences.region.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio StudentRegion
 * Representa la relación entre un estudiante y una región
 */
public class StudentRegion {
    
    private final Long studentId;
    private final Long regionId;
    private final Region region;
    
    // Constructor privado para usar Builder
    private StudentRegion(Builder builder) {
        this.studentId = builder.studentId;
        this.regionId = builder.regionId;
        this.region = builder.region;
    }
    
    // Getters
    public Long getStudentId() { return studentId; }
    public Long getRegionId() { return regionId; }
    public Region getRegion() { return region; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentRegion that = (StudentRegion) o;
        return Objects.equals(studentId, that.studentId) &&
               Objects.equals(regionId, that.regionId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(studentId, regionId);
    }
    
    @Override
    public String toString() {
        return "StudentRegion{" +
                "studentId=" + studentId +
                ", regionId=" + regionId +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long studentId;
        private Long regionId;
        private Region region;
        
        public Builder studentId(Long studentId) {
            this.studentId = studentId;
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
        
        public StudentRegion build() {
            return new StudentRegion(this);
        }
    }
}
