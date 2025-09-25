package com.icheha.aprendia_api.exercises.exercises.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.exercises.data.dtos.request.CreateExerciseDto;
import com.icheha.aprendia_api.exercises.exercises.data.dtos.response.ExerciseResponseDto;
import com.icheha.aprendia_api.exercises.exercises.services.IExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@Tag(name = "Exercises", description = "API para gestión de ejercicios")
public class ExerciseController {

    private final IExerciseService exerciseService;

    @Autowired
    public ExerciseController(IExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo ejercicio", description = "Crea un nuevo ejercicio en el sistema")
    public ResponseEntity<BaseResponse<ExerciseResponseDto>> createExercise(
            @RequestBody CreateExerciseDto createExerciseDto) {
        try {
            ExerciseResponseDto exercise = exerciseService.createExercise(createExerciseDto);
            BaseResponse<ExerciseResponseDto> response = new BaseResponse<>(
                    true, exercise, "Ejercicio creado exitosamente", HttpStatus.CREATED);
            return response.buildResponseEntity();
        } catch (Exception e) {
            BaseResponse<ExerciseResponseDto> response = new BaseResponse<>(
                    false, null, "Error al crear ejercicio: " + e.getMessage(), HttpStatus.BAD_REQUEST);
            return response.buildResponseEntity();
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todos los ejercicios", description = "Retorna una lista de todos los ejercicios disponibles")
    public ResponseEntity<BaseResponse<List<ExerciseResponseDto>>> getAllExercises() {
        try {
            List<ExerciseResponseDto> exercises = exerciseService.getAllExercises();
            BaseResponse<List<ExerciseResponseDto>> response = new BaseResponse<>(
                    true, exercises, "Ejercicios obtenidos exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (Exception e) {
            BaseResponse<List<ExerciseResponseDto>> response = new BaseResponse<>(
                    false, null, "Error al obtener ejercicios: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return response.buildResponseEntity();
        }
    }

    @GetMapping("/porcentage")
    @Operation(summary = "Obtener porcentaje por ID y habilidad", description = "Obtiene el porcentaje de un ejercicio específico por ID y habilidad")
    public ResponseEntity<BaseResponse<Double>> getPercentageByIdAndSkill(
            @Parameter(description = "ID del ejercicio") @RequestParam Integer id,
            @Parameter(description = "ID de la habilidad") @RequestParam Integer skillId) {
        try {
            Double percentage = exerciseService.getPercentageByIdAndSkill(id, skillId);
            BaseResponse<Double> response = new BaseResponse<>(
                    true, percentage, "Porcentaje obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (Exception e) {
            BaseResponse<Double> response = new BaseResponse<>(
                    false, null, "Error al obtener porcentaje: " + e.getMessage(), HttpStatus.BAD_REQUEST);
            return response.buildResponseEntity();
        }
    }

    @GetMapping("/pupil/{id}/learning-path")
    @Operation(summary = "Obtener ejercicios propuestos para el estudiante", 
               description = "Obtiene los ejercicios propuestos para el estudiante basados en el algoritmo genético")
    public ResponseEntity<BaseResponse<List<ExerciseResponseDto>>> getExercisesByPupil(
            @Parameter(description = "ID del estudiante") @PathVariable Integer id,
            @Parameter(description = "ID del camino de aprendizaje") @RequestParam Integer learningPathId) {
        try {
            List<ExerciseResponseDto> exercises = exerciseService.getExercisesByPupil(id, learningPathId);
            BaseResponse<List<ExerciseResponseDto>> response = new BaseResponse<>(
                    true, exercises, "Ejercicios del estudiante obtenidos exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (Exception e) {
            BaseResponse<List<ExerciseResponseDto>> response = new BaseResponse<>(
                    false, null, "Error al obtener ejercicios del estudiante: " + e.getMessage(), HttpStatus.BAD_REQUEST);
            return response.buildResponseEntity();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener ejercicio específico", description = "Obtiene un ejercicio específico por su ID")
    public ResponseEntity<BaseResponse<ExerciseResponseDto>> getExerciseById(
            @Parameter(description = "ID del ejercicio") @PathVariable Integer id) {
        try {
            ExerciseResponseDto exercise = exerciseService.getExerciseById(id);
            BaseResponse<ExerciseResponseDto> response = new BaseResponse<>(
                    true, exercise, "Ejercicio obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (Exception e) {
            BaseResponse<ExerciseResponseDto> response = new BaseResponse<>(
                    false, null, "Error al obtener ejercicio: " + e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }

    @GetMapping("/templates/{id}")
    @Operation(summary = "Obtener ejercicio aleatorio por plantilla", 
               description = "Obtiene un ejercicio aleatorio buscando por el ID de la plantilla")
    public ResponseEntity<BaseResponse<ExerciseResponseDto>> getRandomExerciseByTemplate(
            @Parameter(description = "ID de la plantilla") @PathVariable Integer id) {
        try {
            ExerciseResponseDto exercise = exerciseService.getRandomExerciseByTemplate(id);
            BaseResponse<ExerciseResponseDto> response = new BaseResponse<>(
                    true, exercise, "Ejercicio aleatorio obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (Exception e) {
            BaseResponse<ExerciseResponseDto> response = new BaseResponse<>(
                    false, null, "Error al obtener ejercicio aleatorio: " + e.getMessage(), HttpStatus.BAD_REQUEST);
            return response.buildResponseEntity();
        }
    }
}


