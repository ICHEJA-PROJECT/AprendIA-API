package com.icheha.aprendia_api.preferences.words.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase de ID compuesto para WordOccupationEntity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordOccupationId implements Serializable {
    
    private Long wordId;
    private Long occupationId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordOccupationId that = (WordOccupationId) o;
        return Objects.equals(wordId, that.wordId) &&
               Objects.equals(occupationId, that.occupationId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(wordId, occupationId);
    }
}
