package com.icheha.aprendia_api.exercises.topics.domain.entities.pivots;

import com.icheha.aprendia_api.exercises.topics.domain.entities.ResourceEntity;
import com.icheha.aprendia_api.exercises.topics.domain.entities.TopicEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "tema_recursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(TopicResourceEntity.TopicResourceEntityId.class)
public class TopicResourceEntity {
    
    @Id
    @Column(name = "id_tema", nullable = false)
    private Long idTema;

    @Id
    @Column(name = "id_recurso", nullable = false)
    private Long idRecurso;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tema", insertable = false, updatable = false)
    private TopicEntity tema;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recurso", insertable = false, updatable = false)
    private ResourceEntity recurso;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TopicResourceEntityId implements Serializable {
        private Long idTema;
        private Long idRecurso;
    }
}
