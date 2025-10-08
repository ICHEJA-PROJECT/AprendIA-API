package com.icheha.aprendia_api.auth.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateTokenResponseDto {
    private Boolean isValid;
    private Boolean isExpired;
    private TokenPayloadDto payload;
    private String message;
}
