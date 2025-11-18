package com.icheha.aprendia_api.exercises.topics.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "contenido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuadernilloEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuadernillo")
    private Long idCuadernillo;
    
    @Column(name = "id_ruta_aprendizaje", nullable = false)
    private Long idRutaAprendizaje;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ruta_aprendizaje", referencedColumnName = "id_ruta", insertable = false, updatable = false)
    private LearningPath rutaAprendizaje;
    
    @Column(name = "nombre", length = 200, nullable = false)
    private String nombre;
    
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "objetivo", columnDefinition = "TEXT")
    private String objetivo;
    
    @Column(name = "url_imagen", length = 500)
    private String urlImagen;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    
    @OneToMany(mappedBy = "cuadernillo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UnitEntity> unidades;
    
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

