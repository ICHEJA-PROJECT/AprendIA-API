package com.icheha.aprendia_api.preferences.words.domain.entities;

import java.util.List;
import java.util.Objects;

/**
 * Entidad de dominio Word
 * Representa una palabra en el sistema
 * Contiene la lógica de negocio relacionada con palabras
 */
public class Word {
    
    private final Long id;
    private final String word;
    private final List<WordMeaning> meanings;
    private final List<WordOccupation> occupations;
    private final List<WordRegion> regions;
    
    // Constructor privado para usar Builder
    private Word(Builder builder) {
        this.id = builder.id;
        this.word = builder.word;
        this.meanings = builder.meanings;
        this.occupations = builder.occupations;
        this.regions = builder.regions;
    }
    
    // Getters
    public Long getId() { return id; }
    public String getWord() { return word; }
    public List<WordMeaning> getMeanings() { return meanings; }
    public List<WordOccupation> getOccupations() { return occupations; }
    public List<WordRegion> getRegions() { return regions; }
    
    /**
     * Verifica si la palabra tiene significados asociados
     */
    public boolean hasMeanings() {
        return meanings != null && !meanings.isEmpty();
    }
    
    /**
     * Verifica si la palabra tiene ocupaciones asociadas
     */
    public boolean hasOccupations() {
        return occupations != null && !occupations.isEmpty();
    }
    
    /**
     * Verifica si la palabra tiene regiones asociadas
     */
    public boolean hasRegions() {
        return regions != null && !regions.isEmpty();
    }
    
    /**
     * Obtiene el número total de significados asociados
     */
    public int getMeaningCount() {
        return meanings != null ? meanings.size() : 0;
    }
    
    /**
     * Obtiene el número total de ocupaciones asociadas
     */
    public int getOccupationCount() {
        return occupations != null ? occupations.size() : 0;
    }
    
    /**
     * Obtiene el número total de regiones asociadas
     */
    public int getRegionCount() {
        return regions != null ? regions.size() : 0;
    }
    
    /**
     * Obtiene el primer significado de la palabra
     */
    public String getFirstMeaning() {
        if (meanings == null || meanings.isEmpty()) {
            return null;
        }
        return meanings.get(0).getMeaning();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(id, word1.id) &&
               Objects.equals(word, word1.word);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, word);
    }
    
    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", meaningCount=" + getMeaningCount() +
                ", occupationCount=" + getOccupationCount() +
                ", regionCount=" + getRegionCount() +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long id;
        private String word;
        private List<WordMeaning> meanings;
        private List<WordOccupation> occupations;
        private List<WordRegion> regions;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder word(String word) {
            this.word = word;
            return this;
        }
        
        public Builder meanings(List<WordMeaning> meanings) {
            this.meanings = meanings;
            return this;
        }
        
        public Builder occupations(List<WordOccupation> occupations) {
            this.occupations = occupations;
            return this;
        }
        
        public Builder regions(List<WordRegion> regions) {
            this.regions = regions;
            return this;
        }
        
        public Word build() {
            return new Word(this);
        }
    }
}

