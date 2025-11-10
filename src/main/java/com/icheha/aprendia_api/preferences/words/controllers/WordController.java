package com.icheha.aprendia_api.preferences.words.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.preferences.words.data.dtos.request.CreateWordDto;
import com.icheha.aprendia_api.preferences.words.data.dtos.response.WordResponseDto;
import com.icheha.aprendia_api.preferences.words.services.IWordService;
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
@RequestMapping("/words")
@Tag(name = "3.12. Words", description = "Endpoints de gestión de palabras")
public class WordController {
    
    @Autowired
    private IWordService wordService;
    
    @PostMapping
    @Operation(
        summary = "Crear palabra",
        description = "Crear una nueva palabra en el sistema"
    )
    @ApiResponse(responseCode = "201", description = "Palabra creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<WordResponseDto>> create(
            @Valid @RequestBody CreateWordDto createDto) {
        WordResponseDto response = wordService.create(createDto);
        BaseResponse<WordResponseDto> baseResponse = new BaseResponse<>(
                true, response, "Palabra creada exitosamente", HttpStatus.CREATED);
        return baseResponse.buildResponseEntity();
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener todas las palabras",
        description = "Retorna una lista de todas las palabras disponibles"
    )
    @ApiResponse(responseCode = "200", description = "Palabras obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<WordResponseDto>>> findAll() {
        List<WordResponseDto> words = wordService.findAll();
        BaseResponse<List<WordResponseDto>> response = new BaseResponse<>(
                true, words, "Palabras obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener palabra por ID",
        description = "Obtiene una palabra específica por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Palabra encontrada")
    @ApiResponse(responseCode = "404", description = "Palabra no encontrada")
    public ResponseEntity<BaseResponse<WordResponseDto>> findById(
            @Parameter(description = "ID de la palabra") @PathVariable Long id) {
        WordResponseDto word = wordService.findById(id);
        BaseResponse<WordResponseDto> response = new BaseResponse<>(
                true, word, "Palabra obtenida exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

