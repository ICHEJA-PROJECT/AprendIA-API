package com.icheha.aprendia_api.records.pupilExcerise.data.entities;

import com.icheha.aprendia_api.auth.data.entities.PersonaEntity;
import com.icheha.aprendia_api.exercises.templates.domain.entities.SkillEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alumno_habilidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PupilSkillEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno_habilidad")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno", nullable = false)
    private PersonaEntity pupil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_habilidad", nullable = false)
    private SkillEntity skill;

    @Column(name = "puntuacion", nullable = false)
    private Double puntuacion;

    @Column(name = "porcentaje", nullable = false)
    private Double porcentaje;

    @Column(name = "nivel", nullable = false)
    private Integer nivel;
}
