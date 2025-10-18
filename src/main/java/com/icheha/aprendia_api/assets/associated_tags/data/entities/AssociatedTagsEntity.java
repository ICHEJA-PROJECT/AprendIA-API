package com.icheha.aprendia_api.assets.associated_tags.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tags_asociadas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociatedTagsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_activo", nullable = false)
    private Long id_asset;

    @Column(name = "id_tag", nullable = false)
    private Long id_tag;

}
