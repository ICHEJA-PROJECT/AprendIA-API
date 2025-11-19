package com.icheha.aprendia_api.users.person.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.person.data.dtos.request.CreateRoadTypeDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateRoadTypeDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.RoadTypeResponseDto;
import com.icheha.aprendia_api.users.person.services.IRoadTypeService;
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
@RequestMapping("/road-types")
@Tag(name = "2.10. Road Types", description = "API para gestión de tipos de vialidad")
public class RoadTypeController {
    
    private final IRoadTypeService roadTypeService;
    
    @Autowired
    public RoadTypeController(IRoadTypeService roadTypeService) {
        this.roadTypeService = roadTypeService;
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener todos los tipos de vialidad", 
        description = "Obtener todos los tipos de vialidad disponibles con paginación. Parámetros: page (número de página, default: 0), size (tamaño de página, default: 20), sort (campo de ordenamiento, default: nombre)"
    )
    @ApiResponse(responseCode = "200", description = "Tipos de vialidad obtenidos exitosamente")
    @Parameter(name = "page", description = "Número de página (0-indexed)", example = "0")
    @Parameter(name = "size", description = "Tamaño de página", example = "20")
    @Parameter(name = "sort", description = "Campo de ordenamiento (ej: nombre,asc)", example = "nombre")
    public ResponseEntity<BaseResponse<Page<RoadTypeResponseDto>>> findAll(
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        Page<RoadTypeResponseDto> roadTypes = roadTypeService.findAll(pageable);
        BaseResponse<Page<RoadTypeResponseDto>> response = new BaseResponse<>(
                true, roadTypes, "Tipos de vialidad obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PostMapping
    @Operation(summary = "Crear tipo de vialidad", description = "Crear nuevo tipo de vialidad")
    @ApiResponse(responseCode = "201", description = "Tipo de vialidad creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<RoadTypeResponseDto>> create(
            @Valid @RequestBody CreateRoadTypeDto createRoadTypeDto) {
        RoadTypeResponseDto roadType = roadTypeService.create(createRoadTypeDto);
        BaseResponse<RoadTypeResponseDto> response = new BaseResponse<>(
                true, roadType, "Tipo de vialidad creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener tipo de vialidad por ID", description = "Obtener un tipo de vialidad por su ID")
    @ApiResponse(responseCode = "200", description = "Tipo de vialidad obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Tipo de vialidad no encontrado")
    public ResponseEntity<BaseResponse<RoadTypeResponseDto>> findById(
            @Parameter(description = "ID del tipo de vialidad") @PathVariable Long id) {
        try {
            RoadTypeResponseDto roadType = roadTypeService.findById(id);
            BaseResponse<RoadTypeResponseDto> response = new BaseResponse<>(
                    true, roadType, "Tipo de vialidad obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<RoadTypeResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tipo de vialidad", description = "Actualizar un tipo de vialidad existente")
    @ApiResponse(responseCode = "200", description = "Tipo de vialidad actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Tipo de vialidad no encontrado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<RoadTypeResponseDto>> update(
            @Parameter(description = "ID del tipo de vialidad") @PathVariable Long id,
            @Valid @RequestBody UpdateRoadTypeDto updateRoadTypeDto) {
        try {
            RoadTypeResponseDto roadType = roadTypeService.update(id, updateRoadTypeDto);
            BaseResponse<RoadTypeResponseDto> response = new BaseResponse<>(
                    true, roadType, "Tipo de vialidad actualizado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<RoadTypeResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tipo de vialidad", description = "Eliminar un tipo de vialidad por su ID")
    @ApiResponse(responseCode = "200", description = "Tipo de vialidad eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Tipo de vialidad no encontrado")
    public ResponseEntity<BaseResponse<Void>> deleteById(
            @Parameter(description = "ID del tipo de vialidad") @PathVariable Long id) {
        try {
            roadTypeService.deleteById(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Tipo de vialidad eliminado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @GetMapping("/search")
    @Operation(
        summary = "Buscar tipos de vialidad", 
        description = "Buscar tipos de vialidad por nombre con paginación. Parámetros: search (término de búsqueda), page (número de página, default: 0), size (tamaño de página, default: 20), sort (campo de ordenamiento, default: nombre)"
    )
    @ApiResponse(responseCode = "200", description = "Tipos de vialidad obtenidos exitosamente")
    @Parameter(name = "search", description = "Término de búsqueda (nombre)", example = "calle")
    @Parameter(name = "page", description = "Número de página (0-indexed)", example = "0")
    @Parameter(name = "size", description = "Tamaño de página", example = "20")
    @Parameter(name = "sort", description = "Campo de ordenamiento (ej: nombre,asc)", example = "nombre")
    public ResponseEntity<BaseResponse<Page<RoadTypeResponseDto>>> search(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        Page<RoadTypeResponseDto> roadTypes = roadTypeService.search(search, pageable);
        BaseResponse<Page<RoadTypeResponseDto>> response = new BaseResponse<>(
                true, roadTypes, "Tipos de vialidad obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

