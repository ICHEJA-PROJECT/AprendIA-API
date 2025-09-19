package com.icheha.aprendia_api.exercises.layouts.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tipo_layouts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeLayoutEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_layout")
    private Long idTipoLayout;

    @Column(name = "nombre", length = 64, nullable = false)
    private String nombre;

    // Relaciones
    @OneToMany(mappedBy = "tipoLayout", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LayoutEntity> layouts;
}
