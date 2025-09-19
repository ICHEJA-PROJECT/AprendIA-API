package com.icheha.aprendia_api.preferences.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/preferences")
@Tag(name = "Preferences", description = "Endpoints de gestión de preferencias")
public class PreferencesController {
    
    @GetMapping("/student-impairments/students/{studentId}/details")
    @Operation(
        summary = "Obtener preferencias del estudiante",
        description = "Obtener las discapacidades y ruta de aprendizaje del estudiante"
    )
    @ApiResponse(responseCode = "200", description = "Preferencias del estudiante obtenidas")
    @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    public ResponseEntity<Map<String, Object>> getStudentPreferencesWithDetails(@PathVariable Long studentId) {
        // Implementar lógica de preferencias del estudiante
        Map<String, Object> response = Map.of(
            "studentId", studentId,
            "impairments", "[]",
            "learningPath", "[]",
            "message", "Preferencias del estudiante obtenidas"
        );
        return ResponseEntity.ok(response);
    }
}
