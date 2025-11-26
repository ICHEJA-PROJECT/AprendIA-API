package com.icheha.aprendia_api.auth.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenPayloadDto {
    private Long idPersona;
    private String username;
    private String nombre;
    private String roleName;
    private String disabilityName;
    private Long disabilityId;
    private Long learningPathId;
    private Long studentId;
    private Long iat;
    private Long exp;
    // Información de parientes
    private ParienteInfoDto padre;
    private ParienteInfoDto madre;
}
