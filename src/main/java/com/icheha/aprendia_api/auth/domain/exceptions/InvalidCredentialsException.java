package com.icheha.aprendia_api.auth.domain.exceptions;

/**
 * Excepción lanzada cuando las credenciales proporcionadas son inválidas
 */
public class InvalidCredentialsException extends DomainException {
    
    public InvalidCredentialsException() {
        super("Credenciales inválidas");
    }
    
    public InvalidCredentialsException(String message) {
        super(message);
    }
    
    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}

