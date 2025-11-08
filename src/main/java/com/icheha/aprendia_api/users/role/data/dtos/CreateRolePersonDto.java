package com.icheha.aprendia_api.users.role.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para asignar un rol a una persona")
public class CreateRolePersonDto {
    
    @NotNull(message = "El ID de la persona es obligatorio")
    @Schema(description = "ID de la persona", example = "1")
    private Long personId;
    
    @NotNull(message = "El ID del rol es obligatorio")
    @Schema(description = "ID del rol", example = "1")
    private Long roleId;
}

