package com.icheha.aprendia_api.preferences.region.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA para StudentRegion
 * Mapea a la tabla 'estudiante_region'
 */
@Entity
@Table(name = "estudiante_region")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(StudentRegionId.class)
public class StudentRegionEntity {
    
    @Id
    @Column(name = "id_estudiante", nullable = false)
    private Long studentId;
    
    @Id
    @Column(name = "id_region", nullable = false)
    private Long regionId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_region", insertable = false, updatable = false)
    private RegionEntity region;
}
