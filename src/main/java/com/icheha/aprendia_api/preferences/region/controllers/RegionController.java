package com.icheha.aprendia_api.preferences.region.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.preferences.region.data.dtos.request.CreateRegionDto;
import com.icheha.aprendia_api.preferences.region.data.dtos.request.UpdateRegionDto;
import com.icheha.aprendia_api.preferences.region.data.dtos.response.RegionResponseDto;
import com.icheha.aprendia_api.preferences.region.services.IRegionService;
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
@RequestMapping("/regions")
@Tag(name = "3.09. Regions", description = "Endpoints de gestión de regiones")
public class RegionController {
    
    @Autowired
    private IRegionService regionService;
    
    @PostMapping
    @Operation(
        summary = "Crear región",
        description = "Crear una nueva región en el sistema"
    )
    @ApiResponse(responseCode = "201", description = "Región creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<RegionResponseDto>> create(
            @Valid @RequestBody CreateRegionDto createDto) {
        RegionResponseDto response = regionService.create(createDto);
        BaseResponse<RegionResponseDto> baseResponse = new BaseResponse<>(
                true, response, "Región creada exitosamente", HttpStatus.CREATED);
        return baseResponse.buildResponseEntity();
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener todas las regiones",
        description = "Retorna una lista de todas las regiones disponibles"
    )
    @ApiResponse(responseCode = "200", description = "Regiones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<RegionResponseDto>>> findAll() {
        List<RegionResponseDto> regions = regionService.findAll();
        BaseResponse<List<RegionResponseDto>> response = new BaseResponse<>(
                true, regions, "Regiones obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener región por ID",
        description = "Obtiene una región específica por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Región encontrada")
    @ApiResponse(responseCode = "404", description = "Región no encontrada")
    public ResponseEntity<BaseResponse<RegionResponseDto>> findById(
            @Parameter(description = "ID de la región") @PathVariable Long id) {
        RegionResponseDto region = regionService.findById(id);
        BaseResponse<RegionResponseDto> response = new BaseResponse<>(
                true, region, "Región obtenida exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar región",
        description = "Actualiza una región existente"
    )
    @ApiResponse(responseCode = "200", description = "Región actualizada exitosamente")
    @ApiResponse(responseCode = "404", description = "Región no encontrada")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<RegionResponseDto>> update(
            @Parameter(description = "ID de la región") @PathVariable Long id,
            @Valid @RequestBody UpdateRegionDto updateDto) {
        RegionResponseDto response = regionService.update(id, updateDto);
        BaseResponse<RegionResponseDto> baseResponse = new BaseResponse<>(
                true, response, "Región actualizada exitosamente", HttpStatus.OK);
        return baseResponse.buildResponseEntity();
    }
    
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar región",
        description = "Elimina una región por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Región eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Región no encontrada")
    public ResponseEntity<BaseResponse<Void>> deleteById(
            @Parameter(description = "ID de la región") @PathVariable Long id) {
        regionService.delete(id);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Región eliminada exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

