package com.icheha.aprendia_api.preferences.occupation.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.CreateExerciseOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.response.ExerciseOccupationResponseDto;
import com.icheha.aprendia_api.preferences.occupation.services.IExerciseOccupationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preferences/exercise-occupations")
@Tag(name = "Exercise-Occupation Management", description = "Endpoints para gestión de relaciones ejercicio-ocupación")
public class ExerciseOccupationController {
    
    private static final Logger logger = LoggerFactory.getLogger(ExerciseOccupationController.class);
    
    @Autowired
    private IExerciseOccupationService exerciseOccupationService;
    
    @PostMapping
    @Operation(
        summary = "Crear relación ejercicio-ocupación",
        description = "Crear una nueva relación entre un ejercicio y una ocupación"
    )
    @ApiResponse(responseCode = "201", description = "Relación creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos o relación ya existe")
    public ResponseEntity<BaseResponse<ExerciseOccupationResponseDto>> create(@Valid @RequestBody CreateExerciseOccupationDto createDto) {
        try {
            logger.debug("Received request to create exercise-occupation relation: exerciseId={}, occupationId={}", 
                        createDto.getExerciseId(), createDto.getOccupationId());
            
            ExerciseOccupationResponseDto response = exerciseOccupationService.create(createDto);
            
            BaseResponse<ExerciseOccupationResponseDto> baseResponse = new BaseResponse<>(
                true,
                response,
                "Relación ejercicio-ocupación creada exitosamente",
                HttpStatus.CREATED
            );
            
            return baseResponse.buildResponseEntity();
        } catch (IllegalArgumentException e) {
            logger.warn("Failed to create exercise-occupation relation: {} - {}", 
                       createDto.getExerciseId() + "-" + createDto.getOccupationId(), e.getMessage());
            
            BaseResponse<ExerciseOccupationResponseDto> baseResponse = new BaseResponse<>(
                false,
                null,
                e.getMessage(),
                HttpStatus.BAD_REQUEST
            );
            
            return baseResponse.buildResponseEntity();
        } catch (Exception e) {
            logger.error("Unexpected error creating exercise-occupation relation: {}", 
                        createDto.getExerciseId() + "-" + createDto.getOccupationId(), e);
            
            BaseResponse<ExerciseOccupationResponseDto> baseResponse = new BaseResponse<>(
                false,
                null,
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
            
            return baseResponse.buildResponseEntity();
        }
    }
    
    @GetMapping("/by-occupation/{occupationId}")
    @Operation(
        summary = "Buscar ejercicios por ocupación",
        description = "Obtener todos los ejercicios asociados a una ocupación específica"
    )
    @ApiResponse(responseCode = "200", description = "Ejercicios obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<ExerciseOccupationResponseDto>>> findByOccupationId(
            @Parameter(description = "ID de la ocupación") @PathVariable Long occupationId) {
        try {
            logger.debug("Received request to find exercises by occupation ID: {}", occupationId);
            
            List<ExerciseOccupationResponseDto> response = exerciseOccupationService.findByOccupationId(occupationId);
            
            BaseResponse<List<ExerciseOccupationResponseDto>> baseResponse = new BaseResponse<>(
                true,
                response,
                "Ejercicios obtenidos exitosamente",
                HttpStatus.OK
            );
            
            return baseResponse.buildResponseEntity();
        } catch (Exception e) {
            logger.error("Unexpected error finding exercises by occupation ID: {}", occupationId, e);
            
            BaseResponse<List<ExerciseOccupationResponseDto>> baseResponse = new BaseResponse<>(
                false,
                null,
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
            
            return baseResponse.buildResponseEntity();
        }
    }
    
    @GetMapping("/by-exercise/{exerciseId}")
    @Operation(
        summary = "Buscar ocupaciones por ejercicio",
        description = "Obtener todas las ocupaciones asociadas a un ejercicio específico"
    )
    @ApiResponse(responseCode = "200", description = "Ocupaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<ExerciseOccupationResponseDto>>> findByExerciseId(
            @Parameter(description = "ID del ejercicio") @PathVariable Long exerciseId) {
        try {
            logger.debug("Received request to find occupations by exercise ID: {}", exerciseId);
            
            List<ExerciseOccupationResponseDto> response = exerciseOccupationService.findByExerciseId(exerciseId);
            
            BaseResponse<List<ExerciseOccupationResponseDto>> baseResponse = new BaseResponse<>(
                true,
                response,
                "Ocupaciones obtenidas exitosamente",
                HttpStatus.OK
            );
            
            return baseResponse.buildResponseEntity();
        } catch (Exception e) {
            logger.error("Unexpected error finding occupations by exercise ID: {}", exerciseId, e);
            
            BaseResponse<List<ExerciseOccupationResponseDto>> baseResponse = new BaseResponse<>(
                false,
                null,
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
            
            return baseResponse.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{exerciseId}/{occupationId}")
    @Operation(
        summary = "Eliminar relación ejercicio-ocupación",
        description = "Eliminar la relación entre un ejercicio y una ocupación específica"
    )
    @ApiResponse(responseCode = "200", description = "Relación eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Relación no encontrada")
    public ResponseEntity<BaseResponse<Void>> deleteByExerciseIdAndOccupationId(
            @Parameter(description = "ID del ejercicio") @PathVariable Long exerciseId,
            @Parameter(description = "ID de la ocupación") @PathVariable Long occupationId) {
        try {
            logger.debug("Received request to delete exercise-occupation relation: exerciseId={}, occupationId={}", 
                        exerciseId, occupationId);
            
            exerciseOccupationService.deleteByExerciseIdAndOccupationId(exerciseId, occupationId);
            
            BaseResponse<Void> baseResponse = new BaseResponse<>(
                true,
                null,
                "Relación ejercicio-ocupación eliminada exitosamente",
                HttpStatus.OK
            );
            
            return baseResponse.buildResponseEntity();
        } catch (IllegalArgumentException e) {
            logger.warn("Failed to delete exercise-occupation relation: {} - {}", 
                       exerciseId + "-" + occupationId, e.getMessage());
            
            BaseResponse<Void> baseResponse = new BaseResponse<>(
                false,
                null,
                e.getMessage(),
                HttpStatus.NOT_FOUND
            );
            
            return baseResponse.buildResponseEntity();
        } catch (Exception e) {
            logger.error("Unexpected error deleting exercise-occupation relation: {}", 
                        exerciseId + "-" + occupationId, e);
            
            BaseResponse<Void> baseResponse = new BaseResponse<>(
                false,
                null,
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
            
            return baseResponse.buildResponseEntity();
        }
    }
    
    @GetMapping("/exists")
    @Operation(
        summary = "Verificar existencia de relación",
        description = "Verificar si existe una relación entre un ejercicio y una ocupación"
    )
    @ApiResponse(responseCode = "200", description = "Verificación completada")
    public ResponseEntity<BaseResponse<Boolean>> existsByExerciseIdAndOccupationId(
            @Parameter(description = "ID del ejercicio") @RequestParam Long exerciseId,
            @Parameter(description = "ID de la ocupación") @RequestParam Long occupationId) {
        try {
            logger.debug("Received request to check if exercise-occupation relation exists: exerciseId={}, occupationId={}", 
                        exerciseId, occupationId);
            
            boolean exists = exerciseOccupationService.existsByExerciseIdAndOccupationId(exerciseId, occupationId);
            
            BaseResponse<Boolean> baseResponse = new BaseResponse<>(
                true,
                exists,
                "Verificación completada",
                HttpStatus.OK
            );
            
            return baseResponse.buildResponseEntity();
        } catch (Exception e) {
            logger.error("Unexpected error checking exercise-occupation relation existence: {}", 
                        exerciseId + "-" + occupationId, e);
            
            BaseResponse<Boolean> baseResponse = new BaseResponse<>(
                false,
                null,
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
            
            return baseResponse.buildResponseEntity();
        }
    }
}
