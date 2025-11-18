package com.icheha.aprendia_api.assets.associated_tags.data.repositories;

import com.icheha.aprendia_api.assets.associated_tags.data.entities.AssociatedTagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AssociatedTagRepository extends JpaRepository<AssociatedTagsEntity, Long> {
    
    @Query("SELECT at FROM AssociatedTagsEntity at WHERE at.id_asset = :assetId")
    List<AssociatedTagsEntity> findByAssetId(@Param("assetId") Long assetId);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM AssociatedTagsEntity at WHERE at.id_asset = :assetId")
    void deleteByAssetId(@Param("assetId") Long assetId);
}
