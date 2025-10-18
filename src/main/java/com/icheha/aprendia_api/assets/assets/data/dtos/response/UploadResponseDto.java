package com.icheha.aprendia_api.assets.assets.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadResponseDto {
    private String publicId;
    private String secureUrl;
}
