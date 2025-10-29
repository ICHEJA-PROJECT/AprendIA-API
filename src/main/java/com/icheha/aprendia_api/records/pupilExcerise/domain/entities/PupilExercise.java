package com.icheha.aprendia_api.records.pupilExcerise.domain.entities;

import java.time.LocalDateTime;

public class PupilExercise {
    
    private final Long id;
    private final Long pupilId;
    private final Long exerciseId;
    private final Double puntuacion;
    private final Integer tiempoResolucion;
    private final LocalDateTime fechaRealizacion;
    private final Boolean completado;
    private final Integer intentos;
    
    private PupilExercise(Builder builder) {
        this.id = builder.id;
        this.pupilId = builder.pupilId;
        this.exerciseId = builder.exerciseId;
        this.puntuacion = builder.puntuacion;
        this.tiempoResolucion = builder.tiempoResolucion;
        this.fechaRealizacion = builder.fechaRealizacion;
        this.completado = builder.completado;
        this.intentos = builder.intentos;
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
    
    public Long getExerciseId() {
        return exerciseId;
    }
    
    public Double getPuntuacion() {
        return puntuacion;
    }
    
    public Integer getTiempoResolucion() {
        return tiempoResolucion;
    }
    
    public LocalDateTime getFechaRealizacion() {
        return fechaRealizacion;
    }
    
    public Boolean getCompletado() {
        return completado;
    }
    
    public Integer getIntentos() {
        return intentos;
    }
    
    public static class Builder {
        private Long id;
        private Long pupilId;
        private Long exerciseId;
        private Double puntuacion;
        private Integer tiempoResolucion;
        private LocalDateTime fechaRealizacion;
        private Boolean completado = false;
        private Integer intentos = 0;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder pupilId(Long pupilId) {
            this.pupilId = pupilId;
            return this;
        }
        
        public Builder exerciseId(Long exerciseId) {
            this.exerciseId = exerciseId;
            return this;
        }
        
        public Builder puntuacion(Double puntuacion) {
            this.puntuacion = puntuacion;
            return this;
        }
        
        public Builder tiempoResolucion(Integer tiempoResolucion) {
            this.tiempoResolucion = tiempoResolucion;
            return this;
        }
        
        public Builder fechaRealizacion(LocalDateTime fechaRealizacion) {
            this.fechaRealizacion = fechaRealizacion;
            return this;
        }
        
        public Builder completado(Boolean completado) {
            this.completado = completado;
            return this;
        }
        
        public Builder intentos(Integer intentos) {
            this.intentos = intentos;
            return this;
        }
        
        public PupilExercise build() {
            return new PupilExercise(this);
        }
    }
}
