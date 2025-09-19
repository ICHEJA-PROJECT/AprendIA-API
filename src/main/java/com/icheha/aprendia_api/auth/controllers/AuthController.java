package com.icheha.aprendia_api.auth.controllers;


import com.icheha.aprendia_api.auth.data.dtos.request.LoginCredentialsDto;
import com.icheha.aprendia_api.auth.data.dtos.request.LoginQrDto;
import com.icheha.aprendia_api.auth.data.dtos.request.ValidateTokenDto;
import com.icheha.aprendia_api.auth.data.dtos.response.LoginResponseDto;
import com.icheha.aprendia_api.auth.data.dtos.response.ValidateTokenResponseDto;
import com.icheha.aprendia_api.auth.domain.services.impl.AuthServiceImpl;
import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints de autenticación")
public class AuthController {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @Autowired
    private AuthServiceImpl authServiceImpl;
    
    @PostMapping("/login/credentials")
    @Operation(
        summary = "Login con credenciales",
        description = "Autenticar usuario usando CURP y contraseña"
    )
    @ApiResponse(responseCode = "200", description = "Login exitoso")
    @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<BaseResponse<LoginResponseDto>> loginWithCredentials(@Valid @RequestBody LoginCredentialsDto loginDto) {
        try {
            logger.debug("Received login request for CURP: {}", loginDto.getCurp());
            
            LoginResponseDto response = authServiceImpl.loginWithCredentials(loginDto);
            
            BaseResponse<LoginResponseDto> baseResponse = new BaseResponse<>(
                true,
                response,
                "Login exitoso",
                HttpStatus.OK
            );
            
            return baseResponse.buildResponseEntity();
        } catch (RuntimeException e) {
            logger.error("Login failed for CURP: {}", loginDto.getCurp(), e);
            
            BaseResponse<LoginResponseDto> baseResponse = new BaseResponse<>(
                false,
                null,
                e.getMessage(),
                HttpStatus.UNAUTHORIZED
            );
            
            return baseResponse.buildResponseEntity();
        }
    }
    
    @PostMapping("/login/qr")
    @Operation(
        summary = "Login con código QR",
        description = "Autenticar usuario usando token encriptado del código QR"
    )
    @ApiResponse(responseCode = "200", description = "Login exitoso")
    @ApiResponse(responseCode = "401", description = "Token QR inválido o usuario no encontrado")
    public ResponseEntity<BaseResponse<LoginResponseDto>> loginWithQR(@Valid @RequestBody LoginQrDto loginDto) {
        try {
            logger.debug("Received QR login request");
            
            LoginResponseDto response = authServiceImpl.loginWithQR(loginDto);
            
            BaseResponse<LoginResponseDto> baseResponse = new BaseResponse<>(
                true,
                response,
                "Login con QR exitoso",
                HttpStatus.OK
            );
            
            return baseResponse.buildResponseEntity();
        } catch (RuntimeException e) {
            logger.error("QR login failed", e);
            
            BaseResponse<LoginResponseDto> baseResponse = new BaseResponse<>(
                false,
                null,
                e.getMessage(),
                HttpStatus.UNAUTHORIZED
            );
            
            return baseResponse.buildResponseEntity();
        }
    }
    
    @PostMapping("/validate-token")
    @Operation(
        summary = "Validar token JWT",
        description = "Validar token JWT y extraer información del payload, verificar si expiró"
    )
    @ApiResponse(responseCode = "200", description = "Resultado de validación del token")
    public ResponseEntity<BaseResponse<ValidateTokenResponseDto>> validateToken(@Valid @RequestBody ValidateTokenDto validateDto) {
        try {
            logger.debug("Received token validation request");
            
            ValidateTokenResponseDto response = authServiceImpl.validateToken(validateDto.getToken());
            
            BaseResponse<ValidateTokenResponseDto> baseResponse = new BaseResponse<>(
                true,
                response,
                "Validación de token completada",
                HttpStatus.OK
            );
            
            return baseResponse.buildResponseEntity();
        } catch (Exception e) {
            logger.error("Token validation failed", e);
            
            BaseResponse<ValidateTokenResponseDto> baseResponse = new BaseResponse<>(
                false,
                null,
                "Error en la validación del token",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
            
            return baseResponse.buildResponseEntity();
        }
    }
}
