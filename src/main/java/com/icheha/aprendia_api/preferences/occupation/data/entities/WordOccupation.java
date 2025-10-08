package com.icheha.aprendia_api.preferences.occupation.data.entities;

import com.icheha.aprendia_api.preferences.words.data.entities.WordEntity;
import jakarta.persistence.*;

/**
 * Entidad JPA para WordOccupation
 * Mapea a la tabla 'palabra_ocupacion'
 */
@Entity
@Table(name = "palabra_ocupacion")
public class WordOccupation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_palabra", nullable = false)
    private WordEntity word;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ocupacion", nullable = false)
    private OccupationEntity occupation;

    // Constructores, getters y setters
    public WordOccupation() {}

    public WordOccupation(WordEntity word, OccupationEntity occupation) {
        this.word = word;
        this.occupation = occupation;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public WordEntity getWord() { return word; }
    public void setWord(WordEntity word) { this.word = word; }

    public OccupationEntity getOccupation() { return occupation; }
    public void setOccupation(OccupationEntity occupation) { this.occupation = occupation; }
}
