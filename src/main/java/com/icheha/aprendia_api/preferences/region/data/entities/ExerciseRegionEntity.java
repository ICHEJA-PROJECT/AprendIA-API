package com.icheha.aprendia_api.preferences.region.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA para ExerciseRegion
 * Mapea a la tabla 'ejercicio_region'
 */
@Entity
@Table(name = "ejercicio_region")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ExerciseRegionId.class)
public class ExerciseRegionEntity {
    
    @Id
    @Column(name = "id_ejercicio", nullable = false)
    private Long exerciseId;
    
    @Id
    @Column(name = "id_region", nullable = false)
    private Long regionId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_region", insertable = false, updatable = false)
    private RegionEntity region;
}
