package com.icheha.aprendia_api.preferences.words.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.preferences.words.data.dtos.request.CreateWordMeaningDto;
import com.icheha.aprendia_api.preferences.words.data.dtos.response.WordMeaningResponseDto;
import com.icheha.aprendia_api.preferences.words.services.IWordMeaningService;
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
@RequestMapping("/word-meanings")
@Tag(name = "3.13. Word Meanings", description = "Endpoints de gestión de significados de palabras")
public class WordMeaningController {

    @Autowired
    private IWordMeaningService wordMeaningService;

    @PostMapping
    @Operation(summary = "Crear significado de palabra", description = "Crea un nuevo significado para una palabra")
    @ApiResponse(responseCode = "201", description = "Significado creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos o palabra no encontrada")
    public ResponseEntity<BaseResponse<WordMeaningResponseDto>> create(@Valid @RequestBody CreateWordMeaningDto createDto) {
        try {
            WordMeaningResponseDto response = wordMeaningService.create(createDto);
            return new BaseResponse<>(true, response, "Significado creado exitosamente", HttpStatus.CREATED).buildResponseEntity();
        } catch (IllegalArgumentException e) {
            return new BaseResponse<WordMeaningResponseDto>(false, null, e.getMessage(), HttpStatus.BAD_REQUEST).buildResponseEntity();
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todos los significados", description = "Retorna una lista de todos los significados de palabras")
    @ApiResponse(responseCode = "200", description = "Significados obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<WordMeaningResponseDto>>> findAll() {
        List<WordMeaningResponseDto> meanings = wordMeaningService.findAll();
        return new BaseResponse<>(true, meanings, "Significados obtenidos exitosamente", HttpStatus.OK).buildResponseEntity();
    }
}

