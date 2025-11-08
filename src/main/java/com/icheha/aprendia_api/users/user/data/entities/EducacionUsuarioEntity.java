package com.icheha.aprendia_api.users.user.data.entities;

import com.icheha.aprendia_api.exercises.topics.data.entities.LearningPath;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "educacion_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducacionUsuarioEntity {
    
    @Id
    @Column(name = "id_user", nullable = false)
    private Long idUser;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    @MapsId
    private UserEntity user;
    
    @Column(name = "id_ruta_aprendizaje", nullable = false)
    private Long idRutaAprendizaje;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ruta_aprendizaje", referencedColumnName = "id_ruta", insertable = false, updatable = false)
    private LearningPath rutaAprendizaje;
    
    @Column(name = "url_ar", length = 500)
    private String urlAr;
    
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

