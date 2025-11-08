package com.icheha.aprendia_api.preferences.impairments.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.CreateResourceImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.ResourceImpairmentResponseDto;
import com.icheha.aprendia_api.preferences.impairments.services.IResourceImpairmentService;
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
@RequestMapping("/resource-impairments")
@Tag(name = "3.04. Resource Impairment Management", description = "Endpoints para gestión de relaciones recurso-discapacidad")
public class ResourceImpairmentController {

    @Autowired
    private IResourceImpairmentService resourceImpairmentService;

    @PostMapping
    @Operation(summary = "Crear relación recurso-discapacidad", description = "Crea una nueva relación recurso-discapacidad")
    @ApiResponse(responseCode = "201", description = "Relación creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos o relación ya existe")
    public ResponseEntity<BaseResponse<ResourceImpairmentResponseDto>> create(@Valid @RequestBody CreateResourceImpairmentDto createDto) {
        try {
            ResourceImpairmentResponseDto response = resourceImpairmentService.create(createDto);
            return new BaseResponse<>(true, response, "Relación creada exitosamente", HttpStatus.CREATED).buildResponseEntity();
        } catch (IllegalArgumentException e) {
            return new BaseResponse<ResourceImpairmentResponseDto>(false, null, e.getMessage(), HttpStatus.BAD_REQUEST).buildResponseEntity();
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todas las relaciones recurso-discapacidad", description = "Retorna una lista de todas las relaciones")
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<ResourceImpairmentResponseDto>>> findAll() {
        List<ResourceImpairmentResponseDto> relations = resourceImpairmentService.findAll();
        return new BaseResponse<>(true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK).buildResponseEntity();
    }

    @GetMapping("/resource/{resourceId}")
    @Operation(summary = "Obtener relaciones por recurso", description = "Retorna las relaciones de un recurso específico")
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<ResourceImpairmentResponseDto>>> findByResource(
            @Parameter(description = "ID del recurso") @PathVariable Long resourceId) {
        List<ResourceImpairmentResponseDto> relations = resourceImpairmentService.findByResource(resourceId);
        return new BaseResponse<>(true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK).buildResponseEntity();
    }

    @GetMapping("/impairment/{impairmentId}")
    @Operation(summary = "Obtener relaciones por discapacidad", description = "Retorna las relaciones de una discapacidad específica")
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<ResourceImpairmentResponseDto>>> findByImpairment(
            @Parameter(description = "ID de la discapacidad") @PathVariable Long impairmentId) {
        List<ResourceImpairmentResponseDto> relations = resourceImpairmentService.findByImpairment(impairmentId);
        return new BaseResponse<>(true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK).buildResponseEntity();
    }

    @GetMapping("/learning-path/{learningPathId}")
    @Operation(summary = "Obtener relaciones por ruta de aprendizaje", description = "Retorna las relaciones de una ruta de aprendizaje específica")
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<ResourceImpairmentResponseDto>>> findByLearningPath(
            @Parameter(description = "ID de la ruta de aprendizaje") @PathVariable Long learningPathId) {
        List<ResourceImpairmentResponseDto> relations = resourceImpairmentService.findByLearningPath(learningPathId);
        return new BaseResponse<>(true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK).buildResponseEntity();
    }
}

