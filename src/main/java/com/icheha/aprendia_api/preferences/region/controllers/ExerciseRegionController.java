package com.icheha.aprendia_api.preferences.region.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.preferences.region.data.dtos.request.CreateExerciseRegionDto;
import com.icheha.aprendia_api.preferences.region.data.dtos.response.ExerciseRegionResponseDto;
import com.icheha.aprendia_api.preferences.region.services.IExerciseRegionService;
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
@RequestMapping("/exercise-regions")
@Tag(name = "3.11. Exercise Region Management", description = "Endpoints para gestión de relaciones ejercicio-región")
public class ExerciseRegionController {

    @Autowired
    private IExerciseRegionService exerciseRegionService;

    @PostMapping
    @Operation(summary = "Crear relación ejercicio-región", description = "Crea una nueva relación ejercicio-región")
    @ApiResponse(responseCode = "201", description = "Relación creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos o relación ya existe")
    public ResponseEntity<BaseResponse<ExerciseRegionResponseDto>> create(@Valid @RequestBody CreateExerciseRegionDto createDto) {
        try {
            ExerciseRegionResponseDto response = exerciseRegionService.create(createDto);
            return new BaseResponse<>(true, response, "Relación creada exitosamente", HttpStatus.CREATED).buildResponseEntity();
        } catch (IllegalArgumentException e) {
            return new BaseResponse<ExerciseRegionResponseDto>(false, null, e.getMessage(), HttpStatus.BAD_REQUEST).buildResponseEntity();
        }
    }

    @GetMapping("/exercise/{exerciseId}")
    @Operation(summary = "Obtener relaciones ejercicio-región por ID de ejercicio", description = "Retorna las relaciones de un ejercicio específico")
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<ExerciseRegionResponseDto>>> findByExercise(
            @Parameter(description = "ID del ejercicio") @PathVariable Long exerciseId) {
        List<ExerciseRegionResponseDto> relations = exerciseRegionService.findByExercise(exerciseId);
        return new BaseResponse<>(true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK).buildResponseEntity();
    }

    @GetMapping("/region/{regionId}/exercise-ids")
    @Operation(summary = "Obtener IDs de ejercicios por ID de región", description = "Retorna los IDs de los ejercicios asociados a una región")
    @ApiResponse(responseCode = "200", description = "IDs de ejercicios obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<Long>>> findByRegionOnlyIds(
            @Parameter(description = "ID de la región") @PathVariable Long regionId) {
        List<Long> exerciseIds = exerciseRegionService.findByRegionOnlyIds(regionId);
        return new BaseResponse<>(true, exerciseIds, "IDs de ejercicios obtenidos exitosamente", HttpStatus.OK).buildResponseEntity();
    }

    @GetMapping("/region/{regionId}")
    @Operation(summary = "Obtener relaciones ejercicio-región por ID de región", description = "Retorna las relaciones de una región específica")
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<ExerciseRegionResponseDto>>> findByRegion(
            @Parameter(description = "ID de la región") @PathVariable Long regionId) {
        List<ExerciseRegionResponseDto> relations = exerciseRegionService.findByRegion(regionId);
        return new BaseResponse<>(true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK).buildResponseEntity();
    }
}

