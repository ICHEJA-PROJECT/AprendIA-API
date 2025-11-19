package com.icheha.aprendia_api.users.person.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.person.data.dtos.request.CreateStateDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateStateDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.StateResponseDto;
import com.icheha.aprendia_api.users.person.services.IStateService;
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
@RequestMapping("/states")
@Tag(name = "2.12. States", description = "API para gestión de estados")
public class StateController {
    
    private final IStateService stateService;
    
    @Autowired
    public StateController(IStateService stateService) {
        this.stateService = stateService;
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener todos los estados", 
        description = "Obtener todos los estados disponibles con paginación. Parámetros: page (número de página, default: 0), size (tamaño de página, default: 20), sort (campo de ordenamiento, default: nombre)"
    )
    @ApiResponse(responseCode = "200", description = "Estados obtenidos exitosamente")
    @Parameter(name = "page", description = "Número de página (0-indexed)", example = "0")
    @Parameter(name = "size", description = "Tamaño de página", example = "20")
    @Parameter(name = "sort", description = "Campo de ordenamiento (ej: nombre,asc)", example = "nombre")
    public ResponseEntity<BaseResponse<Page<StateResponseDto>>> findAll(
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        Page<StateResponseDto> states = stateService.findAll(pageable);
        BaseResponse<Page<StateResponseDto>> response = new BaseResponse<>(
                true, states, "Estados obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/search")
    @Operation(
        summary = "Buscar estados", 
        description = "Buscar estados por nombre con paginación. Parámetros: search (término de búsqueda), page (número de página, default: 0), size (tamaño de página, default: 20), sort (campo de ordenamiento, default: nombre)"
    )
    @ApiResponse(responseCode = "200", description = "Estados obtenidos exitosamente")
    @Parameter(name = "search", description = "Término de búsqueda (nombre)", example = "chiapas")
    @Parameter(name = "page", description = "Número de página (0-indexed)", example = "0")
    @Parameter(name = "size", description = "Tamaño de página", example = "20")
    @Parameter(name = "sort", description = "Campo de ordenamiento (ej: nombre,asc)", example = "nombre")
    public ResponseEntity<BaseResponse<Page<StateResponseDto>>> search(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        Page<StateResponseDto> states = stateService.search(search, pageable);
        BaseResponse<Page<StateResponseDto>> response = new BaseResponse<>(
                true, states, "Estados obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PostMapping
    @Operation(summary = "Crear estado", description = "Crear nuevo estado")
    @ApiResponse(responseCode = "201", description = "Estado creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<StateResponseDto>> create(
            @Valid @RequestBody CreateStateDto createStateDto) {
        StateResponseDto state = stateService.create(createStateDto);
        BaseResponse<StateResponseDto> response = new BaseResponse<>(
                true, state, "Estado creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener estado por ID", description = "Obtener un estado por su ID")
    @ApiResponse(responseCode = "200", description = "Estado obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Estado no encontrado")
    public ResponseEntity<BaseResponse<StateResponseDto>> findById(
            @Parameter(description = "ID del estado") @PathVariable Long id) {
        try {
            StateResponseDto state = stateService.findById(id);
            BaseResponse<StateResponseDto> response = new BaseResponse<>(
                    true, state, "Estado obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<StateResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar estado", description = "Actualizar un estado existente")
    @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Estado no encontrado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<StateResponseDto>> update(
            @Parameter(description = "ID del estado") @PathVariable Long id,
            @Valid @RequestBody UpdateStateDto updateStateDto) {
        try {
            StateResponseDto state = stateService.update(id, updateStateDto);
            BaseResponse<StateResponseDto> response = new BaseResponse<>(
                    true, state, "Estado actualizado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<StateResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar estado", description = "Eliminar un estado por su ID")
    @ApiResponse(responseCode = "200", description = "Estado eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Estado no encontrado")
    public ResponseEntity<BaseResponse<Void>> deleteById(
            @Parameter(description = "ID del estado") @PathVariable Long id) {
        try {
            stateService.deleteById(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Estado eliminado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
}

