package com.icheha.aprendia_api.users.student.domain.repositories;

/**
 * Interfaz del repositorio para encriptación de datos
 */
public interface IEncryptDataRepository {
    
    /**
     * Encripta un texto
     * @param text Texto a encriptar
     * @return Texto encriptado
     */
    String encrypt(String text);
    
    /**
     * Desencripta un texto
     * @param encryptedText Texto encriptado
     * @return Texto desencriptado
     */
    String decrypt(String encryptedText);
}

