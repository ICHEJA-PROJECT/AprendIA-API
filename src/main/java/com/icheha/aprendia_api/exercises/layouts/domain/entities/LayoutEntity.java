package com.icheha.aprendia_api.exercises.layouts.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "layout")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LayoutEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_layout")
    private Long idLayout;

    @Column(name = "nombre", length = 64, nullable = false)
    private String nombre;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "atributos", nullable = false)
    private String atributos;

    @Column(name = "id_tipo_layout", nullable = false)
    private Long idTipoLayout;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_layout", insertable = false, updatable = false)
    private TypeLayoutEntity tipoLayout;

    @OneToMany(mappedBy = "layout", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.icheha.aprendia_api.exercises.topics.domain.entities.ResourceEntity> recursos;

    @OneToMany(mappedBy = "layout", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.icheha.aprendia_api.exercises.templates.domain.entities.TemplateEntity> templates;
}
