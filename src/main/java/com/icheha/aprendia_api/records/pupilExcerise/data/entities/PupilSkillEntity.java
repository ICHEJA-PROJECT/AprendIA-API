package com.icheha.aprendia_api.records.pupilExcerise.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "educando_ejercicio_habilidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PupilSkillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_educando_ejercicio", nullable = false)
    private Long pupilExerciseId;

    @Column(name = "id_habilidad", nullable = false)
    private Long skillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_educando_ejercicio", insertable = false, updatable = false)
    private PupilExerciseEntity pupilExercise;

    @Column(name = "puntaje", nullable = false)
    private Double score;
}
