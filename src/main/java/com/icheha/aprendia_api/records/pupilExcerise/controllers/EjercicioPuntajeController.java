package com.icheha.aprendia_api.records.pupilExcerise.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreateEjercicioPuntajeDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.UpdateEjercicioPuntajeDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.EjercicioPuntajeResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.services.IEjercicioPuntajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ejercicio-puntaje")
@Tag(name = "5.4. Ejercicio Puntaje", description = "API para gestión de puntajes de ejercicios")
public class EjercicioPuntajeController {
    
    private final IEjercicioPuntajeService ejercicioPuntajeService;
    
    @Autowired
    public EjercicioPuntajeController(IEjercicioPuntajeService ejercicioPuntajeService) {
        this.ejercicioPuntajeService = ejercicioPuntajeService;
    }
    
    @PostMapping
    @Operation(summary = "Crear ejercicio puntaje", description = "Crear un nuevo registro de puntaje de ejercicio")
    @ApiResponse(responseCode = "201", description = "Ejercicio puntaje creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<EjercicioPuntajeResponseDto>> create(
            @Valid @RequestBody CreateEjercicioPuntajeDto createDto) {
        EjercicioPuntajeResponseDto ejercicioPuntaje = ejercicioPuntajeService.create(createDto);
        BaseResponse<EjercicioPuntajeResponseDto> response = new BaseResponse<>(
                true, ejercicioPuntaje, "Ejercicio puntaje creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }
    
    @GetMapping
    @Operation(summary = "Obtener todos los ejercicio puntajes", description = "Obtener todos los registros de puntajes de ejercicios")
    @ApiResponse(responseCode = "200", description = "Ejercicio puntajes obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<EjercicioPuntajeResponseDto>>> findAll() {
        List<EjercicioPuntajeResponseDto> ejercicioPuntajes = ejercicioPuntajeService.findAll();
        BaseResponse<List<EjercicioPuntajeResponseDto>> response = new BaseResponse<>(
                true, ejercicioPuntajes, "Ejercicio puntajes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener ejercicio puntaje por ID", description = "Obtener un registro de puntaje de ejercicio específico por su ID")
    @ApiResponse(responseCode = "200", description = "Ejercicio puntaje obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Ejercicio puntaje no encontrado")
    public ResponseEntity<BaseResponse<EjercicioPuntajeResponseDto>> findById(
            @Parameter(description = "ID del ejercicio puntaje") @PathVariable Long id) {
        return ejercicioPuntajeService.findById(id)
                .map(ejercicioPuntaje -> {
                    BaseResponse<EjercicioPuntajeResponseDto> response = new BaseResponse<>(
                            true, ejercicioPuntaje, "Ejercicio puntaje obtenido exitosamente", HttpStatus.OK);
                    return response.buildResponseEntity();
                })
                .orElseGet(() -> {
                    BaseResponse<EjercicioPuntajeResponseDto> response = new BaseResponse<>(
                            false, null, "Ejercicio puntaje no encontrado", HttpStatus.NOT_FOUND);
                    return response.buildResponseEntity();
                });
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar ejercicio puntaje", description = "Actualizar un registro de puntaje de ejercicio existente")
    @ApiResponse(responseCode = "200", description = "Ejercicio puntaje actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "Ejercicio puntaje no encontrado")
    public ResponseEntity<BaseResponse<EjercicioPuntajeResponseDto>> update(
            @Parameter(description = "ID del ejercicio puntaje") @PathVariable Long id,
            @Valid @RequestBody UpdateEjercicioPuntajeDto updateDto) {
        EjercicioPuntajeResponseDto updated = ejercicioPuntajeService.update(id, updateDto);
        BaseResponse<EjercicioPuntajeResponseDto> response = new BaseResponse<>(
                true, updated, "Ejercicio puntaje actualizado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar ejercicio puntaje", description = "Eliminar un registro de puntaje de ejercicio del sistema")
    @ApiResponse(responseCode = "200", description = "Ejercicio puntaje eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Ejercicio puntaje no encontrado")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del ejercicio puntaje") @PathVariable Long id) {
        ejercicioPuntajeService.delete(id);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Ejercicio puntaje eliminado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/ejercicio/{idEjercicio}")
    @Operation(summary = "Obtener ejercicio puntajes por ejercicio", description = "Obtener todos los registros de puntajes para un ejercicio específico")
    @ApiResponse(responseCode = "200", description = "Ejercicio puntajes obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<EjercicioPuntajeResponseDto>>> findByEjercicioId(
            @Parameter(description = "ID del ejercicio") @PathVariable Long idEjercicio) {
        List<EjercicioPuntajeResponseDto> ejercicioPuntajes = ejercicioPuntajeService.findByEjercicioId(idEjercicio);
        BaseResponse<List<EjercicioPuntajeResponseDto>> response = new BaseResponse<>(
                true, ejercicioPuntajes, "Ejercicio puntajes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/persona/{idPersona}")
    @Operation(summary = "Obtener ejercicio puntajes por persona", description = "Obtener todos los registros de puntajes de ejercicios para una persona específica")
    @ApiResponse(responseCode = "200", description = "Ejercicio puntajes obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<EjercicioPuntajeResponseDto>>> findByPersonaId(
            @Parameter(description = "ID de la persona") @PathVariable Long idPersona) {
        List<EjercicioPuntajeResponseDto> ejercicioPuntajes = ejercicioPuntajeService.findByPersonaId(idPersona);
        BaseResponse<List<EjercicioPuntajeResponseDto>> response = new BaseResponse<>(
                true, ejercicioPuntajes, "Ejercicio puntajes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

