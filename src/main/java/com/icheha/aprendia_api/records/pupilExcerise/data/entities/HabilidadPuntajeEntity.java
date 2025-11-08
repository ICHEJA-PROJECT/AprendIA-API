package com.icheha.aprendia_api.records.pupilExcerise.data.entities;

import com.icheha.aprendia_api.exercises.exercises.data.entities.ExerciseEntity;
import com.icheha.aprendia_api.exercises.templates.data.entities.SkillEntity;
import com.icheha.aprendia_api.users.user.data.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "habilidad_puntaje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabilidadPuntajeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habilidad_puntaje")
    private Long idHabilidadPuntaje;
    
    @Column(name = "id_ejercicio", nullable = false)
    private Long idEjercicio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ejercicio", insertable = false, updatable = false)
    private ExerciseEntity ejercicio;
    
    @Column(name = "id_habilidad", nullable = false)
    private Long idHabilidad;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_habilidad", referencedColumnName = "id_agenda", insertable = false, updatable = false)
    private SkillEntity habilidad;
    
    @Column(name = "id_user", nullable = false)
    private Long idUser;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private UserEntity user;
    
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

