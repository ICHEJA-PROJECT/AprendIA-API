package com.icheha.aprendia_api.preferences.words.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA para WordOccupation
 * Mapea a la tabla 'palabra_ocupacion'
 */
@Entity
@Table(name = "palabra_ocupacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(WordOccupationId.class)
public class WordOccupationEntity {
    
    @Id
    @Column(name = "id_palabra", nullable = false)
    private Long wordId;
    
    @Id
    @Column(name = "id_ocupacion", nullable = false)
    private Long occupationId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_palabra", insertable = false, updatable = false)
    private WordEntity word;
}
