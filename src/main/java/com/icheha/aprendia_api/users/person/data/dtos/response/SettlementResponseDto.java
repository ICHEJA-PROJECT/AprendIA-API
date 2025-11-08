package com.icheha.aprendia_api.users.person.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettlementResponseDto {
    
    private Long id;
    private String nombre;
    private Long zipcodeId;
    private String zipcode;
    private Long settlementTypeId;
    private String settlementTypeName;
    private Long municipalityId;
    private String municipalityName;
    private Long townId;
    private String townName;
}

