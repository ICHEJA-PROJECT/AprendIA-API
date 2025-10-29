package com.icheha.aprendia_api.exercises.topics.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "secuencia_temas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(TopicSequenceEntity.TopicSequenceEntityId.class)
public class TopicSequenceEntity {
    
    @Id
    @Column(name = "id_tema", nullable = false)
    private Long idTema;

    @Id
    @Column(name = "id_tema_siguiente", nullable = false)
    private Long idTemaSiguiente;

    @Column(name = "id_ruta_aprendizaje", nullable = false)
    private Long idRutaAprendizaje;
    
    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tema", insertable = false, updatable = false)
    private TopicEntity tema;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tema_siguiente", insertable = false, updatable = false)
    private TopicEntity temaSiguiente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ruta_aprendizaje", insertable = false, updatable = false)
    private LearningPath learningPath;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TopicSequenceEntityId implements Serializable {
        private Long idTema;
        private Long idTemaSiguiente;
    }
}
