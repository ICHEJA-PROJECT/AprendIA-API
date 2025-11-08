package com.icheha.aprendia_api.users.person.domain.repositories;

/**
 * Interfaz del repositorio para hash de datos
 * Define las operaciones de hash para contraseñas
 */
public interface IHashDataRepository {
    
    /**
     * Genera un hash de un texto (usualmente contraseña)
     * @param text Texto a hashear
     * @return Texto hasheado
     */
    String hash(String text);
}

