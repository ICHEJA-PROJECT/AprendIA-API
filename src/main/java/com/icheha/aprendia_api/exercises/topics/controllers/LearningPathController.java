package com.icheha.aprendia_api.exercises.topics.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateLearningPathDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateLearningPathDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.LearningPathResponseDto;
import com.icheha.aprendia_api.exercises.topics.services.ILearningPathService;
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
@RequestMapping("/learning-paths")
@Tag(name = "4.03. Learning Paths", description = "API para gestión de rutas de aprendizaje")
public class LearningPathController {

    private final ILearningPathService learningPathService;

    @Autowired
    public LearningPathController(ILearningPathService learningPathService) {
        this.learningPathService = learningPathService;
    }

    @PostMapping
    @Operation(
        summary = "Crear ruta de aprendizaje",
        description = "Crear nueva ruta de aprendizaje"
    )
    @ApiResponse(responseCode = "201", description = "Ruta de aprendizaje creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<LearningPathResponseDto>> create(
            @Valid @RequestBody CreateLearningPathDto createLearningPathDto) {
        LearningPathResponseDto learningPath = learningPathService.create(createLearningPathDto);
        BaseResponse<LearningPathResponseDto> response = new BaseResponse<>(
                true, learningPath, "Ruta de aprendizaje creada exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }

    @GetMapping
    @Operation(
        summary = "Obtener todas las rutas de aprendizaje",
        description = "Obtener todas las rutas de aprendizaje"
    )
    @ApiResponse(responseCode = "200", description = "Rutas de aprendizaje obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<LearningPathResponseDto>>> findAll() {
        List<LearningPathResponseDto> learningPaths = learningPathService.findAll();
        BaseResponse<List<LearningPathResponseDto>> response = new BaseResponse<>(
                true, learningPaths, "Rutas de aprendizaje obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener ruta de aprendizaje por ID",
        description = "Obtener una ruta de aprendizaje por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Ruta de aprendizaje obtenida exitosamente")
    @ApiResponse(responseCode = "404", description = "Ruta de aprendizaje no encontrada")
    public ResponseEntity<BaseResponse<LearningPathResponseDto>> findById(
            @Parameter(description = "ID de la ruta de aprendizaje") @PathVariable Long id) {
        try {
            LearningPathResponseDto learningPath = learningPathService.findById(id);
            BaseResponse<LearningPathResponseDto> response = new BaseResponse<>(
                    true, learningPath, "Ruta de aprendizaje obtenida exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<LearningPathResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar ruta de aprendizaje",
        description = "Actualizar una ruta de aprendizaje existente"
    )
    @ApiResponse(responseCode = "200", description = "Ruta de aprendizaje actualizada exitosamente")
    @ApiResponse(responseCode = "404", description = "Ruta de aprendizaje no encontrada")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<LearningPathResponseDto>> update(
            @Parameter(description = "ID de la ruta de aprendizaje") @PathVariable Long id,
            @Valid @RequestBody UpdateLearningPathDto updateLearningPathDto) {
        try {
            LearningPathResponseDto learningPath = learningPathService.update(id, updateLearningPathDto);
            BaseResponse<LearningPathResponseDto> response = new BaseResponse<>(
                    true, learningPath, "Ruta de aprendizaje actualizada exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<LearningPathResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar ruta de aprendizaje",
        description = "Eliminar una ruta de aprendizaje por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Ruta de aprendizaje eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Ruta de aprendizaje no encontrada")
    public ResponseEntity<BaseResponse<Void>> deleteById(
            @Parameter(description = "ID de la ruta de aprendizaje") @PathVariable Long id) {
        try {
            learningPathService.deleteById(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Ruta de aprendizaje eliminada exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }

    @GetMapping("/perfil/{idPerfil}")
    @Operation(
        summary = "Obtener rutas de aprendizaje por perfil",
        description = "Obtener todas las rutas de aprendizaje asociadas a un perfil"
    )
    @ApiResponse(responseCode = "200", description = "Rutas de aprendizaje obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<LearningPathResponseDto>>> findByIdPerfil(
            @Parameter(description = "ID del perfil") @PathVariable Long idPerfil) {
        List<LearningPathResponseDto> learningPaths = learningPathService.findByIdPerfil(idPerfil);
        BaseResponse<List<LearningPathResponseDto>> response = new BaseResponse<>(
                true, learningPaths, "Rutas de aprendizaje obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/metodologia/{idMetodologia}")
    @Operation(
        summary = "Obtener rutas de aprendizaje por metodología",
        description = "Obtener todas las rutas de aprendizaje asociadas a una metodología"
    )
    @ApiResponse(responseCode = "200", description = "Rutas de aprendizaje obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<LearningPathResponseDto>>> findByIdMetodologia(
            @Parameter(description = "ID de la metodología") @PathVariable Long idMetodologia) {
        List<LearningPathResponseDto> learningPaths = learningPathService.findByIdMetodologia(idMetodologia);
        BaseResponse<List<LearningPathResponseDto>> response = new BaseResponse<>(
                true, learningPaths, "Rutas de aprendizaje obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/search")
    @Operation(
        summary = "Buscar rutas de aprendizaje por nombre",
        description = "Buscar rutas de aprendizaje que contengan el nombre especificado"
    )
    @ApiResponse(responseCode = "200", description = "Rutas de aprendizaje obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<LearningPathResponseDto>>> findByNombreContaining(
            @Parameter(description = "Nombre a buscar") @RequestParam String nombre) {
        List<LearningPathResponseDto> learningPaths = learningPathService.findByNombreContaining(nombre);
        BaseResponse<List<LearningPathResponseDto>> response = new BaseResponse<>(
                true, learningPaths, "Rutas de aprendizaje obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

