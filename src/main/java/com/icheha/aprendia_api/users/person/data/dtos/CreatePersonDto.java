package com.icheha.aprendia_api.users.person.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear una nueva persona")
public class CreatePersonDto {
    
    @NotBlank(message = "El primer nombre es obligatorio")
    @Schema(description = "Primer nombre de la persona", example = "Fernando")
    private String firstName;
    
    @NotBlank(message = "El segundo nombre es obligatorio")
    @Schema(description = "Segundo nombre de la persona", example = "Emiliano")
    private String middleName;
    
    @NotBlank(message = "El apellido paterno es obligatorio")
    @Schema(description = "Apellido paterno", example = "Flores")
    private String paternalSurname;
    
    @NotBlank(message = "El apellido materno es obligatorio")
    @Schema(description = "Apellido materno", example = "De la Riva")
    private String maternalSurname;
    
    @NotBlank(message = "El CURP es obligatorio")
    @Schema(description = "CURP de la persona", example = "FORF040807HCSLVRA8")
    private String curp;
    
    @NotBlank(message = "El número de INE es obligatorio")
    @Schema(description = "Número de INE", example = "2364956377")
    private String ineNumber;
    
    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    @Schema(description = "Fecha de nacimiento", example = "2004-08-07")
    private String birthdate;
    
    @NotBlank(message = "El género es obligatorio")
    @Schema(description = "Género (M/F)", example = "M")
    private String gender;
    
    @NotBlank(message = "El nombre de la vialidad es obligatorio")
    @Schema(description = "Nombre de la vialidad", example = "Versalles")
    private String roadName;
    
    @NotNull(message = "El ID del tipo de vialidad es obligatorio")
    @Schema(description = "ID del tipo de vialidad", example = "1")
    private Long roadTypeId;
    
    @NotNull(message = "El ID del asentamiento es obligatorio")
    @Schema(description = "ID del asentamiento", example = "416")
    private Long settlementId;
    
    @Schema(description = "Contraseña de la persona (opcional, si se proporciona se creará un usuario automáticamente)", example = "12345678")
    private String password;
    
    @NotBlank(message = "La imagen de perfil es obligatoria")
    @Schema(description = "Imagen de perfil en base64")
    private String profileImagePath;
}

