package com.icheha.aprendia_api.preferences.region.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase de ID compuesto para WordRegionEntity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordRegionId implements Serializable {
    
    private Long wordId;
    private Long regionId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordRegionId that = (WordRegionId) o;
        return Objects.equals(wordId, that.wordId) &&
               Objects.equals(regionId, that.regionId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(wordId, regionId);
    }
}
