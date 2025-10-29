package com.icheha.aprendia_api.preferences.occupation.data.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO de respuesta para Occupation
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información de una ocupación")
public class OccupationResponseDto {
    
    @Schema(description = "ID de la ocupación", example = "1")
    private Long id;
    
    @Schema(description = "Nombre de la ocupación", example = "Ingeniero de Software")
    private String name;
    
    @Schema(description = "Número de estudiantes asociados", example = "5")
    private Integer studentCount;
    
    
    @Schema(description = "Número de ejercicios asociados", example = "8")
    private Integer exerciseCount;
    
    @Schema(description = "Lista de IDs de estudiantes asociados")
    private List<Long> studentIds;
    
    
    @Schema(description = "Lista de IDs de ejercicios asociados")
    private List<Long> exerciseIds;
}
