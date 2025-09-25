package com.icheha.aprendia_api.preferences.occupation.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio StudentOccupation
 * Representa la relación entre un estudiante y una ocupación
 */
public class StudentOccupation {
    
    private final Long studentId;
    private final Long occupationId;
    private final Occupation occupation;
    
    // Constructor privado para usar Builder
    private StudentOccupation(Builder builder) {
        this.studentId = builder.studentId;
        this.occupationId = builder.occupationId;
        this.occupation = builder.occupation;
    }
    
    // Getters
    public Long getStudentId() { return studentId; }
    public Long getOccupationId() { return occupationId; }
    public Occupation getOccupation() { return occupation; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentOccupation that = (StudentOccupation) o;
        return Objects.equals(studentId, that.studentId) &&
               Objects.equals(occupationId, that.occupationId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(studentId, occupationId);
    }
    
    @Override
    public String toString() {
        return "StudentOccupation{" +
                "studentId=" + studentId +
                ", occupationId=" + occupationId +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long studentId;
        private Long occupationId;
        private Occupation occupation;
        
        public Builder studentId(Long studentId) {
            this.studentId = studentId;
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
        
        public StudentOccupation build() {
            return new StudentOccupation(this);
        }
    }
}
