package com.icheha.aprendia_api.users.student.domain.entities;

import com.icheha.aprendia_api.auth.domain.entities.Persona;

import java.util.Objects;

/**
 * Entidad de dominio Student (Educando)
 * Representa un estudiante del sistema
 */
public class Student {
    
    private final Long id;
    private final Persona persona;
    private final Persona teacher; // Educador (puede ser null)
    private final String qrPath;
    
    private Student(Builder builder) {
        this.id = builder.id;
        this.persona = builder.persona;
        this.teacher = builder.teacher;
        this.qrPath = builder.qrPath;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public Persona getPersona() {
        return persona;
    }
    
    public Persona getTeacher() {
        return teacher;
    }
    
    public String getQrPath() {
        return qrPath;
    }
    
    public boolean hasTeacher() {
        return teacher != null;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", persona=" + (persona != null ? persona.getCurp().getValue() : null) +
                ", teacher=" + (teacher != null ? teacher.getCurp().getValue() : null) +
                ", qrPath='" + qrPath + '\'' +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long id;
        private Persona persona;
        private Persona teacher;
        private String qrPath;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder persona(Persona persona) {
            this.persona = persona;
            return this;
        }
        
        public Builder teacher(Persona teacher) {
            this.teacher = teacher;
            return this;
        }
        
        public Builder qrPath(String qrPath) {
            this.qrPath = qrPath;
            return this;
        }
        
        public Student build() {
            return new Student(this);
        }
    }
}

