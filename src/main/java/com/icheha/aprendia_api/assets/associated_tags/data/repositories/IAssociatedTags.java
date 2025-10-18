package com.icheha.aprendia_api.assets.associated_tags.data.repositories;

import com.icheha.aprendia_api.assets.associated_tags.data.entities.AssociatedTagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAssociatedTags extends JpaRepository<AssociatedTagsEntity, Long> {

    // Buscar las tags asociadas a la imagen
    @Query("SELECT A FROM AssociatedTagsEntity A WHERE A.id_asset = :id_asset")
    List<AssociatedTagsEntity> findByIdAsset(@Param("id_asset") Long id_asset);

    // Buscar imagenes asociadas a una tag
    @Query("SELECT A FROM AssociatedTagsEntity A WHERE A.id_tag = :id_tag")
    List<AssociatedTagsEntity> findByIdTag(@Param("id_tag") Long id_tag);

}
