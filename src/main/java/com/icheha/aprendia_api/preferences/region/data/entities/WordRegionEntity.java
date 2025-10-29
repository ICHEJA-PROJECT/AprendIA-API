package com.icheha.aprendia_api.preferences.region.data.entities;

import com.icheha.aprendia_api.preferences.region.domain.WordRegionId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA para WordRegion
 * Mapea a la tabla 'region_palabras'
 */
@Entity
@Table(name = "region_palabras")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(WordRegionId.class)
public class WordRegionEntity {
    
    @Id
    @Column(name = "id_palabra", nullable = false)
    private Long wordId;
    
    @Id
    @Column(name = "id_region", nullable = false)
    private Long regionId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_region", insertable = false, updatable = false)
    private RegionEntity region;
}

