package com.icheha.aprendia_api.users.person.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSettlementDto {
    
    @NotBlank(message = "Nombre es requerido")
    private String nombre;
    
    @NotNull(message = "ID de código postal es requerido")
    private Long zipcodeId;
    
    @NotNull(message = "ID de tipo de asentamiento es requerido")
    private Long settlementTypeId;
    
    @NotNull(message = "ID de municipio es requerido")
    private Long municipalityId;
    
    private Long townId;
}

