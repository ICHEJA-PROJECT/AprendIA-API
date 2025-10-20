package com.icheha.aprendia_api.assets.assets.data.repositories;

import com.icheha.aprendia_api.assets.assets.data.entities.AssetEntity;
import com.icheha.aprendia_api.assets.assets.data.mappers.AssetMapper;
import com.icheha.aprendia_api.assets.assets.data.mappers.AssetTagsMapper;
import com.icheha.aprendia_api.assets.assets.domain.entities.Asset;
import com.icheha.aprendia_api.assets.assets.domain.entities.AssetTags;
import com.icheha.aprendia_api.assets.assets.domain.repositories.IAssetRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AssetRepositoryAdapter implements IAssetRepository {

    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;
    private final AssetTagsMapper  assetTagsMapper;

    public AssetRepositoryAdapter(
            AssetRepository assetRepository,
            @Qualifier("assetDataMapper") AssetMapper assetMapper,
            @Qualifier("assetTagsDataMapper") AssetTagsMapper assetTagsMapper
    ) {
        this.assetRepository = assetRepository;
        this.assetMapper = assetMapper;
        this.assetTagsMapper = assetTagsMapper;
    }

    @Override
    public Asset save(Asset asset) {
        AssetEntity entity = assetMapper.toEntity(asset);
        AssetEntity savedEntity = assetRepository.save(entity);
        return assetMapper.toDomain(savedEntity);
    }

    @Override
    public List<AssetTags>  findByTags(List<Long> tags) {
        return assetRepository.getAssetByTags(tags).stream()
                .map(assetTagsMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssetTags>  findByDescription(String description) {
        return null;
    }


}