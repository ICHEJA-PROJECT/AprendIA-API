package com.icheha.aprendia_api.preferences.words.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio WordOccupation
 * Representa la relación entre una palabra y una ocupación
 */
public class WordOccupation {
    
    private final Long wordId;
    private final Long occupationId;
    private final Word word;
    private final Object occupation; // Referencia a Occupation del módulo occupation
    
    // Constructor privado para usar Builder
    private WordOccupation(Builder builder) {
        this.wordId = builder.wordId;
        this.occupationId = builder.occupationId;
        this.word = builder.word;
        this.occupation = builder.occupation;
    }
    
    // Getters
    public Long getWordId() { return wordId; }
    public Long getOccupationId() { return occupationId; }
    public Word getWord() { return word; }
    public Object getOccupation() { return occupation; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordOccupation that = (WordOccupation) o;
        return Objects.equals(wordId, that.wordId) &&
               Objects.equals(occupationId, that.occupationId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(wordId, occupationId);
    }
    
    @Override
    public String toString() {
        return "WordOccupation{" +
                "wordId=" + wordId +
                ", occupationId=" + occupationId +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long wordId;
        private Long occupationId;
        private Word word;
        private Object occupation;
        
        public Builder wordId(Long wordId) {
            this.wordId = wordId;
            return this;
        }
        
        public Builder occupationId(Long occupationId) {
            this.occupationId = occupationId;
            return this;
        }
        
        public Builder word(Word word) {
            this.word = word;
            return this;
        }
        
        public Builder occupation(Object occupation) {
            this.occupation = occupation;
            return this;
        }
        
        public WordOccupation build() {
            return new WordOccupation(this);
        }
    }
}
