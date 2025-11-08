package com.icheha.aprendia_api.preferences.words.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.preferences.words.data.dtos.request.CreateWordOccupationDto;
import com.icheha.aprendia_api.preferences.words.data.dtos.response.WordOccupationResponseDto;
import com.icheha.aprendia_api.preferences.words.services.IWordOccupationService;
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
@RequestMapping("/word-occupations")
@Tag(name = "3.14. Word Occupation Management", description = "Endpoints para gestión de relaciones palabra-ocupación")
public class WordOccupationController {

    @Autowired
    private IWordOccupationService wordOccupationService;

    @PostMapping
    @Operation(summary = "Crear relación palabra-ocupación", description = "Crea una nueva relación palabra-ocupación")
    @ApiResponse(responseCode = "201", description = "Relación creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos o relación ya existe")
    public ResponseEntity<BaseResponse<WordOccupationResponseDto>> create(@Valid @RequestBody CreateWordOccupationDto createDto) {
        try {
            WordOccupationResponseDto response = wordOccupationService.create(createDto);
            return new BaseResponse<>(true, response, "Relación creada exitosamente", HttpStatus.CREATED).buildResponseEntity();
        } catch (IllegalArgumentException e) {
            return new BaseResponse<WordOccupationResponseDto>(false, null, e.getMessage(), HttpStatus.BAD_REQUEST).buildResponseEntity();
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todas las relaciones palabra-ocupación", description = "Retorna una lista de todas las relaciones palabra-ocupación")
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<WordOccupationResponseDto>>> findAll() {
        List<WordOccupationResponseDto> relations = wordOccupationService.findAll();
        return new BaseResponse<>(true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK).buildResponseEntity();
    }
}

