package com.icheha.aprendia_api.exercises.templates.domain.entities;

import com.icheha.aprendia_api.exercises.templates.domain.entities.pivots.TemplateInstructionMediaEntity;
import com.icheha.aprendia_api.exercises.templates.domain.entities.pivots.TemplateSkillEntity;
import com.icheha.aprendia_api.exercises.topics.data.entities.TopicEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "reactivo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reactivo")
    private Long idReactivo;

    @Column(name = "titulo", length = 64, nullable = false)
    private String titulo;

    @Column(name = "instrucciones", nullable = false, columnDefinition = "TEXT")
    private String instrucciones;

    @Column(name = "tiempo_sugerido", nullable = false)
    private LocalTime tiempoSugerido;

    @Column(name = "id_tema", nullable = false)
    private Long idTema;

    @Column(name = "id_layout", nullable = false)
    private Long idLayout;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tema", insertable = false, updatable = false)
    private TopicEntity tema;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_layout", insertable = false, updatable = false)
    private com.icheha.aprendia_api.exercises.layouts.domain.entities.LayoutEntity layout;

    @OneToMany(mappedBy = "reactivo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TemplateSkillEntity> templateSkills;

    @OneToMany(mappedBy = "reactivo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TemplateInstructionMediaEntity> templateInstructionMedias;

    @OneToMany(mappedBy = "reactivo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.icheha.aprendia_api.exercises.exercises.domain.entities.ExerciseEntity> ejercicios;
}
