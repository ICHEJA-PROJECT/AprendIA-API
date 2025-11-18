package com.icheha.aprendia_api.assets.assets.domain.repositories;

import com.icheha.aprendia_api.assets.assets.domain.entities.Asset;
import com.icheha.aprendia_api.assets.assets.domain.entities.AssetTags;

import java.util.List;
import java.util.Optional;

public interface IAssetRepository {
    Asset save(Asset asset);
    List<AssetTags> findByTags(List<Long> tags);
    List<AssetTags> findByDescription(float[] description);
    Optional<Asset> findById(Long id);
    Optional<AssetTags> findByIdWithTags(Long id);
    void delete(Long id);
}