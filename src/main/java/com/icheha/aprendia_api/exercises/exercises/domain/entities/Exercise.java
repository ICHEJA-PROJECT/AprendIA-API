package com.icheha.aprendia_api.exercises.exercises.domain.entities;

import java.util.Map;

public class Exercise {
    
    private final Long id;
    private final Map<String, Object> context;
    private final Long templateId;
    
    private Exercise(Builder builder) {
        this.id = builder.id;
        this.context = builder.context;
        this.templateId = builder.templateId;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public Long getId() {
        return id;
    }
    
    public Map<String, Object> getContext() {
        return context;
    }
    
    public Long getTemplateId() {
        return templateId;
    }
    
    public static class Builder {
        private Long id;
        private Map<String, Object> context;
        private Long templateId;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder context(Map<String, Object> context) {
            this.context = context;
            return this;
        }
        
        public Builder templateId(Long templateId) {
            this.templateId = templateId;
            return this;
        }
        
        public Exercise build() {
            return new Exercise(this);
        }
    }
}
