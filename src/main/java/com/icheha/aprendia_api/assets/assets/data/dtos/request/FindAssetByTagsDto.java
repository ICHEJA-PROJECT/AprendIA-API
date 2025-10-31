package com.icheha.aprendia_api.assets.assets.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindAssetByTagsDto {

    private List<Long> tagsIds;
}
