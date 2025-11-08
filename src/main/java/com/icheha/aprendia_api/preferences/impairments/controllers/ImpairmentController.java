package com.icheha.aprendia_api.preferences.impairments.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.CreateImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.ImpairmentResponseDto;
import com.icheha.aprendia_api.preferences.impairments.services.IImpairmentService;
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
@RequestMapping("/impairments")
@Tag(name = "3.01. Impairments", description = "Endpoints de gestión de discapacidades")
public class ImpairmentController {
    
    @Autowired
    private IImpairmentService impairmentService;
    
    @PostMapping
    @Operation(
        summary = "Crear discapacidad",
        description = "Crear una nueva discapacidad en el sistema"
    )
    @ApiResponse(responseCode = "201", description = "Discapacidad creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<ImpairmentResponseDto>> create(
            @Valid @RequestBody CreateImpairmentDto createDto) {
        ImpairmentResponseDto response = impairmentService.create(createDto);
        BaseResponse<ImpairmentResponseDto> baseResponse = new BaseResponse<>(
                true, response, "Discapacidad creada exitosamente", HttpStatus.CREATED);
        return baseResponse.buildResponseEntity();
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener todas las discapacidades",
        description = "Retorna una lista de todas las discapacidades disponibles"
    )
    @ApiResponse(responseCode = "200", description = "Discapacidades obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<ImpairmentResponseDto>>> findAll() {
        List<ImpairmentResponseDto> impairments = impairmentService.findAll();
        BaseResponse<List<ImpairmentResponseDto>> response = new BaseResponse<>(
                true, impairments, "Discapacidades obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener discapacidad por ID",
        description = "Obtiene una discapacidad específica por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Discapacidad encontrada")
    @ApiResponse(responseCode = "404", description = "Discapacidad no encontrada")
    public ResponseEntity<BaseResponse<ImpairmentResponseDto>> findById(
            @Parameter(description = "ID de la discapacidad") @PathVariable Long id) {
        try {
            ImpairmentResponseDto impairment = impairmentService.findById(id);
            BaseResponse<ImpairmentResponseDto> response = new BaseResponse<>(
                    true, impairment, "Discapacidad obtenida exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<ImpairmentResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar discapacidad",
        description = "Actualiza una discapacidad existente"
    )
    @ApiResponse(responseCode = "200", description = "Discapacidad actualizada exitosamente")
    @ApiResponse(responseCode = "404", description = "Discapacidad no encontrada")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<ImpairmentResponseDto>> update(
            @Parameter(description = "ID de la discapacidad") @PathVariable Long id,
            @Valid @RequestBody com.icheha.aprendia_api.preferences.impairments.data.dtos.request.UpdateImpairmentDto updateDto) {
        try {
            ImpairmentResponseDto response = impairmentService.update(id, updateDto);
            BaseResponse<ImpairmentResponseDto> baseResponse = new BaseResponse<>(
                    true, response, "Discapacidad actualizada exitosamente", HttpStatus.OK);
            return baseResponse.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<ImpairmentResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar discapacidad",
        description = "Elimina una discapacidad por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Discapacidad eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Discapacidad no encontrada")
    public ResponseEntity<BaseResponse<Void>> deleteById(
            @Parameter(description = "ID de la discapacidad") @PathVariable Long id) {
        try {
            impairmentService.deleteById(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Discapacidad eliminada exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
}

