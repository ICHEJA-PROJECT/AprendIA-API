package com.icheha.aprendia_api.preferences.words.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio WordRegion
 * Representa la relación entre una palabra y una región
 */
public class WordRegion {
    
    private final Long wordId;
    private final Long regionId;
    private final Word word;
    private final Object region; // Referencia a Region del módulo region
    
    // Constructor privado para usar Builder
    private WordRegion(Builder builder) {
        this.wordId = builder.wordId;
        this.regionId = builder.regionId;
        this.word = builder.word;
        this.region = builder.region;
    }
    
    // Getters
    public Long getWordId() { return wordId; }
    public Long getRegionId() { return regionId; }
    public Word getWord() { return word; }
    public Object getRegion() { return region; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordRegion that = (WordRegion) o;
        return Objects.equals(wordId, that.wordId) &&
               Objects.equals(regionId, that.regionId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(wordId, regionId);
    }
    
    @Override
    public String toString() {
        return "WordRegion{" +
                "wordId=" + wordId +
                ", regionId=" + regionId +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long wordId;
        private Long regionId;
        private Word word;
        private Object region;
        
        public Builder wordId(Long wordId) {
            this.wordId = wordId;
            return this;
        }
        
        public Builder regionId(Long regionId) {
            this.regionId = regionId;
            return this;
        }
        
        public Builder word(Word word) {
            this.word = word;
            return this;
        }
        
        public Builder region(Object region) {
            this.region = region;
            return this;
        }
        
        public WordRegion build() {
            return new WordRegion(this);
        }
    }
}

