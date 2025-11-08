package com.icheha.aprendia_api.users.role.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para asignar múltiples roles a una persona")
public class CreateManyRolePersonDto {
    
    @NotNull(message = "El ID de la persona es obligatorio")
    @Schema(description = "ID de la persona", example = "1")
    private Long personId;
    
    @NotEmpty(message = "La lista de IDs de roles no puede estar vacía")
    @Schema(description = "Lista de IDs de roles", example = "[1, 2]")
    private List<Long> roleIds;
}

