package com.icheha.aprendia_api.preferences.impairments.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.CreateStudentImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.StudentImpairmentResponseDto;
import com.icheha.aprendia_api.preferences.impairments.services.IStudentImpairmentService;
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
@RequestMapping("/student-impairments")
@Tag(name = "3.02. Student Impairments", description = "Endpoints de gestión de relaciones estudiante-discapacidad")
public class StudentImpairmentController {
    
    @Autowired
    private IStudentImpairmentService studentImpairmentService;
    
    @PostMapping
    @Operation(
        summary = "Crear relación estudiante-discapacidad",
        description = "Crear una nueva relación entre un estudiante y una discapacidad"
    )
    @ApiResponse(responseCode = "201", description = "Relación creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<StudentImpairmentResponseDto>> create(
            @Valid @RequestBody CreateStudentImpairmentDto createDto) {
        StudentImpairmentResponseDto response = studentImpairmentService.create(createDto);
        BaseResponse<StudentImpairmentResponseDto> baseResponse = new BaseResponse<>(
                true, response, "Relación estudiante-discapacidad creada exitosamente", HttpStatus.CREATED);
        return baseResponse.buildResponseEntity();
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener todas las relaciones estudiante-discapacidad",
        description = "Retorna una lista de todas las relaciones estudiante-discapacidad"
    )
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<StudentImpairmentResponseDto>>> findAll() {
        List<StudentImpairmentResponseDto> relations = studentImpairmentService.findAll();
        BaseResponse<List<StudentImpairmentResponseDto>> response = new BaseResponse<>(
                true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/impairment/{impairmentId}/student-ids")
    @Operation(
        summary = "Obtener IDs de estudiantes por discapacidad",
        description = "Obtiene solo los IDs de los estudiantes que tienen una discapacidad específica"
    )
    @ApiResponse(responseCode = "200", description = "IDs obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<Long>>> findByImpairmentOnlyIds(
            @Parameter(description = "ID de la discapacidad") @PathVariable Long impairmentId) {
        List<Long> studentIds = studentImpairmentService.findByImpairmentOnlyIds(impairmentId);
        BaseResponse<List<Long>> response = new BaseResponse<>(
                true, studentIds, "IDs de estudiantes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/student/{studentId}/impairment-ids")
    @Operation(
        summary = "Obtener IDs de discapacidades por estudiante",
        description = "Obtiene solo los IDs de las discapacidades de un estudiante específico"
    )
    @ApiResponse(responseCode = "200", description = "IDs obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<Long>>> findByStudentOnlyIds(
            @Parameter(description = "ID del estudiante") @PathVariable Long studentId) {
        List<Long> impairmentIds = studentImpairmentService.findByStudentOnlyIds(studentId);
        BaseResponse<List<Long>> response = new BaseResponse<>(
                true, impairmentIds, "IDs de discapacidades obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/student/{studentId}")
    @Operation(
        summary = "Obtener discapacidades por estudiante",
        description = "Obtiene todas las discapacidades de un estudiante específico"
    )
    @ApiResponse(responseCode = "200", description = "Discapacidades obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<StudentImpairmentResponseDto>>> findByStudent(
            @Parameter(description = "ID del estudiante") @PathVariable Long studentId) {
        List<StudentImpairmentResponseDto> relations = studentImpairmentService.findByStudent(studentId);
        BaseResponse<List<StudentImpairmentResponseDto>> response = new BaseResponse<>(
                true, relations, "Discapacidades del estudiante obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

