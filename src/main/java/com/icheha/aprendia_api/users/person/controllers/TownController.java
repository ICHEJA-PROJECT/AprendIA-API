package com.icheha.aprendia_api.users.person.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.person.data.dtos.request.CreateTownDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateTownDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.TownResponseDto;
import com.icheha.aprendia_api.users.person.services.ITownService;
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
@RequestMapping("/towns")
@Tag(name = "2.12. Towns", description = "API para gestión de ciudades")
public class TownController {
    
    private final ITownService townService;
    
    @Autowired
    public TownController(ITownService townService) {
        this.townService = townService;
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener todas las ciudades", 
        description = "Obtener todas las ciudades disponibles con paginación. Parámetros: page (número de página, default: 0), size (tamaño de página, default: 20), sort (campo de ordenamiento, default: nombre)"
    )
    @ApiResponse(responseCode = "200", description = "Ciudades obtenidas exitosamente")
    @Parameter(name = "page", description = "Número de página (0-indexed)", example = "0")
    @Parameter(name = "size", description = "Tamaño de página", example = "20")
    @Parameter(name = "sort", description = "Campo de ordenamiento (ej: nombre,asc)", example = "nombre")
    public ResponseEntity<BaseResponse<Page<TownResponseDto>>> findAll(
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        Page<TownResponseDto> towns = townService.findAll(pageable);
        BaseResponse<Page<TownResponseDto>> response = new BaseResponse<>(
                true, towns, "Ciudades obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PostMapping
    @Operation(summary = "Crear ciudad", description = "Crear nueva ciudad")
    @ApiResponse(responseCode = "201", description = "Ciudad creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<TownResponseDto>> create(
            @Valid @RequestBody CreateTownDto createTownDto) {
        TownResponseDto town = townService.create(createTownDto);
        BaseResponse<TownResponseDto> response = new BaseResponse<>(
                true, town, "Ciudad creada exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener ciudad por ID", description = "Obtener una ciudad por su ID")
    @ApiResponse(responseCode = "200", description = "Ciudad obtenida exitosamente")
    @ApiResponse(responseCode = "404", description = "Ciudad no encontrada")
    public ResponseEntity<BaseResponse<TownResponseDto>> findById(
            @Parameter(description = "ID de la ciudad") @PathVariable Long id) {
        try {
            TownResponseDto town = townService.findById(id);
            BaseResponse<TownResponseDto> response = new BaseResponse<>(
                    true, town, "Ciudad obtenida exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<TownResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar ciudad", description = "Actualizar una ciudad existente")
    @ApiResponse(responseCode = "200", description = "Ciudad actualizada exitosamente")
    @ApiResponse(responseCode = "404", description = "Ciudad no encontrada")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<TownResponseDto>> update(
            @Parameter(description = "ID de la ciudad") @PathVariable Long id,
            @Valid @RequestBody UpdateTownDto updateTownDto) {
        try {
            TownResponseDto town = townService.update(id, updateTownDto);
            BaseResponse<TownResponseDto> response = new BaseResponse<>(
                    true, town, "Ciudad actualizada exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<TownResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar ciudad", description = "Eliminar una ciudad por su ID")
    @ApiResponse(responseCode = "200", description = "Ciudad eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Ciudad no encontrada")
    public ResponseEntity<BaseResponse<Void>> deleteById(
            @Parameter(description = "ID de la ciudad") @PathVariable Long id) {
        try {
            townService.deleteById(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Ciudad eliminada exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @GetMapping("/municipality/{municipalityId}")
    @Operation(summary = "Buscar ciudades por municipio", description = "Obtener todas las ciudades de un municipio")
    @ApiResponse(responseCode = "200", description = "Ciudades obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<TownResponseDto>>> findByMunicipality(
            @Parameter(description = "ID del municipio", required = true)
            @PathVariable Long municipalityId) {
        List<TownResponseDto> towns = townService.findByMunicipality(municipalityId);
        BaseResponse<List<TownResponseDto>> response = new BaseResponse<>(
                true, towns, "Ciudades obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/search")
    @Operation(
        summary = "Buscar ciudades", 
        description = "Buscar ciudades por nombre o municipio con paginación. Parámetros: search (término de búsqueda), page (número de página, default: 0), size (tamaño de página, default: 20), sort (campo de ordenamiento, default: nombre)"
    )
    @ApiResponse(responseCode = "200", description = "Ciudades obtenidas exitosamente")
    @Parameter(name = "search", description = "Término de búsqueda (nombre o municipio)", example = "centro")
    @Parameter(name = "page", description = "Número de página (0-indexed)", example = "0")
    @Parameter(name = "size", description = "Tamaño de página", example = "20")
    @Parameter(name = "sort", description = "Campo de ordenamiento (ej: nombre,asc)", example = "nombre")
    public ResponseEntity<BaseResponse<Page<TownResponseDto>>> search(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        Page<TownResponseDto> towns = townService.search(search, pageable);
        BaseResponse<Page<TownResponseDto>> response = new BaseResponse<>(
                true, towns, "Ciudades obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

