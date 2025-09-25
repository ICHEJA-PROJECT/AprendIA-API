package com.icheha.aprendia_api.preferences.words.data.entities;

import com.icheha.aprendia_api.preferences.region.data.entities.WordRegionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entidad JPA para Word
 * Mapea a la tabla 'palabra'
 */
@Entity
@Table(name = "palabra")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_palabra")
    private Long id;
    
    @Column(name = "nombre", columnDefinition = "TEXT", nullable = false)
    private String word;
    
    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WordMeaningEntity> meanings;
    
    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WordOccupationEntity> occupations;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_palabra")
    private List<WordRegionEntity> regions;
}
