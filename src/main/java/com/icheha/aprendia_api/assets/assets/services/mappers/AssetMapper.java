package com.icheha.aprendia_api.assets.assets.services.mappers;

import com.icheha.aprendia_api.assets.assets.data.dtos.response.CreateAssetResponseDto;
import com.icheha.aprendia_api.assets.assets.domain.entities.Asset;
import org.springframework.stereotype.Component;

@Component("assetServiceMapper")
public class AssetMapper {

    public CreateAssetResponseDto toResponseDto(Asset domain) {
        if (domain == null) {
            return null;
        }

        CreateAssetResponseDto dto = new CreateAssetResponseDto();
        dto.setId(domain.getId());
        dto.setUrl(domain.getUrl());
        dto.setName(domain.getName());
        dto.setDescription(domain.getDescription());
        return dto;
    }
}
