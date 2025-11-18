package com.icheha.aprendia_api.exercises.topics.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entidad JPA para Unit
 * Mapea a la tabla 'unidad'
 * Una unidad pertenece a un cuadernillo y puede tener varios temas
 */
@Entity
@Table(name = "unidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad")
    private Long idUnidad;

    @Column(name = "nombre", length = 64, nullable = false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @Column(name = "id_cuadernillo", nullable = false)
    private Long idCuadernillo;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuadernillo", insertable = false, updatable = false)
    private CuadernilloEntity cuadernillo;

    @OneToMany(mappedBy = "unidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TopicEntity> temas;
}
