package com.icheha.aprendia_api.users.cell.domain.entities;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Entidad de dominio Cell
 * Representa una célula educativa
 */
public class Cell {
    
    private final Long id;
    private final Institution institution;
    private final Persona coordinator;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final List<TeacherCell> teachers;
    
    private Cell(Builder builder) {
        this.id = builder.id;
        this.institution = builder.institution;
        this.coordinator = builder.coordinator;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.teachers = builder.teachers;
    }
    
    public Long getId() { return id; }
    public Institution getInstitution() { return institution; }
    public Persona getCoordinator() { return coordinator; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public List<TeacherCell> getTeachers() { return teachers; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return Objects.equals(id, cell.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    public static class Builder {
        private Long id;
        private Institution institution;
        private Persona coordinator;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private List<TeacherCell> teachers;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder institution(Institution institution) {
            this.institution = institution;
            return this;
        }
        
        public Builder coordinator(Persona coordinator) {
            this.coordinator = coordinator;
            return this;
        }
        
        public Builder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }
        
        public Builder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }
        
        public Builder teachers(List<TeacherCell> teachers) {
            this.teachers = teachers;
            return this;
        }
        
        public Cell build() {
            return new Cell(this);
        }
    }
}

