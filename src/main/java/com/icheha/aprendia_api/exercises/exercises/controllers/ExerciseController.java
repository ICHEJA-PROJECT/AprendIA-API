package com.icheha.aprendia_api.exercises.exercises.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.exercises.data.dtos.request.CreateExerciseDto;
import com.icheha.aprendia_api.exercises.exercises.data.dtos.request.UpdateExerciseDto;
import com.icheha.aprendia_api.exercises.exercises.data.dtos.response.ExerciseResponseDto;
import com.icheha.aprendia_api.exercises.exercises.services.IExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
@Tag(name = "4.16. Exercises", description = "API para gestión de ejercicios")
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
        ExerciseResponseDto exercise = exerciseService.createExercise(createExerciseDto);
        BaseResponse<ExerciseResponseDto> response = new BaseResponse<>(
                true, exercise, "Ejercicio creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }

    @GetMapping
    @Operation(summary = "Obtener todos los ejercicios", description = "Retorna una lista de todos los ejercicios disponibles")
    public ResponseEntity<BaseResponse<List<ExerciseResponseDto>>> getAllExercises() {
        List<ExerciseResponseDto> exercises = exerciseService.getAllExercises();
        BaseResponse<List<ExerciseResponseDto>> response = new BaseResponse<>(
                true, exercises, "Ejercicios obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/porcentage")
    @Operation(summary = "Obtener porcentaje por ID y habilidad", description = "Obtiene el porcentaje de un ejercicio específico por ID y habilidad")
    public ResponseEntity<BaseResponse<Double>> getPercentageByIdAndSkill(
            @Parameter(description = "ID del ejercicio") @RequestParam Integer id,
            @Parameter(description = "ID de la habilidad") @RequestParam Integer skillId) {
        Double percentage = exerciseService.getPercentageByIdAndSkill(id, skillId);
        BaseResponse<Double> response = new BaseResponse<>(
                true, percentage, "Porcentaje obtenido exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/pupil/{id}/learning-path")
    @Operation(summary = "Obtener ejercicios propuestos para el estudiante", 
               description = "Obtiene los ejercicios propuestos para el estudiante basados en el algoritmo genético")
    public ResponseEntity<BaseResponse<List<ExerciseResponseDto>>> getExercisesByPupil(
            @Parameter(description = "ID del estudiante") @PathVariable Integer id,
            @Parameter(description = "ID del camino de aprendizaje") @RequestParam Integer learningPathId) {
        List<ExerciseResponseDto> exercises = exerciseService.getExercisesByPupil(id, learningPathId);
        BaseResponse<List<ExerciseResponseDto>> response = new BaseResponse<>(
                true, exercises, "Ejercicios del estudiante obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener ejercicio específico", description = "Obtiene un ejercicio específico por su ID")
    public ResponseEntity<BaseResponse<ExerciseResponseDto>> getExerciseById(
            @Parameter(description = "ID del ejercicio") @PathVariable Integer id) {
        ExerciseResponseDto exercise = exerciseService.getExerciseById(id.longValue());
        BaseResponse<ExerciseResponseDto> response = new BaseResponse<>(
                true, exercise, "Ejercicio obtenido exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/templates/{id}")
    @Operation(summary = "Obtener ejercicio aleatorio por plantilla", 
               description = "Obtiene un ejercicio aleatorio buscando por el ID de la plantilla")
    public ResponseEntity<BaseResponse<ExerciseResponseDto>> getRandomExerciseByTemplate(
            @Parameter(description = "ID de la plantilla") @PathVariable Integer id) {
        ExerciseResponseDto exercise = exerciseService.getRandomExerciseByTemplate(id);
        BaseResponse<ExerciseResponseDto> response = new BaseResponse<>(
                true, exercise, "Ejercicio aleatorio obtenido exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}/percentages")
    @Operation(summary = "Obtener todos los porcentajes de un ejercicio", 
               description = "Obtiene todos los porcentajes asociados a un ejercicio por su ID")
    public ResponseEntity<BaseResponse<List<Double>>> getPorcentages(
            @Parameter(description = "ID del ejercicio") @PathVariable Integer id) {
        List<Double> percentages = exerciseService.getPorcentages(id);
        BaseResponse<List<Double>> response = new BaseResponse<>(
                true, percentages, "Porcentajes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/ids")
    @Operation(summary = "Obtener ejercicios por IDs", 
               description = "Obtiene múltiples ejercicios por sus IDs")
    public ResponseEntity<BaseResponse<List<ExerciseResponseDto>>> findByIds(
            @Parameter(description = "Lista de IDs de ejercicios") @RequestParam List<Integer> ids) {
        List<ExerciseResponseDto> exercises = exerciseService.findByIds(ids);
        BaseResponse<List<ExerciseResponseDto>> response = new BaseResponse<>(
                true, exercises, "Ejercicios obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/templates/{templateId}/all")
    @Operation(summary = "Obtener todos los ejercicios por plantilla", 
               description = "Obtiene todos los ejercicios de un template específico")
    public ResponseEntity<BaseResponse<List<ExerciseResponseDto>>> getExercisesByTemplateId(
            @Parameter(description = "ID de la plantilla") @PathVariable Long templateId) {
        List<ExerciseResponseDto> exercises = exerciseService.getExercisesByTemplateId(templateId);
        BaseResponse<List<ExerciseResponseDto>> response = new BaseResponse<>(
                true, exercises, "Ejercicios obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar ejercicio", description = "Actualizar un ejercicio existente")
    public ResponseEntity<BaseResponse<ExerciseResponseDto>> update(
            @Parameter(description = "ID del ejercicio") @PathVariable Long id,
            @RequestBody UpdateExerciseDto updateExerciseDto) {
        ExerciseResponseDto updated = exerciseService.update(id, updateExerciseDto);
        BaseResponse<ExerciseResponseDto> response = new BaseResponse<>(
                true, updated, "Ejercicio actualizado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar ejercicio", description = "Eliminar un ejercicio del sistema")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del ejercicio") @PathVariable Long id) {
        exerciseService.delete(id);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Ejercicio eliminado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}


