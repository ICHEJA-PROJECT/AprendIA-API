package com.icheha.aprendia_api.users.person.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.person.data.dtos.request.CreateZipcodeDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateZipcodeDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.ZipcodeResponseDto;
import com.icheha.aprendia_api.users.person.services.IZipcodeService;
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
@RequestMapping("/zipcodes")
@Tag(name = "2.14. Zipcodes", description = "API para gestión de códigos postales")
public class ZipcodeController {
    
    private final IZipcodeService zipcodeService;
    
    @Autowired
    public ZipcodeController(IZipcodeService zipcodeService) {
        this.zipcodeService = zipcodeService;
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener todos los códigos postales", 
        description = "Obtener todos los códigos postales disponibles con paginación. Parámetros: page (número de página, default: 0), size (tamaño de página, default: 20), sort (campo de ordenamiento, default: codigo)"
    )
    @ApiResponse(responseCode = "200", description = "Códigos postales obtenidos exitosamente")
    @Parameter(name = "page", description = "Número de página (0-indexed)", example = "0")
    @Parameter(name = "size", description = "Tamaño de página", example = "20")
    @Parameter(name = "sort", description = "Campo de ordenamiento (ej: codigo,asc)", example = "codigo")
    public ResponseEntity<BaseResponse<Page<ZipcodeResponseDto>>> findAll(
            @PageableDefault(size = 20, sort = "codigo") Pageable pageable) {
        Page<ZipcodeResponseDto> zipcodes = zipcodeService.findAll(pageable);
        BaseResponse<Page<ZipcodeResponseDto>> response = new BaseResponse<>(
                true, zipcodes, "Códigos postales obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/search")
    @Operation(
        summary = "Buscar códigos postales", 
        description = "Buscar códigos postales por código con paginación. Parámetros: search (término de búsqueda), page (número de página, default: 0), size (tamaño de página, default: 20), sort (campo de ordenamiento, default: codigo)"
    )
    @ApiResponse(responseCode = "200", description = "Códigos postales obtenidos exitosamente")
    @Parameter(name = "search", description = "Término de búsqueda (código postal)", example = "29000")
    @Parameter(name = "page", description = "Número de página (0-indexed)", example = "0")
    @Parameter(name = "size", description = "Tamaño de página", example = "20")
    @Parameter(name = "sort", description = "Campo de ordenamiento (ej: codigo,asc)", example = "codigo")
    public ResponseEntity<BaseResponse<Page<ZipcodeResponseDto>>> search(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 20, sort = "codigo") Pageable pageable) {
        Page<ZipcodeResponseDto> zipcodes = zipcodeService.search(search, pageable);
        BaseResponse<Page<ZipcodeResponseDto>> response = new BaseResponse<>(
                true, zipcodes, "Códigos postales obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PostMapping
    @Operation(summary = "Crear código postal", description = "Crear nuevo código postal")
    @ApiResponse(responseCode = "201", description = "Código postal creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<ZipcodeResponseDto>> create(
            @Valid @RequestBody CreateZipcodeDto createZipcodeDto) {
        ZipcodeResponseDto zipcode = zipcodeService.create(createZipcodeDto);
        BaseResponse<ZipcodeResponseDto> response = new BaseResponse<>(
                true, zipcode, "Código postal creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener código postal por ID", description = "Obtener un código postal por su ID")
    @ApiResponse(responseCode = "200", description = "Código postal obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Código postal no encontrado")
    public ResponseEntity<BaseResponse<ZipcodeResponseDto>> findById(
            @Parameter(description = "ID del código postal") @PathVariable Long id) {
        try {
            ZipcodeResponseDto zipcode = zipcodeService.findById(id);
            BaseResponse<ZipcodeResponseDto> response = new BaseResponse<>(
                    true, zipcode, "Código postal obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<ZipcodeResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar código postal", description = "Actualizar un código postal existente")
    @ApiResponse(responseCode = "200", description = "Código postal actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Código postal no encontrado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<ZipcodeResponseDto>> update(
            @Parameter(description = "ID del código postal") @PathVariable Long id,
            @Valid @RequestBody UpdateZipcodeDto updateZipcodeDto) {
        try {
            ZipcodeResponseDto zipcode = zipcodeService.update(id, updateZipcodeDto);
            BaseResponse<ZipcodeResponseDto> response = new BaseResponse<>(
                    true, zipcode, "Código postal actualizado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<ZipcodeResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar código postal", description = "Eliminar un código postal por su ID")
    @ApiResponse(responseCode = "200", description = "Código postal eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Código postal no encontrado")
    public ResponseEntity<BaseResponse<Void>> deleteById(
            @Parameter(description = "ID del código postal") @PathVariable Long id) {
        try {
            zipcodeService.deleteById(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Código postal eliminado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
}

