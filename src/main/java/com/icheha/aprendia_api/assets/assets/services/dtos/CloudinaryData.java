package com.icheha.aprendia_api.assets.assets.services.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CloudinaryData {
    @JsonProperty("publicId")
    private String publicId;

    @JsonProperty("secureUrl")
    private String secureUrl;

}
