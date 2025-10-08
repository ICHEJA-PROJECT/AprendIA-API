package com.icheha.aprendia_api.preferences.region.domain.entities;

import java.util.List;
import java.util.Objects;

/**
 * Entidad de dominio Region
 * Representa una región en el sistema
 * Contiene la lógica de negocio relacionada con regiones
 */
public class Region {
    
    private final Long id;
    private final String name;
    private final List<StudentRegion> students;
    private final List<WordRegion> words;
    private final List<ExerciseRegion> exercises;
    
    // Constructor privado para usar Builder
    private Region(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.students = builder.students;
        this.words = builder.words;
        this.exercises = builder.exercises;
    }
    
    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<StudentRegion> getStudents() { return students; }
    public List<WordRegion> getWords() { return words; }
    public List<ExerciseRegion> getExercises() { return exercises; }
    
    /**
     * Verifica si la región tiene estudiantes asociados
     */
    public boolean hasStudents() {
        return students != null && !students.isEmpty();
    }
    
    /**
     * Verifica si la región tiene palabras asociadas
     */
    public boolean hasWords() {
        return words != null && !words.isEmpty();
    }
    
    /**
     * Verifica si la región tiene ejercicios asociados
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
     * Obtiene el número total de palabras asociadas
     */
    public int getWordCount() {
        return words != null ? words.size() : 0;
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
        Region region = (Region) o;
        return Objects.equals(id, region.id) &&
               Objects.equals(name, region.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    
    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentCount=" + getStudentCount() +
                ", wordCount=" + getWordCount() +
                ", exerciseCount=" + getExerciseCount() +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long id;
        private String name;
        private List<StudentRegion> students;
        private List<WordRegion> words;
        private List<ExerciseRegion> exercises;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder students(List<StudentRegion> students) {
            this.students = students;
            return this;
        }
        
        public Builder words(List<WordRegion> words) {
            this.words = words;
            return this;
        }
        
        public Builder exercises(List<ExerciseRegion> exercises) {
            this.exercises = exercises;
            return this;
        }
        
        public Region build() {
            return new Region(this);
        }
    }
}

