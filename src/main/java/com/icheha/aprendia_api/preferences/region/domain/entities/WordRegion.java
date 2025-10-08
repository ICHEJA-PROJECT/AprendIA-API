package com.icheha.aprendia_api.preferences.region.domain.entities;

import java.util.Objects;

/**
 * Entidad de dominio WordRegion
 * Representa la relación entre una palabra y una región
 */
public class WordRegion {
    
    private final Long wordId;
    private final Long regionId;
    private final Region region;
    
    // Constructor privado para usar Builder
    private WordRegion(Builder builder) {
        this.wordId = builder.wordId;
        this.regionId = builder.regionId;
        this.region = builder.region;
    }
    
    // Getters
    public Long getWordId() { return wordId; }
    public Long getRegionId() { return regionId; }
    public Region getRegion() { return region; }
    
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
        private Region region;
        
        public Builder wordId(Long wordId) {
            this.wordId = wordId;
            return this;
        }
        
        public Builder regionId(Long regionId) {
            this.regionId = regionId;
            return this;
        }
        
        public Builder region(Region region) {
            this.region = region;
            return this;
        }
        
        public WordRegion build() {
            return new WordRegion(this);
        }
    }
}

