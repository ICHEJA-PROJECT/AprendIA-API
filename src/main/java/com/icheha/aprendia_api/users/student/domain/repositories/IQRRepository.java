package com.icheha.aprendia_api.users.student.domain.repositories;

/**
 * Interfaz del repositorio para generación de códigos QR
 */
public interface IQRRepository {
    
    /**
     * Genera un código QR a partir de un texto
     * @param text Texto a codificar en el QR
     * @return Imagen del QR en base64
     */
    String generateQR(String text);
}

