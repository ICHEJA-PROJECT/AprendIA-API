package com.icheha.aprendia_api.records.pupilExcerise.domain.entities;

public class PupilSkill {
    
    private final Long id;
    private final Long pupilId;
    private final Long skillId;
    private final Double puntuacion;
    
    private PupilSkill(Builder builder) {
        this.id = builder.id;
        this.pupilId = builder.pupilId;
        this.skillId = builder.skillId;
        this.puntuacion = builder.puntuacion;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public Long getId() {
        return id;
    }
    
    public Long getPupilId() {
        return pupilId;
    }
    
    public Long getSkillId() {
        return skillId;
    }
    
    public Double getPuntuacion() {
        return puntuacion;
    }
    
    public static class Builder {
        private Long id;
        private Long pupilId;
        private Long skillId;
        private Double puntuacion;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder pupilId(Long pupilId) {
            this.pupilId = pupilId;
            return this;
        }
        
        public Builder skillId(Long skillId) {
            this.skillId = skillId;
            return this;
        }
        
        public Builder puntuacion(Double puntuacion) {
            this.puntuacion = puntuacion;
            return this;
        }
        
        public PupilSkill build() {
            return new PupilSkill(this);
        }
    }
}
