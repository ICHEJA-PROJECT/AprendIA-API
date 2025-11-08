package com.icheha.aprendia_api.exercises.layouts.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tipo_layouts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeLayoutEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_layout")
    private Long idTipoLayout;

    @Column(name = "nombre", length = 64, nullable = false)
    private String nombre;



    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    // Relaciones
    @OneToMany(mappedBy = "tipoLayout", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LayoutEntity> layouts;

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
