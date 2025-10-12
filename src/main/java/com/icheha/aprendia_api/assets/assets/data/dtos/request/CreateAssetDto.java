package com.icheha.aprendia_api.assets.assets.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAssetDto {
    private String name;
    private String description;
    private String url;

    public String getName(){ return name; }

    public String getDescription(){ return description; }

    public String getUrl(){ return url; }

}
