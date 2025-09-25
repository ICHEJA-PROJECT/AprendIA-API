package com.icheha.aprendia_api.preferences.occupation.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.CreateOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.response.OccupationResponseDto;
import com.icheha.aprendia_api.preferences.occupation.services.IOccupationService;
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
@RequestMapping("/preferences/occupations")
@Tag(name = "Occupation Management", description = "Endpoints para gestión de ocupaciones")
public class OccupationController {
    
    private static final Logger logger = LoggerFactory.getLogger(OccupationController.class);
    
    @Autowired
    private IOccupationService occupationService;
    
    @PostMapping
    @Operation(
        summary = "Crear ocupación",
        description = "Crear una nueva ocupación en el sistema"
    )
    @ApiResponse(responseCode = "201", description = "Ocupación creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos o ocupación ya existe")
    public ResponseEntity<BaseResponse<OccupationResponseDto>> create(@Valid @RequestBody CreateOccupationDto createDto) {
        try {
            logger.debug("Received request to create occupation: {}", createDto.getName());
            
            OccupationResponseDto response = occupationService.create(createDto);
            
            BaseResponse<OccupationResponseDto> baseResponse = new BaseResponse<>(
                true,
                response,
                "Ocupación creada exitosamente",
                HttpStatus.CREATED
            );
            
            return baseResponse.buildResponseEntity();
        } catch (IllegalArgumentException e) {
            logger.warn("Failed to create occupation: {} - {}", createDto.getName(), e.getMessage());
            
            BaseResponse<OccupationResponseDto> baseResponse = new BaseResponse<>(
                false,
                null,
                e.getMessage(),
                HttpStatus.BAD_REQUEST
            );
            
            return baseResponse.buildResponseEntity();
        } catch (Exception e) {
            logger.error("Unexpected error creating occupation: {}", createDto.getName(), e);
            
            BaseResponse<OccupationResponseDto> baseResponse = new BaseResponse<>(
                false,
                null,
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
            
            return baseResponse.buildResponseEntity();
        }
    }
    
    @GetMapping
    @Operation(
        summary = "Listar ocupaciones",
        description = "Obtener todas las ocupaciones del sistema"
    )
    @ApiResponse(responseCode = "200", description = "Lista de ocupaciones obtenida exitosamente")
    public ResponseEntity<BaseResponse<List<OccupationResponseDto>>> findAll() {
        try {
            logger.debug("Received request to find all occupations");
            
            List<OccupationResponseDto> response = occupationService.findAll();
            
            BaseResponse<List<OccupationResponseDto>> baseResponse = new BaseResponse<>(
                true,
                response,
                "Ocupaciones obtenidas exitosamente",
                HttpStatus.OK
            );
            
            return baseResponse.buildResponseEntity();
        } catch (Exception e) {
            logger.error("Unexpected error finding all occupations", e);
            
            BaseResponse<List<OccupationResponseDto>> baseResponse = new BaseResponse<>(
                false,
                null,
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
            
            return baseResponse.buildResponseEntity();
        }
    }
    
    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar ocupación por ID",
        description = "Obtener una ocupación específica por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Ocupación encontrada exitosamente")
    @ApiResponse(responseCode = "404", description = "Ocupación no encontrada")
    public ResponseEntity<BaseResponse<OccupationResponseDto>> findById(
            @Parameter(description = "ID de la ocupación") @PathVariable Long id) {
        try {
            logger.debug("Received request to find occupation by ID: {}", id);
            
            OccupationResponseDto response = occupationService.findById(id);
            
            BaseResponse<OccupationResponseDto> baseResponse = new BaseResponse<>(
                true,
                response,
                "Ocupación encontrada exitosamente",
                HttpStatus.OK
            );
            
            return baseResponse.buildResponseEntity();
        } catch (IllegalArgumentException e) {
            logger.warn("Occupation not found with ID: {} - {}", id, e.getMessage());
            
            BaseResponse<OccupationResponseDto> baseResponse = new BaseResponse<>(
                false,
                null,
                e.getMessage(),
                HttpStatus.NOT_FOUND
            );
            
            return baseResponse.buildResponseEntity();
        } catch (Exception e) {
            logger.error("Unexpected error finding occupation by ID: {}", id, e);
            
            BaseResponse<OccupationResponseDto> baseResponse = new BaseResponse<>(
                false,
                null,
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
            
            return baseResponse.buildResponseEntity();
        }
    }
    
    @GetMapping("/search")
    @Operation(
        summary = "Buscar ocupaciones por nombre",
        description = "Buscar ocupaciones que contengan el nombre especificado"
    )
    @ApiResponse(responseCode = "200", description = "Búsqueda completada exitosamente")
    public ResponseEntity<BaseResponse<List<OccupationResponseDto>>> findByNameContaining(
            @Parameter(description = "Nombre a buscar") @RequestParam String name) {
        try {
            logger.debug("Received request to search occupations by name: {}", name);
            
            List<OccupationResponseDto> response = occupationService.findByNameContaining(name);
            
            BaseResponse<List<OccupationResponseDto>> baseResponse = new BaseResponse<>(
                true,
                response,
                "Búsqueda completada exitosamente",
                HttpStatus.OK
            );
            
            return baseResponse.buildResponseEntity();
        } catch (Exception e) {
            logger.error("Unexpected error searching occupations by name: {}", name, e);
            
            BaseResponse<List<OccupationResponseDto>> baseResponse = new BaseResponse<>(
                false,
                null,
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
            
            return baseResponse.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar ocupación",
        description = "Eliminar una ocupación por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Ocupación eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Ocupación no encontrada")
    public ResponseEntity<BaseResponse<Void>> deleteById(
            @Parameter(description = "ID de la ocupación") @PathVariable Long id) {
        try {
            logger.debug("Received request to delete occupation with ID: {}", id);
            
            occupationService.deleteById(id);
            
            BaseResponse<Void> baseResponse = new BaseResponse<>(
                true,
                null,
                "Ocupación eliminada exitosamente",
                HttpStatus.OK
            );
            
            return baseResponse.buildResponseEntity();
        } catch (IllegalArgumentException e) {
            logger.warn("Failed to delete occupation with ID: {} - {}", id, e.getMessage());
            
            BaseResponse<Void> baseResponse = new BaseResponse<>(
                false,
                null,
                e.getMessage(),
                HttpStatus.NOT_FOUND
            );
            
            return baseResponse.buildResponseEntity();
        } catch (Exception e) {
            logger.error("Unexpected error deleting occupation with ID: {}", id, e);
            
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
        summary = "Verificar existencia de ocupación",
        description = "Verificar si existe una ocupación con el nombre especificado"
    )
    @ApiResponse(responseCode = "200", description = "Verificación completada")
    public ResponseEntity<BaseResponse<Boolean>> existsByName(
            @Parameter(description = "Nombre de la ocupación") @RequestParam String name) {
        try {
            logger.debug("Received request to check if occupation exists with name: {}", name);
            
            boolean exists = occupationService.existsByName(name);
            
            BaseResponse<Boolean> baseResponse = new BaseResponse<>(
                true,
                exists,
                "Verificación completada",
                HttpStatus.OK
            );
            
            return baseResponse.buildResponseEntity();
        } catch (Exception e) {
            logger.error("Unexpected error checking occupation existence: {}", name, e);
            
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
