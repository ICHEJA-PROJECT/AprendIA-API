package com.icheha.aprendia_api.users.person.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.person.data.dtos.request.CreateMunicipalityDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateMunicipalityDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.MunicipalityResponseDto;
import com.icheha.aprendia_api.users.person.services.IMunicipalityService;
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
@RequestMapping("/municipalities")
@Tag(name = "2.13. Municipalities", description = "Gestión de municipios")
public class MunicipalityController {
    
    private final IMunicipalityService municipalityService;
    
    @Autowired
    public MunicipalityController(IMunicipalityService municipalityService) {
        this.municipalityService = municipalityService;
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener todos los municipios", 
        description = "Obtener todos los municipios disponibles con paginación. Parámetros: page (número de página, default: 0), size (tamaño de página, default: 20), sort (campo de ordenamiento, default: nombre)"
    )
    @ApiResponse(responseCode = "200", description = "Municipios obtenidos exitosamente")
    @Parameter(name = "page", description = "Número de página (0-indexed)", example = "0")
    @Parameter(name = "size", description = "Tamaño de página", example = "20")
    @Parameter(name = "sort", description = "Campo de ordenamiento (ej: nombre,asc)", example = "nombre")
    public ResponseEntity<BaseResponse<Page<MunicipalityResponseDto>>> findAll(
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        Page<MunicipalityResponseDto> municipalities = municipalityService.findAll(pageable);
        BaseResponse<Page<MunicipalityResponseDto>> response = new BaseResponse<>(
                true, municipalities, "Municipios obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PostMapping
    @Operation(summary = "Crear municipio", description = "Crear nuevo municipio")
    @ApiResponse(responseCode = "201", description = "Municipio creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<MunicipalityResponseDto>> create(
            @Valid @RequestBody CreateMunicipalityDto createMunicipalityDto) {
        MunicipalityResponseDto municipality = municipalityService.create(createMunicipalityDto);
        BaseResponse<MunicipalityResponseDto> response = new BaseResponse<>(
                true, municipality, "Municipio creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener municipio por ID", description = "Obtener un municipio por su ID")
    @ApiResponse(responseCode = "200", description = "Municipio obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Municipio no encontrado")
    public ResponseEntity<BaseResponse<MunicipalityResponseDto>> findById(
            @Parameter(description = "ID del municipio") @PathVariable Long id) {
        try {
            MunicipalityResponseDto municipality = municipalityService.findById(id);
            BaseResponse<MunicipalityResponseDto> response = new BaseResponse<>(
                    true, municipality, "Municipio obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<MunicipalityResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar municipio", description = "Actualizar un municipio existente")
    @ApiResponse(responseCode = "200", description = "Municipio actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Municipio no encontrado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<MunicipalityResponseDto>> update(
            @Parameter(description = "ID del municipio") @PathVariable Long id,
            @Valid @RequestBody UpdateMunicipalityDto updateMunicipalityDto) {
        try {
            MunicipalityResponseDto municipality = municipalityService.update(id, updateMunicipalityDto);
            BaseResponse<MunicipalityResponseDto> response = new BaseResponse<>(
                    true, municipality, "Municipio actualizado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<MunicipalityResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar municipio", description = "Eliminar un municipio por su ID")
    @ApiResponse(responseCode = "200", description = "Municipio eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Municipio no encontrado")
    public ResponseEntity<BaseResponse<Void>> deleteById(
            @Parameter(description = "ID del municipio") @PathVariable Long id) {
        try {
            municipalityService.deleteById(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Municipio eliminado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @GetMapping("/state/{stateId}")
    @Operation(summary = "Buscar municipios por estado", description = "Obtener todos los municipios de un estado")
    @ApiResponse(responseCode = "200", description = "Municipios obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<MunicipalityResponseDto>>> findByState(
            @Parameter(description = "ID del estado", required = true)
            @PathVariable Long stateId) {
        List<MunicipalityResponseDto> municipalities = municipalityService.findByState(stateId);
        BaseResponse<List<MunicipalityResponseDto>> response = new BaseResponse<>(
                true, municipalities, "Municipios obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/search")
    @Operation(
        summary = "Buscar municipios", 
        description = "Buscar municipios por nombre o estado con paginación. Parámetros: search (término de búsqueda), page (número de página, default: 0), size (tamaño de página, default: 20), sort (campo de ordenamiento, default: nombre)"
    )
    @ApiResponse(responseCode = "200", description = "Municipios obtenidos exitosamente")
    @Parameter(name = "search", description = "Término de búsqueda (nombre o estado)", example = "tuxtla")
    @Parameter(name = "page", description = "Número de página (0-indexed)", example = "0")
    @Parameter(name = "size", description = "Tamaño de página", example = "20")
    @Parameter(name = "sort", description = "Campo de ordenamiento (ej: nombre,asc)", example = "nombre")
    public ResponseEntity<BaseResponse<Page<MunicipalityResponseDto>>> search(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        Page<MunicipalityResponseDto> municipalities = municipalityService.search(search, pageable);
        BaseResponse<Page<MunicipalityResponseDto>> response = new BaseResponse<>(
                true, municipalities, "Municipios obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

