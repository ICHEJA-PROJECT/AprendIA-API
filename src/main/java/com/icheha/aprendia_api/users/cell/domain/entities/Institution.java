package com.icheha.aprendia_api.users.cell.domain.entities;

import java.util.List;
import java.util.Objects;

/**
 * Entidad de dominio Institution
 * Representa una institución educativa
 */
public class Institution {
    
    private final Long id;
    private final String rfc;
    private final String rct;
    private final String name;
    private final List<Cell> cells;
    
    private Institution(Builder builder) {
        this.id = builder.id;
        this.rfc = builder.rfc;
        this.rct = builder.rct;
        this.name = builder.name;
        this.cells = builder.cells;
    }
    
    public Long getId() { return id; }
    public String getRfc() { return rfc; }
    public String getRct() { return rct; }
    public String getName() { return name; }
    public List<Cell> getCells() { return cells; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Institution that = (Institution) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    public static class Builder {
        private Long id;
        private String rfc;
        private String rct;
        private String name;
        private List<Cell> cells;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder rfc(String rfc) {
            this.rfc = rfc;
            return this;
        }
        
        public Builder rct(String rct) {
            this.rct = rct;
            return this;
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder cells(List<Cell> cells) {
            this.cells = cells;
            return this;
        }
        
        public Institution build() {
            return new Institution(this);
        }
    }
}

