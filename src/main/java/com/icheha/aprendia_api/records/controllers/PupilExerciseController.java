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
@RequestMapping("/pupil-exercises")
@Tag(name = "Pupil Exercises", description = "Endpoints de gestión de ejercicios de alumnos")
public class PupilExerciseController {
    
    @PostMapping
    @Operation(
        summary = "Crear registro de ejercicio de alumno",
        description = "Registrar la realización de un ejercicio por un alumno"
    )
    @ApiResponse(responseCode = "201", description = "Registro creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<Map<String, Object>> createPupilExercise(@RequestBody Map<String, Object> createDto) {
        Map<String, Object> response = Map.of(
            "id", 1L,
            "message", "Registro de ejercicio creado exitosamente"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener registros de ejercicios de alumnos",
        description = "Obtener todos los registros de ejercicios realizados por alumnos"
    )
    @ApiResponse(responseCode = "200", description = "Registros obtenidos exitosamente")
    public ResponseEntity<List<Map<String, Object>>> getAllPupilExercises() {
        List<Map<String, Object>> response = List.of(
            Map.of("id", 1L, "pupilId", 1L, "exerciseId", 1L, "score", 85.5),
            Map.of("id", 2L, "pupilId", 2L, "exerciseId", 2L, "score", 92.0)
        );
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener registro de ejercicio por ID",
        description = "Obtener un registro específico de ejercicio de alumno"
    )
    @ApiResponse(responseCode = "200", description = "Registro encontrado")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    public ResponseEntity<Map<String, Object>> getPupilExerciseById(@PathVariable Long id) {
        Map<String, Object> response = Map.of(
            "id", id,
            "pupilId", 1L,
            "exerciseId", 1L,
            "score", 85.5,
            "completed", true
        );
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar registro de ejercicio",
        description = "Actualizar un registro existente de ejercicio de alumno"
    )
    @ApiResponse(responseCode = "200", description = "Registro actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    public ResponseEntity<Map<String, Object>> updatePupilExercise(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updateDto) {
        Map<String, Object> response = Map.of(
            "id", id,
            "message", "Registro de ejercicio actualizado exitosamente"
        );
        return ResponseEntity.ok(response);
    }
}
