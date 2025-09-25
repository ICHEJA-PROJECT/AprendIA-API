package com.icheha.aprendia_api.auth.domain.exceptions;

/**
 * Excepción base del dominio
 * Todas las excepciones específicas del dominio deben extender de esta clase
 */
public abstract class DomainException extends RuntimeException {
    
    public DomainException(String message) {
        super(message);
    }
    
    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}

