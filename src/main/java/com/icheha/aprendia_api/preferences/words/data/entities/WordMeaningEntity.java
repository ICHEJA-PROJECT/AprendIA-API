package com.icheha.aprendia_api.preferences.words.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA para WordMeaning
 * Mapea a la tabla 'palabra_significado'
 */
@Entity
@Table(name = "palabra_significado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordMeaningEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_palabra_significado")
    private Long meaningId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_palabra")
    private WordEntity word;
    
    @Column(name = "significado", columnDefinition = "TEXT", nullable = false)
    private String meaning;
}

