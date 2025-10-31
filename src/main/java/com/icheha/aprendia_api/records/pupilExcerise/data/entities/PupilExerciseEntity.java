package com.icheha.aprendia_api.records.pupilExcerise.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "educando_ejercicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PupilExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_educando_ejercicio")
    private Long id;

    @Column(name = "id_educando", nullable = false)
    private Long pupilId;

    @Column(name = "id_ejercicio", nullable = false)
    private Long exerciseId;

    @Column(name = "fecha_asignacion")
    private LocalDateTime assignedDate;

    @Column(name = "fecha_completado")
    private LocalDateTime completedDate;

    @Column(name = "tiempo_asignado", nullable = false)
    private String assignedTime;

    @Column(name = "por_educador", nullable = false)
    private Boolean byTeacher;

    @OneToMany(mappedBy = "pupilExercise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PupilSkillEntity> skills;
}
