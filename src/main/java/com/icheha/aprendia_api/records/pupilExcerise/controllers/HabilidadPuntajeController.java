package com.icheha.aprendia_api.records.pupilExcerise.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreateHabilidadPuntajeDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.UpdateHabilidadPuntajeDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.HabilidadPuntajeResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.services.IHabilidadPuntajeService;
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
@RequestMapping("/habilidad-puntaje")
@Tag(name = "5.3. Habilidad Puntaje", description = "API para gestión de puntajes de habilidades")
public class HabilidadPuntajeController {
    
    private final IHabilidadPuntajeService habilidadPuntajeService;
    
    @Autowired
    public HabilidadPuntajeController(IHabilidadPuntajeService habilidadPuntajeService) {
        this.habilidadPuntajeService = habilidadPuntajeService;
    }
    
    @PostMapping
    @Operation(summary = "Crear habilidad puntaje", description = "Crear un nuevo registro de puntaje de habilidad")
    @ApiResponse(responseCode = "201", description = "Habilidad puntaje creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<HabilidadPuntajeResponseDto>> create(
            @Valid @RequestBody CreateHabilidadPuntajeDto createDto) {
        HabilidadPuntajeResponseDto habilidadPuntaje = habilidadPuntajeService.create(createDto);
        BaseResponse<HabilidadPuntajeResponseDto> response = new BaseResponse<>(
                true, habilidadPuntaje, "Habilidad puntaje creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }
    
    @GetMapping
    @Operation(summary = "Obtener todos los habilidad puntajes", description = "Obtener todos los registros de puntajes de habilidades")
    @ApiResponse(responseCode = "200", description = "Habilidad puntajes obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<HabilidadPuntajeResponseDto>>> findAll() {
        List<HabilidadPuntajeResponseDto> habilidadPuntajes = habilidadPuntajeService.findAll();
        BaseResponse<List<HabilidadPuntajeResponseDto>> response = new BaseResponse<>(
                true, habilidadPuntajes, "Habilidad puntajes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener habilidad puntaje por ID", description = "Obtener un registro de puntaje de habilidad específico por su ID")
    @ApiResponse(responseCode = "200", description = "Habilidad puntaje obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Habilidad puntaje no encontrado")
    public ResponseEntity<BaseResponse<HabilidadPuntajeResponseDto>> findById(
            @Parameter(description = "ID del habilidad puntaje") @PathVariable Long id) {
        return habilidadPuntajeService.findById(id)
                .map(habilidadPuntaje -> {
                    BaseResponse<HabilidadPuntajeResponseDto> response = new BaseResponse<>(
                            true, habilidadPuntaje, "Habilidad puntaje obtenido exitosamente", HttpStatus.OK);
                    return response.buildResponseEntity();
                })
                .orElseGet(() -> {
                    BaseResponse<HabilidadPuntajeResponseDto> response = new BaseResponse<>(
                            false, null, "Habilidad puntaje no encontrado", HttpStatus.NOT_FOUND);
                    return response.buildResponseEntity();
                });
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar habilidad puntaje", description = "Actualizar un registro de puntaje de habilidad existente")
    @ApiResponse(responseCode = "200", description = "Habilidad puntaje actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "Habilidad puntaje no encontrado")
    public ResponseEntity<BaseResponse<HabilidadPuntajeResponseDto>> update(
            @Parameter(description = "ID del habilidad puntaje") @PathVariable Long id,
            @Valid @RequestBody UpdateHabilidadPuntajeDto updateDto) {
        HabilidadPuntajeResponseDto updated = habilidadPuntajeService.update(id, updateDto);
        BaseResponse<HabilidadPuntajeResponseDto> response = new BaseResponse<>(
                true, updated, "Habilidad puntaje actualizado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar habilidad puntaje", description = "Eliminar un registro de puntaje de habilidad del sistema")
    @ApiResponse(responseCode = "200", description = "Habilidad puntaje eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Habilidad puntaje no encontrado")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del habilidad puntaje") @PathVariable Long id) {
        habilidadPuntajeService.delete(id);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Habilidad puntaje eliminado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/ejercicio/{idEjercicio}")
    @Operation(summary = "Obtener habilidad puntajes por ejercicio", description = "Obtener todos los registros de puntajes de habilidades para un ejercicio específico")
    @ApiResponse(responseCode = "200", description = "Habilidad puntajes obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<HabilidadPuntajeResponseDto>>> findByEjercicioId(
            @Parameter(description = "ID del ejercicio") @PathVariable Long idEjercicio) {
        List<HabilidadPuntajeResponseDto> habilidadPuntajes = habilidadPuntajeService.findByEjercicioId(idEjercicio);
        BaseResponse<List<HabilidadPuntajeResponseDto>> response = new BaseResponse<>(
                true, habilidadPuntajes, "Habilidad puntajes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/habilidad/{idHabilidad}")
    @Operation(summary = "Obtener habilidad puntajes por habilidad", description = "Obtener todos los registros de puntajes para una habilidad específica")
    @ApiResponse(responseCode = "200", description = "Habilidad puntajes obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<HabilidadPuntajeResponseDto>>> findByHabilidadId(
            @Parameter(description = "ID de la habilidad") @PathVariable Long idHabilidad) {
        List<HabilidadPuntajeResponseDto> habilidadPuntajes = habilidadPuntajeService.findByHabilidadId(idHabilidad);
        BaseResponse<List<HabilidadPuntajeResponseDto>> response = new BaseResponse<>(
                true, habilidadPuntajes, "Habilidad puntajes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/persona/{idPersona}")
    @Operation(summary = "Obtener habilidad puntajes por persona", description = "Obtener todos los registros de puntajes de habilidades para una persona específica")
    @ApiResponse(responseCode = "200", description = "Habilidad puntajes obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<HabilidadPuntajeResponseDto>>> findByPersonaId(
            @Parameter(description = "ID de la persona") @PathVariable Long idPersona) {
        List<HabilidadPuntajeResponseDto> habilidadPuntajes = habilidadPuntajeService.findByPersonaId(idPersona);
        BaseResponse<List<HabilidadPuntajeResponseDto>> response = new BaseResponse<>(
                true, habilidadPuntajes, "Habilidad puntajes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

