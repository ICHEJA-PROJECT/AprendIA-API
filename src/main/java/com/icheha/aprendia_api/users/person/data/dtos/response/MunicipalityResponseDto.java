package com.icheha.aprendia_api.users.person.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MunicipalityResponseDto {
    
    private Long id;
    private String nombre;
    private Long stateId;
    private String stateName;
}

