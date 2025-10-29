package com.icheha.aprendia_api.preferences.occupation.data.entities;

import com.icheha.aprendia_api.preferences.occupation.domain.entities.ExerciseOccupationId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA para ExerciseOccupation
 * Mapea a la tabla 'ejercicio_ocupacion'
 */
@Entity
@Table(name = "ejercicio_ocupacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ExerciseOccupationId.class)
public class ExerciseOccupationEntity {
    
    @Id
    @Column(name = "id_ejercicio", nullable = false)
    private Long exerciseId;
    
    @Id
    @Column(name = "id_ocupacion", nullable = false)
    private Long occupationId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ocupacion", insertable = false, updatable = false)
    private OccupationEntity occupation;
}

