package com.icheha.aprendia_api.users.person.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interfaz del servicio de subida de imágenes
 */
public interface IImageUploadService {
    
    /**
     * Sube una imagen a Cloudinary
     * @param fileBytes Bytes de la imagen
     * @param fileName Nombre del archivo
     * @param folder Carpeta destino
     * @return URL de la imagen subida
     */
    String uploadImage(byte[] fileBytes, String fileName, String folder);
}

