package com.icheha.aprendia_api.records.pupilExcerise.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pupil-skills")
@Tag(name = "Pupil Skills", description = "Endpoints de gestión de habilidades de alumnos")
public class PupilSkillController {
    
    @PostMapping
    @Operation(
        summary = "Crear registro de habilidad de alumno",
        description = "Registrar el progreso de una habilidad de un alumno"
    )
    @ApiResponse(responseCode = "201", description = "Registro creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<Map<String, Object>> createPupilSkill(@RequestBody Map<String, Object> createDto) {
        Map<String, Object> response = Map.of(
            "id", 1L,
            "message", "Registro de habilidad creado exitosamente"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener registros de habilidades de alumnos",
        description = "Obtener todos los registros de habilidades de alumnos"
    )
    @ApiResponse(responseCode = "200", description = "Registros obtenidos exitosamente")
    public ResponseEntity<List<Map<String, Object>>> getAllPupilSkills() {
        List<Map<String, Object>> response = List.of(
            Map.of("id", 1L, "pupilId", 1L, "skillId", 1L, "score", 85.5, "percentage", 85.5),
            Map.of("id", 2L, "pupilId", 2L, "skillId", 2L, "score", 92.0, "percentage", 92.0)
        );
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener registro de habilidad por ID",
        description = "Obtener un registro específico de habilidad de alumno"
    )
    @ApiResponse(responseCode = "200", description = "Registro encontrado")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    public ResponseEntity<Map<String, Object>> getPupilSkillById(@PathVariable Long id) {
        Map<String, Object> response = Map.of(
            "id", id,
            "pupilId", 1L,
            "skillId", 1L,
            "score", 85.5,
            "percentage", 85.5,
            "level", 3
        );
        return ResponseEntity.ok(response);
    }
}
