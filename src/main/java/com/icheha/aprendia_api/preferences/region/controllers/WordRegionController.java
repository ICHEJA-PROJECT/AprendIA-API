package com.icheha.aprendia_api.preferences.region.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.preferences.region.data.dtos.request.CreateWordRegionDto;
import com.icheha.aprendia_api.preferences.region.data.dtos.response.WordRegionResponseDto;
import com.icheha.aprendia_api.preferences.region.services.IWordRegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/word-regions")
@Tag(name = "3.15. Word Region Management", description = "Endpoints para gestión de relaciones palabra-región")
public class WordRegionController {

    @Autowired
    private IWordRegionService wordRegionService;

    @PostMapping
    @Operation(summary = "Crear relación palabra-región", description = "Crea una nueva relación palabra-región")
    @ApiResponse(responseCode = "201", description = "Relación creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos o relación ya existe")
    public ResponseEntity<BaseResponse<WordRegionResponseDto>> create(@Valid @RequestBody CreateWordRegionDto createDto) {
        try {
            WordRegionResponseDto response = wordRegionService.create(createDto);
            return new BaseResponse<>(true, response, "Relación creada exitosamente", HttpStatus.CREATED).buildResponseEntity();
        } catch (IllegalArgumentException e) {
            return new BaseResponse<WordRegionResponseDto>(false, null, e.getMessage(), HttpStatus.BAD_REQUEST).buildResponseEntity();
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todas las relaciones palabra-región", description = "Retorna una lista de todas las relaciones palabra-región")
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<WordRegionResponseDto>>> findAll() {
        List<WordRegionResponseDto> relations = wordRegionService.findAll();
        return new BaseResponse<>(true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK).buildResponseEntity();
    }
}

