package com.icheha.aprendia_api.auth.domain.services;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.valueobjects.Curp;

/**
 * Interface del servicio de dominio de autenticación
 * Contiene la lógica de negocio pura relacionada con autenticación
 */
public interface IAuthDomainService {
    
    /**
     * Autentica un usuario usando credenciales
     * @param curp CURP del usuario
     * @param password Contraseña del usuario
     * @return Persona autenticada
     * @throws com.icheha.aprendia_api.auth.domain.exceptions.InvalidCredentialsException si las credenciales son inválidas
     * @throws com.icheha.aprendia_api.auth.domain.exceptions.UserNotFoundException si el usuario no existe
     */
    Persona authenticateUser(Curp curp, String password);
    
    /**
     * Busca un usuario por CURP
     * @param curp CURP del usuario
     * @return Persona encontrada
     * @throws com.icheha.aprendia_api.auth.domain.exceptions.UserNotFoundException si el usuario no existe
     */
    Persona findUserByCurp(Curp curp);
    
    /**
     * Busca un usuario por ID
     * @param userId ID del usuario
     * @return Persona encontrada
     * @throws com.icheha.aprendia_api.auth.domain.exceptions.UserNotFoundException si el usuario no existe
     */
    Persona findUserById(Long userId);
    
    /**
     * Verifica si un usuario existe por CURP
     * @param curp CURP del usuario
     * @return true si existe, false en caso contrario
     */
    boolean userExists(Curp curp);
}

