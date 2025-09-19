package com.icheha.aprendia_api.exercises.exercises.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "ejercicio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ejercicio")
    private Long idEjercicio;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "contexto", nullable = false)
    private String contexto;

    @Column(name = "id_reactivo", nullable = false)
    private Long idReactivo;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reactivo", insertable = false, updatable = false)
    private com.icheha.aprendia_api.exercises.templates.domain.entities.TemplateEntity reactivo;
}
