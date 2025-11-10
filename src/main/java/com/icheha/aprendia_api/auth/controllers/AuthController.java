package com.icheha.aprendia_api.auth.controllers;


import com.icheha.aprendia_api.auth.data.dtos.request.LoginCredentialsDto;
import com.icheha.aprendia_api.auth.data.dtos.request.LoginQrDto;
import com.icheha.aprendia_api.auth.data.dtos.request.ValidateTokenDto;
import com.icheha.aprendia_api.auth.data.dtos.response.LoginResponseDto;
import com.icheha.aprendia_api.auth.data.dtos.response.ValidateTokenResponseDto;
import com.icheha.aprendia_api.auth.domain.exceptions.InvalidCredentialsException;
import com.icheha.aprendia_api.auth.domain.exceptions.InvalidTokenException;
import com.icheha.aprendia_api.auth.domain.exceptions.UserNotFoundException;
import com.icheha.aprendia_api.auth.domain.exceptions.UserRoleNotFoundException;
import com.icheha.aprendia_api.auth.services.IAuthService;
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
@Tag(name = "1. Authentication", description = "Endpoints de autenticación")
public class AuthController {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @Autowired
    private IAuthService authService;
    
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
            
            LoginResponseDto response = authService.loginWithCredentials(loginDto);
            
            BaseResponse<LoginResponseDto> baseResponse = new BaseResponse<LoginResponseDto>(
                true,
                response,
                "Login exitoso",
                HttpStatus.OK
            );
            
            return baseResponse.buildResponseEntity();
        } catch (InvalidCredentialsException | UserNotFoundException e) {
            logger.warn("Login failed for CURP: {} - {}", loginDto.getCurp(), e.getMessage());
            
            BaseResponse<LoginResponseDto> baseResponse = new BaseResponse<LoginResponseDto>(
                false,
                null,
                e.getMessage(),
                HttpStatus.UNAUTHORIZED
            );
            
            return baseResponse.buildResponseEntity();
        } catch (UserRoleNotFoundException e) {
            logger.warn("User role not found for CURP: {} - {}", loginDto.getCurp(), e.getMessage());
            
            BaseResponse<LoginResponseDto> baseResponse = new BaseResponse<LoginResponseDto>(
                false,
                null,
                e.getMessage(),
                HttpStatus.NOT_FOUND
            );
            
            return baseResponse.buildResponseEntity();
        } catch (Exception e) {
            logger.error("Unexpected error during login for CURP: {}", loginDto.getCurp(), e);
            
            BaseResponse<LoginResponseDto> baseResponse = new BaseResponse<LoginResponseDto>(
                false,
                null,
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR
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
            
            LoginResponseDto response = authService.loginWithQR(loginDto);
            
            BaseResponse<LoginResponseDto> baseResponse = new BaseResponse<LoginResponseDto>(
                true,
                response,
                "Login con QR exitoso",
                HttpStatus.OK
            );
            
            return baseResponse.buildResponseEntity();
        } catch (InvalidTokenException | UserNotFoundException e) {
            logger.warn("QR login failed - {}", e.getMessage());
            
            BaseResponse<LoginResponseDto> baseResponse = new BaseResponse<LoginResponseDto>(
                false,
                null,
                e.getMessage(),
                HttpStatus.UNAUTHORIZED
            );
            
            return baseResponse.buildResponseEntity();
        } catch (Exception e) {
            logger.error("Unexpected error during QR login", e);
            
            BaseResponse<LoginResponseDto> baseResponse = new BaseResponse<LoginResponseDto>(
                false,
                null,
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR
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
            
            ValidateTokenResponseDto response = authService.validateToken(validateDto.getToken());
            
            BaseResponse<ValidateTokenResponseDto> baseResponse = new BaseResponse<ValidateTokenResponseDto>(
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
