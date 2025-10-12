package com.icheha.aprendia_api.assets.assets.data.dtos.response;

import lombok.Data;

@Data
public class AssetResponseDto {
    private Long id;
    private String name;
    private String description;
    private String url;
}