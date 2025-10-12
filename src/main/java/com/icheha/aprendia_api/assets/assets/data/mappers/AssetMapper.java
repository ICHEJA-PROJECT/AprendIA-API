package com.icheha.aprendia_api.assets.assets.data.mappers;

import com.icheha.aprendia_api.assets.assets.data.entities.AssetEntity;
import com.icheha.aprendia_api.assets.assets.domain.entities.Asset;
import org.springframework.stereotype.Component;

@Component("assetDataMapper")
public class AssetMapper {

    public Asset toDomain(AssetEntity entity) {
        if (entity == null) {
            return null;
        }

        return Asset.builder()
                .id(entity.getId())
                .name(entity.getNombre())
                .description(entity.getDescription())
                .url(entity.getUrl())
                .build();
    }

    public AssetEntity toEntity(Asset domain) {
        if (domain == null) {
            return null;
        }

        return new AssetEntity(
                domain.getId(),
                domain.getName(),
                domain.getDescription(),
                domain.getUrl()
        );
    }
}
