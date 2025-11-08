package com.icheha.aprendia_api.users.person.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoadTypeResponseDto {
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("nombre")
    private String nombre;
}

