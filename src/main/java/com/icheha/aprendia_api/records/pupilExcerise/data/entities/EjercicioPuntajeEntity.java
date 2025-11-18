package com.icheha.aprendia_api.records.pupilExcerise.data.entities;

import com.icheha.aprendia_api.exercises.exercises.data.entities.ExerciseEntity;
import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
//cambio para main
@Entity
@Table(name = "ejercicio_puntaje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EjercicioPuntajeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ejercicio_puntaje")
    private Long idEjercicioPuntaje;
    
    @Column(name = "id_ejercicio", nullable = false)
    private Long idEjercicio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ejercicio", insertable = false, updatable = false)
    private ExerciseEntity ejercicio;
    
    @Column(name = "id_persona", nullable = false)
    private Long idPersona;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", insertable = false, updatable = false)
    private PersonaEntity persona;
    
    @Column(name = "puntaje", precision = 5, scale = 2)
    private BigDecimal puntaje;
    
    @Column(name = "fecha_completado")
    private LocalDateTime fechaCompletado;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
    }
}

