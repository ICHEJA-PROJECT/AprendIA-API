package com.icheha.aprendia_api.assets.assets.data.mappers;

import com.icheha.aprendia_api.assets.assets.data.entities.AssetEntity;
import com.icheha.aprendia_api.assets.assets.domain.entities.Asset;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("assetDataMapper")
public class AssetMapper {

    public Asset toDomain(AssetEntity entity) {
        if (entity == null) {
            return null;
        }

        return Asset.builder()
                .id(entity.getId())
                .name(entity.getName())
                .url(entity.getUrl())
                .description(entity.getDescription())
                .vector(entity.getVector())
                .build();
    }

    public AssetEntity toEntity(Asset domain) {
        if (domain == null) {
            return null;
        }

        AssetEntity entity = new AssetEntity();
        entity.setName(domain.getName());
        entity.setUrl(domain.getUrl());
        entity.setDescription(domain.getDescription());
        entity.setVector(domain.getVector());
        return entity;
    }
}