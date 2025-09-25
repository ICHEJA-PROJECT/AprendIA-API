package com.icheha.aprendia_api.core.controllers.advices;

import com.icheha.aprendia_api.auth.domain.exceptions.DomainException;
import com.icheha.aprendia_api.auth.domain.exceptions.InvalidCredentialsException;
import com.icheha.aprendia_api.auth.domain.exceptions.InvalidTokenException;
import com.icheha.aprendia_api.auth.domain.exceptions.UserNotFoundException;
import com.icheha.aprendia_api.auth.domain.exceptions.UserRoleNotFoundException;
import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejador global de excepciones para el módulo de autenticación
 * Centraliza el manejo de excepciones y proporciona respuestas consistentes
 */
@RestControllerAdvice(basePackages = "com.icheha.aprendia_api.auth.controllers")
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    /**
     * Maneja excepciones de credenciales inválidas
     */
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<BaseResponse<Void>> handleInvalidCredentials(InvalidCredentialsException e) {
        logger.warn("Invalid credentials: {}", e.getMessage());
        
        BaseResponse<Void> response = new BaseResponse<>(
            false,
            null,
            e.getMessage(),
            HttpStatus.UNAUTHORIZED
        );
        
        return response.buildResponseEntity();
    }
    
    /**
     * Maneja excepciones de usuario no encontrado
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<BaseResponse<Void>> handleUserNotFound(UserNotFoundException e) {
        logger.warn("User not found: {}", e.getMessage());
        
        BaseResponse<Void> response = new BaseResponse<>(
            false,
            null,
            e.getMessage(),
            HttpStatus.NOT_FOUND
        );
        
        return response.buildResponseEntity();
    }
    
    /**
     * Maneja excepciones de rol de usuario no encontrado
     */
    @ExceptionHandler(UserRoleNotFoundException.class)
    public ResponseEntity<BaseResponse<Void>> handleUserRoleNotFound(UserRoleNotFoundException e) {
        logger.warn("User role not found: {}", e.getMessage());
        
        BaseResponse<Void> response = new BaseResponse<>(
            false,
            null,
            e.getMessage(),
            HttpStatus.NOT_FOUND
        );
        
        return response.buildResponseEntity();
    }
    
    /**
     * Maneja excepciones de token inválido
     */
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<BaseResponse<Void>> handleInvalidToken(InvalidTokenException e) {
        logger.warn("Invalid token: {}", e.getMessage());
        
        BaseResponse<Void> response = new BaseResponse<>(
            false,
            null,
            e.getMessage(),
            HttpStatus.UNAUTHORIZED
        );
        
        return response.buildResponseEntity();
    }
    
    /**
     * Maneja excepciones generales del dominio
     */
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<BaseResponse<Void>> handleDomainException(DomainException e) {
        logger.error("Domain exception: {}", e.getMessage(), e);
        
        BaseResponse<Void> response = new BaseResponse<>(
            false,
            null,
            e.getMessage(),
            HttpStatus.BAD_REQUEST
        );
        
        return response.buildResponseEntity();
    }
    
    /**
     * Maneja excepciones no controladas
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleGenericException(Exception e) {
        logger.error("Unexpected error: {}", e.getMessage(), e);
        
        BaseResponse<Void> response = new BaseResponse<>(
            false,
            null,
            "Error interno del servidor",
            HttpStatus.INTERNAL_SERVER_ERROR
        );
        
        return response.buildResponseEntity();
    }
}

