package com.icheha.aprendia_api.assets.assets.data.repositories;

import com.icheha.aprendia_api.assets.assets.data.entities.AssetEntity;
import com.icheha.aprendia_api.assets.assets.data.mappers.AssetMapper;
import com.icheha.aprendia_api.assets.assets.domain.entities.Asset;
import com.icheha.aprendia_api.assets.assets.domain.repositories.IAssetRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AssetRepositoryAdapter implements IAssetRepository {
    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    public AssetRepositoryAdapter(
            AssetRepository assetRepository,
            @org.springframework.beans.factory.annotation.Qualifier("assetDataMapper") AssetMapper assetMapper
    ) {
        this.assetRepository = assetRepository;
        this.assetMapper = assetMapper;
    }


    @Override
    public Asset save(Asset asset) {
        AssetEntity entity = assetMapper.toEntity(asset);
        AssetEntity savedEntity = assetRepository.save(entity);
        return assetMapper.toDomain(savedEntity);
    }
}
