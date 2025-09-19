package com.icheha.aprendia_api.preferences.domain.entities;

import com.icheha.aprendia_api.exercises.topics.domain.entities.LearningPathEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ruta_aprendizaje_discapacidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningPathImpairment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta_aprendizaje_discapacidad")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ruta_aprendizaje", nullable = false)
    private LearningPathEntity learningPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_discapacidad", nullable = false)
    private Impairment impairment;
}
