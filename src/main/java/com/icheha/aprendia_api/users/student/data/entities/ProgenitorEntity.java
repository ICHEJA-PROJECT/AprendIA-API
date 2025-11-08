package com.icheha.aprendia_api.users.student.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "progenitor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgenitorEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_progenitor")
    private Long id;
    
    @Column(name = "curp", length = 18, nullable = false)
    private String curp;
    
    @Column(name = "primer_nombre", length = 32, nullable = false)
    private String primerNombre;
    
    @Column(name = "segundo_nombre", length = 32, nullable = false)
    private String segundoNombre;
    
    @Column(name = "primer_apellido", length = 32, nullable = false)
    private String primerApellido;
    
    @Column(name = "segundo_apellido", length = 32, nullable = false)
    private String segundoApellido;
    
    @OneToMany(mappedBy = "father", fetch = FetchType.LAZY)
    private List<StudentEntity> studentsAsFather;
    
    @OneToMany(mappedBy = "mother", fetch = FetchType.LAZY)
    private List<StudentEntity> studentsAsMother;
}

