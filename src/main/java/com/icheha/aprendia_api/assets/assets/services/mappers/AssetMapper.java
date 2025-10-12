package com.icheha.aprendia_api.assets.assets.services.mappers;

import com.icheha.aprendia_api.assets.assets.data.dtos.request.CreateAssetDto;
import com.icheha.aprendia_api.assets.assets.data.dtos.response.AssetResponseDto;
import com.icheha.aprendia_api.assets.assets.domain.entities.Asset;
import org.springframework.stereotype.Component;

@Component("assetServiceMapper")
public class AssetMapper {
    public Asset toDomain(CreateAssetDto dto) {
        return Asset.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .url(dto.getUrl())
                .build();
    }

    public AssetResponseDto toResponseDto(Asset domain) {
        AssetResponseDto dto = new AssetResponseDto();
        dto.setId(domain.getId());
        dto.setName(domain.getName());
        dto.setDescription(domain.getDescription());
        dto.setUrl(domain.getUrl());
        return dto;
    }
}