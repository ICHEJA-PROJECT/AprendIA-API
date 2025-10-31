package com.icheha.aprendia_api.assets.assets.data.entities;

import io.hypersistence.utils.hibernate.type.array.FloatArrayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "activo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activo")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "descripcion", nullable = false)
    private String description;

    @Type(FloatArrayType.class)
    @Column(name = "vector", nullable = false, columnDefinition = "vector(384)")
    private float[] vector;
}