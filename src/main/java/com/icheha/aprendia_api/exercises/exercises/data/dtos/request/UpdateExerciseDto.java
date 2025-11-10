package com.icheha.aprendia_api.exercises.exercises.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar un ejercicio")
public class UpdateExerciseDto {

    @Schema(description = "Contexto del ejercicio en formato JSON (opcional)", example = "{\"data\": \"...\"}")
    private Map<String, Object> context;

    @Schema(description = "ID del template/reactivo (opcional)", example = "1")
    private Long templateId;
}

