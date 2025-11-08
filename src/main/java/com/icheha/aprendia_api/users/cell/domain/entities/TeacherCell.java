package com.icheha.aprendia_api.users.cell.domain.entities;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import java.util.Objects;

/**
 * Entidad de dominio TeacherCell
 * Representa la relación entre un educador y una célula
 */
public class TeacherCell {
    
    private final Long teacherId;
    private final Long cellId;
    private final Persona teacher;
    private final Cell cell;
    
    private TeacherCell(Builder builder) {
        this.teacherId = builder.teacherId;
        this.cellId = builder.cellId;
        this.teacher = builder.teacher;
        this.cell = builder.cell;
    }
    
    public Long getTeacherId() { return teacherId; }
    public Long getCellId() { return cellId; }
    public Persona getTeacher() { return teacher; }
    public Cell getCell() { return cell; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherCell that = (TeacherCell) o;
        return Objects.equals(teacherId, that.teacherId) &&
               Objects.equals(cellId, that.cellId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(teacherId, cellId);
    }
    
    public static class Builder {
        private Long teacherId;
        private Long cellId;
        private Persona teacher;
        private Cell cell;
        
        public Builder teacherId(Long teacherId) {
            this.teacherId = teacherId;
            return this;
        }
        
        public Builder cellId(Long cellId) {
            this.cellId = cellId;
            return this;
        }
        
        public Builder teacher(Persona teacher) {
            this.teacher = teacher;
            return this;
        }
        
        public Builder cell(Cell cell) {
            this.cell = cell;
            return this;
        }
        
        public TeacherCell build() {
            return new TeacherCell(this);
        }
    }
}

