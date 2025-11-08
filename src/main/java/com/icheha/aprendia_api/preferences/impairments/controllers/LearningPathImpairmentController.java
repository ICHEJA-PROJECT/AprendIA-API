package com.icheha.aprendia_api.preferences.impairments.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.CreateLearningPathImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.LearningPathImpairmentResponseDto;
import com.icheha.aprendia_api.preferences.impairments.services.ILearningPathImpairmentService;
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
@RequestMapping("/learning-path-impairments")
@Tag(name = "3.03. Learning Path Impairment Management", description = "Endpoints para gestión de relaciones ruta de aprendizaje-discapacidad")
public class LearningPathImpairmentController {

    @Autowired
    private ILearningPathImpairmentService learningPathImpairmentService;

    @PostMapping
    @Operation(summary = "Crear relación ruta de aprendizaje-discapacidad", description = "Crea una nueva relación ruta de aprendizaje-discapacidad")
    @ApiResponse(responseCode = "201", description = "Relación creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos o relación ya existe")
    public ResponseEntity<BaseResponse<LearningPathImpairmentResponseDto>> create(@Valid @RequestBody CreateLearningPathImpairmentDto createDto) {
        try {
            LearningPathImpairmentResponseDto response = learningPathImpairmentService.create(createDto);
            return new BaseResponse<>(true, response, "Relación creada exitosamente", HttpStatus.CREATED).buildResponseEntity();
        } catch (IllegalArgumentException e) {
            return new BaseResponse<LearningPathImpairmentResponseDto>(false, null, e.getMessage(), HttpStatus.BAD_REQUEST).buildResponseEntity();
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todas las relaciones ruta de aprendizaje-discapacidad", description = "Retorna una lista de todas las relaciones")
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<LearningPathImpairmentResponseDto>>> findAll() {
        List<LearningPathImpairmentResponseDto> relations = learningPathImpairmentService.findAll();
        return new BaseResponse<>(true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK).buildResponseEntity();
    }

    @GetMapping("/learning-path/{learningPathId}")
    @Operation(summary = "Obtener relaciones por ruta de aprendizaje", description = "Retorna las relaciones de una ruta de aprendizaje específica")
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<LearningPathImpairmentResponseDto>>> findByLearningPath(
            @Parameter(description = "ID de la ruta de aprendizaje") @PathVariable Long learningPathId) {
        List<LearningPathImpairmentResponseDto> relations = learningPathImpairmentService.findByLearningPath(learningPathId);
        return new BaseResponse<>(true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK).buildResponseEntity();
    }

    @GetMapping("/impairment/{impairmentId}")
    @Operation(summary = "Obtener relaciones por discapacidad", description = "Retorna las relaciones de una discapacidad específica")
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<LearningPathImpairmentResponseDto>>> findByImpairment(
            @Parameter(description = "ID de la discapacidad") @PathVariable Long impairmentId) {
        List<LearningPathImpairmentResponseDto> relations = learningPathImpairmentService.findByImpairment(impairmentId);
        return new BaseResponse<>(true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK).buildResponseEntity();
    }
}

