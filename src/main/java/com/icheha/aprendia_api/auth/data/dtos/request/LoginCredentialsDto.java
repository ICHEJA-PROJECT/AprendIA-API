package com.icheha.aprendia_api.auth.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCredentialsDto {
    
    @NotBlank(message = "CURP es requerido")
    @Size(min = 18, max = 18, message = "CURP debe tener exactamente 18 caracteres")
    private String curp;

    @NotBlank(message = "Password es requerido")
    @Size(min = 6, message = "Password debe tener al menos 6 caracteres")
    private String password;
    
    // Manual getters to ensure compilation
    public String getCurp() {
        return curp;
    }
    
    public String getPassword() {
        return password;
    }
}
