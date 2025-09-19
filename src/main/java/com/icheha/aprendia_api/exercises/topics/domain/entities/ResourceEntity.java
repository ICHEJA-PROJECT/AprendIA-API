package com.icheha.aprendia_api.exercises.topics.domain.entities;

import com.icheha.aprendia_api.exercises.topics.domain.entities.pivots.TopicResourceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    @Column(name = "titulo", length = 64, nullable = false)
    private String titulo;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "contenido", nullable = false)
    private String contenido;

    @Column(name = "id_layout", nullable = false)
    private Long idLayout;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_layout", insertable = false, updatable = false)
    private com.icheha.aprendia_api.exercises.layouts.domain.entities.LayoutEntity layout;

    @OneToMany(mappedBy = "recurso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TopicResourceEntity> topicResources;
}
