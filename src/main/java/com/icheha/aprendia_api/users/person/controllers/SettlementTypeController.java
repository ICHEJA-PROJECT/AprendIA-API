package com.icheha.aprendia_api.users.person.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.person.data.dtos.request.CreateSettlementTypeDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateSettlementTypeDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.SettlementTypeResponseDto;
import com.icheha.aprendia_api.users.person.services.ISettlementTypeService;
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

@RestController
@RequestMapping("/settlement-types")
@Tag(name = "2.13. Settlement Types", description = "API para gestión de tipos de asentamiento")
public class SettlementTypeController {
    
    private final ISettlementTypeService settlementTypeService;
    
    @Autowired
    public SettlementTypeController(ISettlementTypeService settlementTypeService) {
        this.settlementTypeService = settlementTypeService;
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener todos los tipos de asentamiento", 
        description = "Obtener todos los tipos de asentamiento disponibles con paginación. Parámetros: page (número de página, default: 0), size (tamaño de página, default: 20), sort (campo de ordenamiento, default: nombre)"
    )
    @ApiResponse(responseCode = "200", description = "Tipos de asentamiento obtenidos exitosamente")
    @Parameter(name = "page", description = "Número de página (0-indexed)", example = "0")
    @Parameter(name = "size", description = "Tamaño de página", example = "20")
    @Parameter(name = "sort", description = "Campo de ordenamiento (ej: nombre,asc)", example = "nombre")
    public ResponseEntity<BaseResponse<Page<SettlementTypeResponseDto>>> findAll(
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        Page<SettlementTypeResponseDto> settlementTypes = settlementTypeService.findAll(pageable);
        BaseResponse<Page<SettlementTypeResponseDto>> response = new BaseResponse<>(
                true, settlementTypes, "Tipos de asentamiento obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/search")
    @Operation(
        summary = "Buscar tipos de asentamiento", 
        description = "Buscar tipos de asentamiento por nombre con paginación. Parámetros: search (término de búsqueda), page (número de página, default: 0), size (tamaño de página, default: 20), sort (campo de ordenamiento, default: nombre)"
    )
    @ApiResponse(responseCode = "200", description = "Tipos de asentamiento obtenidos exitosamente")
    @Parameter(name = "search", description = "Término de búsqueda (nombre)", example = "colonia")
    @Parameter(name = "page", description = "Número de página (0-indexed)", example = "0")
    @Parameter(name = "size", description = "Tamaño de página", example = "20")
    @Parameter(name = "sort", description = "Campo de ordenamiento (ej: nombre,asc)", example = "nombre")
    public ResponseEntity<BaseResponse<Page<SettlementTypeResponseDto>>> search(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        Page<SettlementTypeResponseDto> settlementTypes = settlementTypeService.search(search, pageable);
        BaseResponse<Page<SettlementTypeResponseDto>> response = new BaseResponse<>(
                true, settlementTypes, "Tipos de asentamiento obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PostMapping
    @Operation(summary = "Crear tipo de asentamiento", description = "Crear nuevo tipo de asentamiento")
    @ApiResponse(responseCode = "201", description = "Tipo de asentamiento creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<SettlementTypeResponseDto>> create(
            @Valid @RequestBody CreateSettlementTypeDto createSettlementTypeDto) {
        SettlementTypeResponseDto settlementType = settlementTypeService.create(createSettlementTypeDto);
        BaseResponse<SettlementTypeResponseDto> response = new BaseResponse<>(
                true, settlementType, "Tipo de asentamiento creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener tipo de asentamiento por ID", description = "Obtener un tipo de asentamiento por su ID")
    @ApiResponse(responseCode = "200", description = "Tipo de asentamiento obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Tipo de asentamiento no encontrado")
    public ResponseEntity<BaseResponse<SettlementTypeResponseDto>> findById(
            @Parameter(description = "ID del tipo de asentamiento") @PathVariable Long id) {
        try {
            SettlementTypeResponseDto settlementType = settlementTypeService.findById(id);
            BaseResponse<SettlementTypeResponseDto> response = new BaseResponse<>(
                    true, settlementType, "Tipo de asentamiento obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<SettlementTypeResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tipo de asentamiento", description = "Actualizar un tipo de asentamiento existente")
    @ApiResponse(responseCode = "200", description = "Tipo de asentamiento actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Tipo de asentamiento no encontrado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<SettlementTypeResponseDto>> update(
            @Parameter(description = "ID del tipo de asentamiento") @PathVariable Long id,
            @Valid @RequestBody UpdateSettlementTypeDto updateSettlementTypeDto) {
        try {
            SettlementTypeResponseDto settlementType = settlementTypeService.update(id, updateSettlementTypeDto);
            BaseResponse<SettlementTypeResponseDto> response = new BaseResponse<>(
                    true, settlementType, "Tipo de asentamiento actualizado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<SettlementTypeResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tipo de asentamiento", description = "Eliminar un tipo de asentamiento por su ID")
    @ApiResponse(responseCode = "200", description = "Tipo de asentamiento eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Tipo de asentamiento no encontrado")
    public ResponseEntity<BaseResponse<Void>> deleteById(
            @Parameter(description = "ID del tipo de asentamiento") @PathVariable Long id) {
        try {
            settlementTypeService.deleteById(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Tipo de asentamiento eliminado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
}

