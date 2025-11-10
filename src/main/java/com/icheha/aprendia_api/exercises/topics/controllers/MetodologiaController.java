package com.icheha.aprendia_api.exercises.topics.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateMetodologiaDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateMetodologiaDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.MetodologiaResponseDto;
import com.icheha.aprendia_api.exercises.topics.services.IMetodologiaService;
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
import java.util.Optional;

@RestController
@RequestMapping("/metodologias")
@Tag(name = "4.01. Metodologías", description = "API para gestión de metodologías educativas")
public class MetodologiaController {
    
    private final IMetodologiaService metodologiaService;
    
    @Autowired
    public MetodologiaController(IMetodologiaService metodologiaService) {
        this.metodologiaService = metodologiaService;
    }
    
    @PostMapping
    @Operation(
        summary = "Crear metodología",
        description = "Crea una nueva metodología educativa"
    )
    @ApiResponse(responseCode = "201", description = "Metodología creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<MetodologiaResponseDto>> create(
            @Valid @RequestBody CreateMetodologiaDto createMetodologiaDto) {
        MetodologiaResponseDto metodologia = metodologiaService.create(createMetodologiaDto);
        BaseResponse<MetodologiaResponseDto> response = new BaseResponse<>(
                true, metodologia, "Metodología creada exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener todas las metodologías",
        description = "Obtiene una lista de todas las metodologías del sistema"
    )
    @ApiResponse(responseCode = "200", description = "Metodologías obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<MetodologiaResponseDto>>> findAll() {
        List<MetodologiaResponseDto> metodologias = metodologiaService.findAll();
        BaseResponse<List<MetodologiaResponseDto>> response = new BaseResponse<>(
                true, metodologias, "Metodologías obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener metodología por ID",
        description = "Obtiene una metodología específica por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Metodología obtenida exitosamente")
    @ApiResponse(responseCode = "404", description = "Metodología no encontrada")
    public ResponseEntity<BaseResponse<MetodologiaResponseDto>> findById(
            @Parameter(description = "ID de la metodología", required = true)
            @PathVariable Long id) {
        Optional<MetodologiaResponseDto> metodologia = metodologiaService.findById(id);
        if (metodologia.isPresent()) {
            BaseResponse<MetodologiaResponseDto> response = new BaseResponse<>(
                    true, metodologia.get(), "Metodología obtenida exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } else {
            BaseResponse<MetodologiaResponseDto> response = new BaseResponse<>(
                    false, null, "Metodología no encontrada", HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @GetMapping("/search")
    @Operation(
        summary = "Buscar metodologías por nombre",
        description = "Busca metodologías que contengan el nombre especificado"
    )
    @ApiResponse(responseCode = "200", description = "Metodologías obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<MetodologiaResponseDto>>> findByNombreContaining(
            @Parameter(description = "Nombre a buscar", required = true)
            @RequestParam String nombre) {
        List<MetodologiaResponseDto> metodologias = metodologiaService.findByNombreContaining(nombre);
        BaseResponse<List<MetodologiaResponseDto>> response = new BaseResponse<>(
                true, metodologias, "Metodologías obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar metodología",
        description = "Actualiza una metodología existente"
    )
    @ApiResponse(responseCode = "200", description = "Metodología actualizada exitosamente")
    @ApiResponse(responseCode = "404", description = "Metodología no encontrada")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<MetodologiaResponseDto>> update(
            @Parameter(description = "ID de la metodología", required = true)
            @PathVariable Long id,
            @Valid @RequestBody UpdateMetodologiaDto updateMetodologiaDto) {
        try {
            MetodologiaResponseDto metodologia = metodologiaService.update(id, updateMetodologiaDto);
            BaseResponse<MetodologiaResponseDto> response = new BaseResponse<>(
                    true, metodologia, "Metodología actualizada exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<MetodologiaResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar metodología",
        description = "Elimina una metodología del sistema"
    )
    @ApiResponse(responseCode = "200", description = "Metodología eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Metodología no encontrada")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID de la metodología", required = true)
            @PathVariable Long id) {
        try {
            metodologiaService.delete(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Metodología eliminada exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
}

