package com.icheha.aprendia_api.exercises.exercises.data.entities;

import com.icheha.aprendia_api.exercises.templates.data.entities.TemplateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plantilla")
    private TemplateEntity template;
}
