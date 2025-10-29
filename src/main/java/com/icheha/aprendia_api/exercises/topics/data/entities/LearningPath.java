package com.icheha.aprendia_api.exercises.topics.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ruta_aprendizaje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningPath {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta_aprendizaje")
    private Long id;
    
    @Column(name = "nombre", nullable = false, length = 64)
    private String name;
    
    @OneToMany(mappedBy = "learningPath", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TopicSequenceEntity> sequences;
}
