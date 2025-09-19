package com.icheha.aprendia_api.records.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pupil-topics")
@Tag(name = "Pupil Topics", description = "Endpoints de gestión de temas de alumnos")
public class PupilTopicController {
    
    @PostMapping
    @Operation(
        summary = "Crear registro de tema de alumno",
        description = "Registrar el progreso de un tema de un alumno"
    )
    @ApiResponse(responseCode = "201", description = "Registro creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<Map<String, Object>> createPupilTopic(@RequestBody Map<String, Object> createDto) {
        Map<String, Object> response = Map.of(
            "id", 1L,
            "message", "Registro de tema creado exitosamente"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener registros de temas de alumnos",
        description = "Obtener todos los registros de temas de alumnos"
    )
    @ApiResponse(responseCode = "200", description = "Registros obtenidos exitosamente")
    public ResponseEntity<List<Map<String, Object>>> getAllPupilTopics() {
        List<Map<String, Object>> response = List.of(
            Map.of("id", 1L, "pupilId", 1L, "topicId", 1L, "completed", true),
            Map.of("id", 2L, "pupilId", 2L, "topicId", 2L, "completed", false)
        );
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener registro de tema por ID",
        description = "Obtener un registro específico de tema de alumno"
    )
    @ApiResponse(responseCode = "200", description = "Registro encontrado")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    public ResponseEntity<Map<String, Object>> getPupilTopicById(@PathVariable Long id) {
        Map<String, Object> response = Map.of(
            "id", id,
            "pupilId", 1L,
            "topicId", 1L,
            "completed", true,
            "progress", 100.0
        );
        return ResponseEntity.ok(response);
    }
}
