package com.icheha.aprendia_api.exercises.topics.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA para Unit
 * Mapea a la tabla 'unidad'
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

    // Relaciones
    // Nota: La relación con TopicEntity se eliminó porque la tabla 'tema' no tiene columna 'id_unidad'
    // Si se necesita esta relación en el futuro, agregar la columna 'id_unidad' a la tabla 'tema'
    // @OneToMany(mappedBy = "unidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<TopicEntity> temas;
}
