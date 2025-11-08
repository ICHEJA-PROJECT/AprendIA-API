package com.icheha.aprendia_api.exercises.topics.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ruta_aprendizaje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningPath {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta")
    private Long idRuta;
    
    @Column(name = "id_metodologia", nullable = false)
    private Long idMetodologia;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_metodologia", insertable = false, updatable = false)
    private MetodologiaEntity metodologia;
    
    @Column(name = "id_perfil", nullable = false)
    private Long idPerfil;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perfil", insertable = false, updatable = false)
    private PerfilEntity perfil;
    
    @Column(name = "nombre", length = 200, nullable = false)
    private String nombre;
    
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "url_imagen", length = 500)
    private String urlImagen;

    @OneToMany(mappedBy = "rutaAprendizaje", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CuadernilloEntity> cuadernillos;
    
    @OneToMany(mappedBy = "learningPath", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TopicSequenceEntity> sequences;
    
    
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
