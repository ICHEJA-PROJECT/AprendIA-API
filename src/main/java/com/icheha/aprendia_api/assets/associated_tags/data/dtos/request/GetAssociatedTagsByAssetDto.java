package com.icheha.aprendia_api.assets.associated_tags.data.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GetAssociatedTagsByAssetDto {
    @NotEmpty(message = "Asset IDs no puede estar vacío")
    private List<Long> assetIds;
}
