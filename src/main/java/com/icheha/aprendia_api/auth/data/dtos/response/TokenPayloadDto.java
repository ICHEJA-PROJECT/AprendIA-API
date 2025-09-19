package com.icheha.aprendia_api.auth.data.dtos.response;

import com.icheha.aprendia_api.auth.domain.interfaces.TokenPayloadI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenPayloadDto implements TokenPayloadI {
    private Long idPersona;
    private String nombre;
    private String roleName;
    private String disabilityName;
    private Long disabilityId;
    private Long learningPathId;
    private Long iat;
    private Long exp;
}
