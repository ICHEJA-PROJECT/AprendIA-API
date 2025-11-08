package com.icheha.aprendia_api.exercises.layouts.data.entities;

import com.icheha.aprendia_api.exercises.templates.data.entities.TemplateEntity;
import com.icheha.aprendia_api.exercises.topics.data.entities.ResourceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
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

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "id_tipo_layout", nullable = false)
    private Long idTipoLayout;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_layout", insertable = false, updatable = false)
    private TypeLayoutEntity tipoLayout;
    @OneToMany(mappedBy = "layout", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResourceEntity> recursos;

    @OneToMany(mappedBy = "layout", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TemplateEntity> templates;
    
    @OneToMany(mappedBy = "layout", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BoxLayoutEntity> boxLayouts;
    
    @PreUpdate
    protected void onUpdate() {
        updateAt = java.time.LocalDateTime.now();
    }
}
