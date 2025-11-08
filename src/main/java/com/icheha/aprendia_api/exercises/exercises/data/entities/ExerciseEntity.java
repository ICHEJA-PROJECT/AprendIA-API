package com.icheha.aprendia_api.exercises.exercises.data.entities;

import com.icheha.aprendia_api.exercises.templates.data.entities.TemplateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.LocalDateTime;

import java.util.Map;

@Entity
@Table(name = "ejercicio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ejercicio")
    private Long id;
    
    @Column(name = "contexto", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> context;
    
    @Column(name = "id_reactivo", nullable = false)
    private Long idReactivo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reactivo", insertable = false, updatable = false)
    private TemplateEntity template;

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
