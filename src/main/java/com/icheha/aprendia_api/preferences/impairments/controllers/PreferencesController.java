package com.icheha.aprendia_api.preferences.impairments.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/preferences")
@Tag(name = "Preferences", description = "Endpoints de gestión de preferencias")
public class PreferencesController {
    
    @GetMapping("/student-impairments/students/{studentId}/details")
    @Operation(
        summary = "Obtener preferencias del estudiante",
        description = "Obtener las discapacidades y ruta de aprendizaje del estudiante"
    )
    @ApiResponse(responseCode = "200", description = "Preferencias del estudiante obtenidas")
    @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    public ResponseEntity<BaseResponse<Map<String, Object>>> getStudentPreferencesWithDetails(
            @Parameter(description = "ID del estudiante") @PathVariable Long studentId) {
        try {
            // Implementar lógica de preferencias del estudiante
            Map<String, Object> preferences = Map.of(
                "studentId", studentId,
                "impairments", "[]",
                "learningPath", "[]"
            );
            
            BaseResponse<Map<String, Object>> response = new BaseResponse<>(
                    true, preferences, "Preferencias del estudiante obtenidas exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (Exception e) {
            BaseResponse<Map<String, Object>> response = new BaseResponse<>(
                    false, null, "Error al obtener preferencias del estudiante: " + e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
}
