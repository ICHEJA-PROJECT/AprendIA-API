package com.icheha.aprendia_api.records.domain.entities;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.exercises.exercises.domain.entities.ExerciseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "alumno_ejercicio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PupilExercise {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno_ejercicio")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno", nullable = false)
    private Persona pupil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ejercicio", nullable = false)
    private ExerciseEntity exercise;

    @Column(name = "puntuacion")
    private Double puntuacion;

    @Column(name = "tiempo_resolucion")
    private Integer tiempoResolucion;

    @Column(name = "fecha_realizacion")
    private LocalDateTime fechaRealizacion;

    @Column(name = "completado", nullable = false)
    private Boolean completado = false;

    @Column(name = "intentos")
    private Integer intentos = 0;
}
