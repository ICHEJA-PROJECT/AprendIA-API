package com.icheha.aprendia_api.assets.assets.services.mappers;

import com.icheha.aprendia_api.assets.assets.data.dtos.response.FindAssetDto;
import com.icheha.aprendia_api.assets.assets.domain.entities.AssetTags;
import org.springframework.stereotype.Component;

@Component("assetTagServiceMapper")
public class AssetTagsMapper {

    public FindAssetDto toResponseDto(AssetTags domain) {
        if (domain == null) {
            return null;
        }

        FindAssetDto dto = new FindAssetDto();
        dto.setId(domain.getId());
        dto.setName(domain.getName());
        dto.setDescription(domain.getDescription());
        dto.setUrl(domain.getUrl());
        dto.setTags(domain.getTags());
        return dto;
    }
}
