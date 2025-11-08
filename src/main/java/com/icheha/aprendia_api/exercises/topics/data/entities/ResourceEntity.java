package com.icheha.aprendia_api.exercises.topics.data.entities;

import com.icheha.aprendia_api.exercises.layouts.data.entities.LayoutEntity;
import com.icheha.aprendia_api.exercises.templates.data.entities.TemplateEntity;
import com.icheha.aprendia_api.exercises.topics.data.entities.pivots.TopicResourceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "recurso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso")
    private Long idRecurso;
    
    @Column(name = "id_tema", nullable = false)
    private Long idTema;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tema", insertable = false, updatable = false)
    private TopicEntity tema;

    @Column(name = "id_layout", nullable = false)
    private Long idLayout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_layout", insertable = false, updatable = false)
    private LayoutEntity layout;

    @Column(name = "nombre", length = 200, nullable = false)
    private String nombre;

    @Column(name = "url_imagen", length = 500)
    private String urlImagen;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "contenido")
    private String contenido;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "recurso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TopicResourceEntity> topicResources;
    
    @OneToMany(mappedBy = "recurso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TemplateEntity> reactivos;
    
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
