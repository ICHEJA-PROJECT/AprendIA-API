package com.icheha.aprendia_api.users.person.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSettlementDto {
    
    private String nombre;
    
    private Long zipcodeId;
    
    private Long settlementTypeId;
    
    private Long municipalityId;
    
    private Long townId;
}

