package com.icheha.aprendia_api.users.student.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta al registrar un estudiante")
public class RegisterStudentResponseDto {
    
    @Schema(description = "URL de la imagen del código QR", example = "https://cloudinary.com/qr-image.png")
    private String qrImage;
    
    @Schema(description = "Token encriptado para el QR", example = "a1b2c3d4e5f6...")
    private String encryptedToken;
}

