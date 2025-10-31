package com.icheha.aprendia_api.assets.assets.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAssetDto {
    private String name;
    private String description;
    private List<Long> tagIds;
}