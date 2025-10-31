package com.icheha.aprendia_api.exercises.templates.data.entities;

import com.icheha.aprendia_api.exercises.layouts.data.entities.LayoutEntity;
import com.icheha.aprendia_api.exercises.topics.data.entities.TopicEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;
    
    @Column(name = "titulo", nullable = false)
    private String titulo;
    
    @Column(name = "instrucciones", nullable = false, columnDefinition = "TEXT")
    private String instrucciones;
    
    @Column(name = "tiempo_sugerido", nullable = false)
    private String suggestTime;
    
    @Column(name = "id_tema", nullable = false)
    private Long topicId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tema", insertable = false, updatable = false)
    private TopicEntity tema;
    
    @Column(name = "id_layout", nullable = false)
    private Long layoutId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_layout", insertable = false, updatable = false)
    private LayoutEntity layout;
    
    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.icheha.aprendia_api.exercises.exercises.data.entities.ExerciseEntity> exercises;
}
