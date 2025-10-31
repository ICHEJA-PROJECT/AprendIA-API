package com.icheha.aprendia_api.preferences.occupation.domain.entities;

import java.util.List;
import java.util.Objects;

/**
 * Entidad de dominio Occupation
 * Representa una ocupación en el sistema
 * Contiene la lógica de negocio relacionada con ocupaciones
 */
public class Occupation {
    
    private final Long id;
    private final String name;
    private final List<StudentOccupation> students;
    private final List<ExerciseOccupation> exercises;
    
    // Constructor privado para usar Builder
    private Occupation(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.students = builder.students;
        this.exercises = builder.exercises;
    }
    
    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<StudentOccupation> getStudents() { return students; }
    public List<ExerciseOccupation> getExercises() { return exercises; }
    
    /**
     * Verifica si la ocupación tiene estudiantes asociados
     */
    public boolean hasStudents() {
        return students != null && !students.isEmpty();
    }
    
    
    /**
     * Verifica si la ocupación tiene ejercicios asociados
     */
    public boolean hasExercises() {
        return exercises != null && !exercises.isEmpty();
    }
    
    /**
     * Obtiene el número total de estudiantes asociados
     */
    public int getStudentCount() {
        return students != null ? students.size() : 0;
    }
    
    
    /**
     * Obtiene el número total de ejercicios asociados
     */
    public int getExerciseCount() {
        return exercises != null ? exercises.size() : 0;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Occupation that = (Occupation) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    
    @Override
    public String toString() {
        return "Occupation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentCount=" + getStudentCount() +
                ", exerciseCount=" + getExerciseCount() +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long id;
        private String name;
        private List<StudentOccupation> students;
        private List<ExerciseOccupation> exercises;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder students(List<StudentOccupation> students) {
            this.students = students;
            return this;
        }
        
        
        public Builder exercises(List<ExerciseOccupation> exercises) {
            this.exercises = exercises;
            return this;
        }
        
        public Occupation build() {
            return new Occupation(this);
        }
    }
}
