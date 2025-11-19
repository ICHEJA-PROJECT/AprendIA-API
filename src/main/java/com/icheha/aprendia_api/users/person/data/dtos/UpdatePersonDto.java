package com.icheha.aprendia_api.users.person.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar una persona")
public class UpdatePersonDto {
    
    @Schema(description = "Primer nombre de la persona", example = "Fernando")
    private String firstName;
    
    @Schema(description = "Segundo nombre de la persona", example = "Emiliano")
    private String middleName;
    
    @Schema(description = "Apellido paterno", example = "Flores")
    private String paternalSurname;
    
    @Schema(description = "Apellido materno", example = "De la Riva")
    private String maternalSurname;
    
    @Schema(description = "CURP de la persona", example = "FORF040807HCSLVRA8")
    private String curp;
    
    @Schema(description = "Número de INE", example = "2364956377")
    private String ineNumber;
    
    @Schema(description = "Fecha de nacimiento", example = "2004-08-07")
    private String birthdate;
    
    @Schema(description = "Género (M/F)", example = "M")
    private String gender;
    
    @Schema(description = "Nombre de la vialidad", example = "Versalles")
    private String roadName;
    
    @Schema(description = "ID del tipo de vialidad", example = "1")
    private Long roadTypeId;
    
    @Schema(description = "ID del asentamiento", example = "416")
    private Long settlementId;
    
    @Schema(description = "Email de la persona", example = "fernando.flores@example.com")
    private String email;
    
    @Schema(description = "Teléfono de la persona", example = "5551234567")
    private String telefono;
    
    @Schema(description = "Contraseña de la persona", example = "12345678")
    private String password;
    
    @Schema(description = "Imagen de perfil en base64")
    private String profileImagePath;
}

