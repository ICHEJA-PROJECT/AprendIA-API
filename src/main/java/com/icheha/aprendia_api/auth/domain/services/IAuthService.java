package com.icheha.aprendia_api.auth.domain.services;

import com.icheha.aprendia_api.auth.data.dtos.request.LoginCredentialsDto;
import com.icheha.aprendia_api.auth.data.dtos.request.LoginQrDto;
import com.icheha.aprendia_api.auth.data.dtos.response.LoginResponseDto;
import com.icheha.aprendia_api.auth.data.dtos.response.ValidateTokenResponseDto;

/**
 * Interface del servicio de autenticación
 * Define los métodos principales para autenticación de usuarios
 */
public interface IAuthService {
    
    /**
     * Autenticar usuario usando credenciales (CURP y password)
     * @param loginDto Datos de login con credenciales
     * @return Respuesta de login exitoso con token y payload
     */
    LoginResponseDto loginWithCredentials(LoginCredentialsDto loginDto);
    
    /**
     * Autenticar usuario usando token QR encriptado
     * @param loginDto Datos de login con token QR
     * @return Respuesta de login exitoso con token y payload
     */
    LoginResponseDto loginWithQR(LoginQrDto loginDto);
    
    /**
     * Validar token JWT y extraer información del payload
     * @param token Token JWT a validar
     * @return Resultado de validación del token
     */
    ValidateTokenResponseDto validateToken(String token);
}
