package com.icheha.aprendia_api.users.person.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.person.data.dtos.request.CreateSettlementDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateSettlementDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.SettlementResponseDto;
import com.icheha.aprendia_api.users.person.services.ISettlementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settlements")
@Tag(name = "2.11. Settlements", description = "API para gestión de asentamientos")
public class SettlementController {
    
    private final ISettlementService settlementService;
    
    @Autowired
    public SettlementController(ISettlementService settlementService) {
        this.settlementService = settlementService;
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener todos los asentamientos", 
        description = "Obtener todos los asentamientos disponibles con paginación. Parámetros: page (número de página, default: 0), size (tamaño de página, default: 20), sort (campo de ordenamiento, default: nombre)"
    )
    @ApiResponse(responseCode = "200", description = "Asentamientos obtenidos exitosamente")
    @Parameter(name = "page", description = "Número de página (0-indexed)", example = "0")
    @Parameter(name = "size", description = "Tamaño de página", example = "20")
    @Parameter(name = "sort", description = "Campo de ordenamiento (ej: nombre,asc)", example = "nombre")
    public ResponseEntity<BaseResponse<Page<SettlementResponseDto>>> findAll(
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        Page<SettlementResponseDto> settlements = settlementService.findAll(pageable);
        BaseResponse<Page<SettlementResponseDto>> response = new BaseResponse<>(
                true, settlements, "Asentamientos obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PostMapping
    @Operation(summary = "Crear asentamiento", description = "Crear nuevo asentamiento")
    @ApiResponse(responseCode = "201", description = "Asentamiento creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<SettlementResponseDto>> create(
            @Valid @RequestBody CreateSettlementDto createSettlementDto) {
        SettlementResponseDto settlement = settlementService.create(createSettlementDto);
        BaseResponse<SettlementResponseDto> response = new BaseResponse<>(
                true, settlement, "Asentamiento creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener asentamiento por ID", description = "Obtener un asentamiento por su ID")
    @ApiResponse(responseCode = "200", description = "Asentamiento obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Asentamiento no encontrado")
    public ResponseEntity<BaseResponse<SettlementResponseDto>> findById(
            @Parameter(description = "ID del asentamiento") @PathVariable Long id) {
        try {
            SettlementResponseDto settlement = settlementService.findById(id);
            BaseResponse<SettlementResponseDto> response = new BaseResponse<>(
                    true, settlement, "Asentamiento obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<SettlementResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar asentamiento", description = "Actualizar un asentamiento existente")
    @ApiResponse(responseCode = "200", description = "Asentamiento actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Asentamiento no encontrado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<SettlementResponseDto>> update(
            @Parameter(description = "ID del asentamiento") @PathVariable Long id,
            @Valid @RequestBody UpdateSettlementDto updateSettlementDto) {
        try {
            SettlementResponseDto settlement = settlementService.update(id, updateSettlementDto);
            BaseResponse<SettlementResponseDto> response = new BaseResponse<>(
                    true, settlement, "Asentamiento actualizado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<SettlementResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar asentamiento", description = "Eliminar un asentamiento por su ID")
    @ApiResponse(responseCode = "200", description = "Asentamiento eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Asentamiento no encontrado")
    public ResponseEntity<BaseResponse<Void>> deleteById(
            @Parameter(description = "ID del asentamiento") @PathVariable Long id) {
        try {
            settlementService.deleteById(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Asentamiento eliminado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @GetMapping("/zipcode/{zipcode}")
    @Operation(summary = "Buscar asentamientos por código postal", description = "Obtener todos los asentamientos de un código postal para formularios de ingreso")
    @ApiResponse(responseCode = "200", description = "Asentamientos obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<SettlementResponseDto>>> findByZipcode(
            @Parameter(description = "Código postal (5 dígitos)", required = true, example = "29000")
            @PathVariable String zipcode) {
        List<SettlementResponseDto> settlements = settlementService.findByZipcode(zipcode);
        BaseResponse<List<SettlementResponseDto>> response = new BaseResponse<>(
                true, settlements, "Asentamientos obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/search")
    @Operation(
        summary = "Buscar asentamientos", 
        description = "Buscar asentamientos por nombre o código postal con paginación. Parámetros: search (término de búsqueda), page (número de página, default: 0), size (tamaño de página, default: 20), sort (campo de ordenamiento, default: nombre)"
    )
    @ApiResponse(responseCode = "200", description = "Asentamientos obtenidos exitosamente")
    @Parameter(name = "search", description = "Término de búsqueda (nombre o código postal)", example = "centro")
    @Parameter(name = "page", description = "Número de página (0-indexed)", example = "0")
    @Parameter(name = "size", description = "Tamaño de página", example = "20")
    @Parameter(name = "sort", description = "Campo de ordenamiento (ej: nombre,asc)", example = "nombre")
    public ResponseEntity<BaseResponse<Page<SettlementResponseDto>>> search(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        Page<SettlementResponseDto> settlements = settlementService.search(search, pageable);
        BaseResponse<Page<SettlementResponseDto>> response = new BaseResponse<>(
                true, settlements, "Asentamientos obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/municipality/{municipalityId}")
    @Operation(summary = "Buscar asentamientos por municipio", description = "Obtener todos los asentamientos de un municipio")
    @ApiResponse(responseCode = "200", description = "Asentamientos obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<SettlementResponseDto>>> findByMunicipality(
            @Parameter(description = "ID del municipio", required = true)
            @PathVariable Long municipalityId,
            @Parameter(description = "ID de la ciudad (opcional)")
            @RequestParam(required = false) Long townId) {
        List<SettlementResponseDto> settlements = settlementService.findByMunicipalityAndTown(municipalityId, townId);
        BaseResponse<List<SettlementResponseDto>> response = new BaseResponse<>(
                true, settlements, "Asentamientos obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

