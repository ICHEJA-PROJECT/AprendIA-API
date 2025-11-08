package com.icheha.aprendia_api.preferences.occupation.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.CreateStudentOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.response.StudentOccupationResponseDto;
import com.icheha.aprendia_api.preferences.occupation.services.IStudentOccupationService;
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
@RequestMapping("/student-occupations")
@Tag(name = "3.07. Student Occupation Management", description = "Endpoints para gestión de relaciones estudiante-ocupación")
public class StudentOccupationController {

    @Autowired
    private IStudentOccupationService studentOccupationService;

    @PostMapping
    @Operation(summary = "Crear relación estudiante-ocupación", description = "Crea una nueva relación estudiante-ocupación")
    @ApiResponse(responseCode = "201", description = "Relación creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos o relación ya existe")
    public ResponseEntity<BaseResponse<StudentOccupationResponseDto>> create(@Valid @RequestBody CreateStudentOccupationDto createDto) {
        try {
            StudentOccupationResponseDto response = studentOccupationService.create(createDto);
            return new BaseResponse<>(true, response, "Relación creada exitosamente", HttpStatus.CREATED).buildResponseEntity();
        } catch (IllegalArgumentException e) {
            return new BaseResponse<StudentOccupationResponseDto>(false, null, e.getMessage(), HttpStatus.BAD_REQUEST).buildResponseEntity();
        }
    }

    @GetMapping("/student/{studentId}/occupation-ids")
    @Operation(summary = "Obtener IDs de ocupaciones por ID de estudiante", description = "Retorna los IDs de las ocupaciones asociadas a un estudiante")
    @ApiResponse(responseCode = "200", description = "IDs de ocupaciones obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<Long>>> findByStudentOnlyIds(
            @Parameter(description = "ID del estudiante") @PathVariable Long studentId) {
        List<Long> occupationIds = studentOccupationService.findByStudentOnlyIds(studentId);
        return new BaseResponse<>(true, occupationIds, "IDs de ocupaciones obtenidos exitosamente", HttpStatus.OK).buildResponseEntity();
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Obtener relación estudiante-ocupación por ID de estudiante", description = "Retorna la relación de un estudiante específico")
    @ApiResponse(responseCode = "200", description = "Relación obtenida exitosamente")
    @ApiResponse(responseCode = "404", description = "Relación no encontrada")
    public ResponseEntity<BaseResponse<StudentOccupationResponseDto>> findByStudent(
            @Parameter(description = "ID del estudiante") @PathVariable Long studentId) {
        try {
            StudentOccupationResponseDto response = studentOccupationService.findByStudent(studentId);
            return new BaseResponse<>(true, response, "Relación obtenida exitosamente", HttpStatus.OK).buildResponseEntity();
        } catch (IllegalArgumentException e) {
            return new BaseResponse<StudentOccupationResponseDto>(false, null, e.getMessage(), HttpStatus.NOT_FOUND).buildResponseEntity();
        }
    }

    @GetMapping("/occupation/{occupationId}/student-ids")
    @Operation(summary = "Obtener IDs de estudiantes por ID de ocupación", description = "Retorna los IDs de los estudiantes asociados a una ocupación")
    @ApiResponse(responseCode = "200", description = "IDs de estudiantes obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<Long>>> findByOccupationOnlyIds(
            @Parameter(description = "ID de la ocupación") @PathVariable Long occupationId) {
        List<Long> studentIds = studentOccupationService.findByOccupationOnlyIds(occupationId);
        return new BaseResponse<>(true, studentIds, "IDs de estudiantes obtenidos exitosamente", HttpStatus.OK).buildResponseEntity();
    }
}

