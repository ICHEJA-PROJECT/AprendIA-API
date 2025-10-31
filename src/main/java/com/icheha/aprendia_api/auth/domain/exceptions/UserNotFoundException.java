package com.icheha.aprendia_api.auth.domain.exceptions;

/**
 * Excepción lanzada cuando no se encuentra un usuario
 */
public class UserNotFoundException extends DomainException {
    
    public UserNotFoundException() {
        super("Usuario no encontrado");
    }
    
    public UserNotFoundException(String message) {
        super(message);
    }
    
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public static UserNotFoundException byCurp(String curp) {
        return new UserNotFoundException("Usuario con CURP " + curp + " no encontrado");
    }
    
    public static UserNotFoundException byId(Long id) {
        return new UserNotFoundException("Usuario con ID " + id + " no encontrado");
    }
}

