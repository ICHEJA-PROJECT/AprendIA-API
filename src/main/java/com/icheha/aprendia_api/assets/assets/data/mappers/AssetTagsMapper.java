package com.icheha.aprendia_api.assets.assets.data.mappers;

import com.icheha.aprendia_api.assets.assets.data.dtos.response.FindAssetDB;
import com.icheha.aprendia_api.assets.assets.domain.entities.AssetTags;
import org.springframework.stereotype.Component;

@Component("assetTagsDataMapper")
public class AssetTagsMapper {
    public AssetTags toDomain(FindAssetDB entity) {
        if (entity == null) {
            return null;
        }

        return AssetTags.builder()
                .id(entity.getId())
                .name(entity.getName())
                .url(entity.getUrl())
                .description(entity.getDescription())
                .tags(entity.getTags())
                .build();
    }
}
