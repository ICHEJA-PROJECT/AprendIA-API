package com.icheha.aprendia_api.assets.associated_tags.data.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAssociatedTagsDto {
    @NotNull(message = "Asset ID es requerido")
    private Long id_asset;

    @NotNull(message = "Tag ID es requerido")
    private Long id_tag;
}
