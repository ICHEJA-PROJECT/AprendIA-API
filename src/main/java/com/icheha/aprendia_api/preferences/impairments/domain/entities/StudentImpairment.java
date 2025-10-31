package com.icheha.aprendia_api.preferences.impairments.domain.entities;

public class StudentImpairment {
    
    private final Long id;
    private final Long studentId;
    private final Long impairmentId;
    
    private StudentImpairment(Builder builder) {
        this.id = builder.id;
        this.studentId = builder.studentId;
        this.impairmentId = builder.impairmentId;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public Long getId() {
        return id;
    }
    
    public Long getStudentId() {
        return studentId;
    }
    
    public Long getImpairmentId() {
        return impairmentId;
    }
    
    public static class Builder {
        private Long id;
        private Long studentId;
        private Long impairmentId;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder studentId(Long studentId) {
            this.studentId = studentId;
            return this;
        }
        
        public Builder impairmentId(Long impairmentId) {
            this.impairmentId = impairmentId;
            return this;
        }
        
        public StudentImpairment build() {
            return new StudentImpairment(this);
        }
    }
}
