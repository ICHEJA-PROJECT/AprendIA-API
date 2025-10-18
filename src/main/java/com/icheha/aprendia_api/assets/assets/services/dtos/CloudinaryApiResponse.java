package com.icheha.aprendia_api.assets.assets.services.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CloudinaryApiResponse {
    @JsonProperty("data")
    private CloudinaryData data;
}
