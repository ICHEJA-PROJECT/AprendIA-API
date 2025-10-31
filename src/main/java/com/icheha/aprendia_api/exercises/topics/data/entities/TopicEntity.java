package com.icheha.aprendia_api.exercises.topics.data.entities;

import com.icheha.aprendia_api.exercises.templates.data.entities.TemplateEntity;
import com.icheha.aprendia_api.exercises.topics.data.entities.pivots.TopicResourceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tema")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tema")
    private Long idTema;

    @Column(name = "nombre", length = 64, nullable = false)
    private String nombre;

    @Column(name = "id_unidad", nullable = true)
    private Long idUnidad;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad", insertable = false, updatable = false)
    private UnitEntity unidad;

    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TemplateEntity> templates;

    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TopicSequenceEntity> secuenciasComoOrigen;

    @OneToMany(mappedBy = "temaSiguiente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TopicSequenceEntity> secuenciasComoDestino;

    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TopicResourceEntity> topicResources;
}
