package com.icheha.aprendia_api.preferences.impairments.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "discapacidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"students", "reactives", "resources", "learningPaths"})
@EqualsAndHashCode(exclude = {"students", "reactives", "resources", "learningPaths"})
public class Impairment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discapacidad")
    private Long id;

    @Column(name = "nombre", length = 32, nullable = false)
    private String name;

    @OneToMany(mappedBy = "impairment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentImpairment> students;

    @OneToMany(mappedBy = "impairment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReactiveImpairment> reactives;

    @OneToMany(mappedBy = "impairment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResourceImpairment> resources;

    @OneToMany(mappedBy = "impairment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LearningPathImpairment> learningPaths;
}
