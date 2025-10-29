package com.icheha.aprendia_api.auth.domain.exceptions;

/**
 * Excepción lanzada cuando no se encuentra un rol para un usuario
 */
public class UserRoleNotFoundException extends DomainException {
    
    public UserRoleNotFoundException() {
        super("Rol de usuario no encontrado");
    }
    
    public UserRoleNotFoundException(String message) {
        super(message);
    }
    
    public UserRoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public static UserRoleNotFoundException forUser(Long userId) {
        return new UserRoleNotFoundException("No se encontró rol para el usuario con ID " + userId);
    }
}

