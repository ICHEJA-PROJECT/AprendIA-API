package com.icheha.aprendia_api.records.pupilExcerise.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreatePupilExerciseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.UpdatePupilExerciseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.PupilExerciseResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.services.IPupilExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pupil-exercises")
@Tag(name = "5.1. Pupil Exercises", description = "Endpoints de gestión de ejercicios de alumnos")
public class PupilExerciseController {
    
    private final IPupilExerciseService pupilExerciseService;
    
    @Autowired
    public PupilExerciseController(IPupilExerciseService pupilExerciseService) {
        this.pupilExerciseService = pupilExerciseService;
    }
    
    @PostMapping
    @Operation(
        summary = "Registrar ejercicio completado por alumno",
        description = "Registrar ejercicio completado por alumno"
    )
    @ApiResponse(responseCode = "201", description = "Registro creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<BaseResponse<PupilExerciseResponseDto>> createPupilExercise(
            @RequestBody CreatePupilExerciseDto createPupilExerciseDto) {
        PupilExerciseResponseDto pupilExercise = pupilExerciseService.createPupilExercise(createPupilExerciseDto);
        BaseResponse<PupilExerciseResponseDto> response = new BaseResponse<>(
                true, pupilExercise, "Registro de ejercicio creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/pupils/{id}/ids")
    @Operation(
        summary = "Obtener IDs de ejercicios por alumno",
        description = "Obtener IDs de ejercicios por alumno"
    )
    @ApiResponse(responseCode = "200", description = "IDs obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<Integer>>> getExerciseIdsByPupil(
            @Parameter(description = "ID del alumno") @PathVariable Integer id) {
        List<Integer> exerciseIds = pupilExerciseService.getExerciseIdsByPupil(id);
        BaseResponse<List<Integer>> response = new BaseResponse<>(
                true, exerciseIds, "IDs de ejercicios obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/pupils/{id}/exercises/assigned")
    @Operation(
        summary = "Obtener ejercicios asignados por el profesor",
        description = "Obtener ejercicios asignados por el profesor"
    )
    @ApiResponse(responseCode = "200", description = "Ejercicios obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.ExerciseResponseDto>>> getAssignedExercisesByPupil(
            @Parameter(description = "ID del alumno") @PathVariable Integer id) {
        List<com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.ExerciseResponseDto> exercises = pupilExerciseService.getAssignedExercisesByPupil(id);
        BaseResponse<List<com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.ExerciseResponseDto>> response = new BaseResponse<>(
                true, exercises, "Ejercicios asignados obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/pupils/{id}")
    @Operation(
        summary = "Obtener ejercicios por alumno",
        description = "Obtener ejercicios por alumno"
    )
    @ApiResponse(responseCode = "200", description = "Ejercicios obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<PupilExerciseResponseDto>>> getExercisesByPupil(
            @Parameter(description = "ID del alumno") @PathVariable Integer id) {
        List<PupilExerciseResponseDto> pupilExercises = pupilExerciseService.getExercisesByPupil(id);
        BaseResponse<List<PupilExerciseResponseDto>> response = new BaseResponse<>(
                true, pupilExercises, "Ejercicios del alumno obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/exercises/{id}")
    @Operation(
        summary = "Obtener ejercicios por ID de ejercicio",
        description = "Obtener ejercicios por ID de ejercicio"
    )
    @ApiResponse(responseCode = "200", description = "Ejercicios obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<PupilExerciseResponseDto>>> getExercisesByExerciseId(
            @Parameter(description = "ID del ejercicio") @PathVariable Integer id) {
        List<PupilExerciseResponseDto> pupilExercises = pupilExerciseService.getExercisesByExerciseId(id);
        BaseResponse<List<PupilExerciseResponseDto>> response = new BaseResponse<>(
                true, pupilExercises, "Ejercicios obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PatchMapping("/{id}")
    @Operation(
        summary = "Actualizar ejercicio asignado por profesor",
        description = "Actualizar ejercicio asignado por profesor"
    )
    @ApiResponse(responseCode = "200", description = "Ejercicio actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Ejercicio no encontrado")
    public ResponseEntity<BaseResponse<PupilExerciseResponseDto>> updatePupilExercise(
            @Parameter(description = "ID de la relación pupil-exercise") @PathVariable Integer id,
            @RequestBody UpdatePupilExerciseDto updatePupilExerciseDto) {
        PupilExerciseResponseDto pupilExercise = pupilExerciseService.updatePupilExercise(id, updatePupilExerciseDto);
        BaseResponse<PupilExerciseResponseDto> response = new BaseResponse<>(
                true, pupilExercise, "Ejercicio actualizado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}
