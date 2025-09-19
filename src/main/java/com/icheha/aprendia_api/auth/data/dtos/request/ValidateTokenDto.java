package com.icheha.aprendia_api.auth.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateTokenDto {
    
    @NotBlank(message = "Token es requerido")
    private String token;
    
    // Manual getter to ensure compilation
    public String getToken() {
        return token;
    }
}
