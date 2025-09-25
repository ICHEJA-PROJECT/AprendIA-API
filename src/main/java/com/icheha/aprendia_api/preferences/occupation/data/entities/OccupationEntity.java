package com.icheha.aprendia_api.preferences.occupation.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entidad JPA para Occupation
 * Mapea a la tabla 'ocupacion'
 */
@Entity
@Table(name = "ocupacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OccupationEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ocupacion")
    private Long id;
    
    @Column(name = "nombre", length = 32, nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "occupation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentOccupationEntity> students;
    
    
    @OneToMany(mappedBy = "occupation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExerciseOccupationEntity> exercises;
}
