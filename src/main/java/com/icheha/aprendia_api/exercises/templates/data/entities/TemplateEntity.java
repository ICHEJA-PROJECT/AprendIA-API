package com.icheha.aprendia_api.exercises.templates.data.entities;

import com.icheha.aprendia_api.exercises.exercises.data.entities.ExerciseEntity;
import com.icheha.aprendia_api.exercises.layouts.data.entities.LayoutEntity;
import com.icheha.aprendia_api.exercises.topics.data.entities.ResourceEntity;
import com.icheha.aprendia_api.exercises.topics.data.entities.TopicEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    
    @Column(name = "id_recurso")
    private Long idRecurso;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recurso", insertable = false, updatable = false)
    private ResourceEntity recurso;
    
    @Column(name = "titulo", length = 200, nullable = false)
    private String titulo;
    
    @Column(name = "instrucciones", columnDefinition = "TEXT")
    private String instrucciones;
    
    @Column(name = "url_imagen", length = 500)
    private String urlImagen;
    
    @Column(name = "tiempo_sugerido")
    private Integer tiempoSugerido;
    

    
    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExerciseEntity> exercises;
    

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
