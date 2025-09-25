package com.icheha.aprendia_api.preferences.words.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio WordMeaning
 * Representa un significado de una palabra
 */
public class WordMeaning {
    
    private final Long meaningId;
    private final Word word;
    private final String meaning;
    
    // Constructor privado para usar Builder
    private WordMeaning(Builder builder) {
        this.meaningId = builder.meaningId;
        this.word = builder.word;
        this.meaning = builder.meaning;
    }
    
    // Getters
    public Long getMeaningId() { return meaningId; }
    public Word getWord() { return word; }
    public String getMeaning() { return meaning; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordMeaning that = (WordMeaning) o;
        return Objects.equals(meaningId, that.meaningId) &&
               Objects.equals(meaning, that.meaning);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(meaningId, meaning);
    }
    
    @Override
    public String toString() {
        return "WordMeaning{" +
                "meaningId=" + meaningId +
                ", meaning='" + meaning + '\'' +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long meaningId;
        private Word word;
        private String meaning;
        
        public Builder meaningId(Long meaningId) {
            this.meaningId = meaningId;
            return this;
        }
        
        public Builder word(Word word) {
            this.word = word;
            return this;
        }
        
        public Builder meaning(String meaning) {
            this.meaning = meaning;
            return this;
        }
        
        public WordMeaning build() {
            return new WordMeaning(this);
        }
    }
}
