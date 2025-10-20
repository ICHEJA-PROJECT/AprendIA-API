package com.icheha.aprendia_api.assets.assets.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAssetResponseDto {
    private Long id;
    private String name;
    private String url;
    private String description;
}
