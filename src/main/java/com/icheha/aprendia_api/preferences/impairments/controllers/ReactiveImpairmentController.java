package com.icheha.aprendia_api.preferences.impairments.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.CreateReactiveImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.ReactiveImpairmentResponseDto;
import com.icheha.aprendia_api.preferences.impairments.services.IReactiveImpairmentService;
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
@RequestMapping("/reactive-impairments")
@Tag(name = "3.05. Reactive Impairment Management", description = "Endpoints para gestión de relaciones reactivo-discapacidad")
public class ReactiveImpairmentController {

    @Autowired
    private IReactiveImpairmentService reactiveImpairmentService;

    @PostMapping
    @Operation(summary = "Crear relación reactivo-discapacidad", description = "Crea una nueva relación reactivo-discapacidad")
    @ApiResponse(responseCode = "201", description = "Relación creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos o relación ya existe")
    public ResponseEntity<BaseResponse<ReactiveImpairmentResponseDto>> create(@Valid @RequestBody CreateReactiveImpairmentDto createDto) {
        try {
            ReactiveImpairmentResponseDto response = reactiveImpairmentService.create(createDto);
            return new BaseResponse<>(true, response, "Relación creada exitosamente", HttpStatus.CREATED).buildResponseEntity();
        } catch (IllegalArgumentException e) {
            return new BaseResponse<ReactiveImpairmentResponseDto>(false, null, e.getMessage(), HttpStatus.BAD_REQUEST).buildResponseEntity();
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todas las relaciones reactivo-discapacidad", description = "Retorna una lista de todas las relaciones")
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<ReactiveImpairmentResponseDto>>> findAll() {
        List<ReactiveImpairmentResponseDto> relations = reactiveImpairmentService.findAll();
        return new BaseResponse<>(true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK).buildResponseEntity();
    }

    @GetMapping("/reactive/{reactiveId}")
    @Operation(summary = "Obtener relaciones por reactivo", description = "Retorna las relaciones de un reactivo específico")
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<ReactiveImpairmentResponseDto>>> findByReactive(
            @Parameter(description = "ID del reactivo") @PathVariable Long reactiveId) {
        List<ReactiveImpairmentResponseDto> relations = reactiveImpairmentService.findByReactive(reactiveId);
        return new BaseResponse<>(true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK).buildResponseEntity();
    }

    @GetMapping("/impairment/{impairmentId}")
    @Operation(summary = "Obtener relaciones por discapacidad", description = "Retorna las relaciones de una discapacidad específica")
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<ReactiveImpairmentResponseDto>>> findByImpairment(
            @Parameter(description = "ID de la discapacidad") @PathVariable Long impairmentId) {
        List<ReactiveImpairmentResponseDto> relations = reactiveImpairmentService.findByImpairment(impairmentId);
        return new BaseResponse<>(true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK).buildResponseEntity();
    }

    @GetMapping("/learning-path/{learningPathId}")
    @Operation(summary = "Obtener relaciones por ruta de aprendizaje", description = "Retorna las relaciones de una ruta de aprendizaje específica")
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<ReactiveImpairmentResponseDto>>> findByLearningPath(
            @Parameter(description = "ID de la ruta de aprendizaje") @PathVariable Long learningPathId) {
        List<ReactiveImpairmentResponseDto> relations = reactiveImpairmentService.findByLearningPath(learningPathId);
        return new BaseResponse<>(true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK).buildResponseEntity();
    }
}

