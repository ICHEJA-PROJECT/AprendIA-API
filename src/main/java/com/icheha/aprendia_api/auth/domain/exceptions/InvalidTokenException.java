package com.icheha.aprendia_api.auth.domain.exceptions;

/**
 * Excepción lanzada cuando un token es inválido
 */
public class InvalidTokenException extends DomainException {
    
    public InvalidTokenException() {
        super("Token inválido");
    }
    
    public InvalidTokenException(String message) {
        super(message);
    }
    
    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public static InvalidTokenException expired() {
        return new InvalidTokenException("Token expirado");
    }
    
    public static InvalidTokenException malformed() {
        return new InvalidTokenException("Formato de token inválido");
    }
    
    public static InvalidTokenException qrToken() {
        return new InvalidTokenException("Token QR inválido");
    }
}

