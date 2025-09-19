package com.icheha.aprendia_api.auth.data.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateTokenResponseDto {
    
    @Schema(description = "Whether the token is valid", example = "true")
    private Boolean valid;
    
    @Schema(description = "Whether the token is expired", example = "false")
    private Boolean expired;
    
    @Schema(description = "Token payload if valid or expired", example = "null")
    private TokenPayloadDto payload;
    
    @Schema(description = "Validation message", example = "Token is valid")
    private String message;
    
}
