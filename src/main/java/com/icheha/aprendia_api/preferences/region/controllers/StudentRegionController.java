package com.icheha.aprendia_api.preferences.region.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.preferences.region.data.dtos.request.CreateStudentRegionDto;
import com.icheha.aprendia_api.preferences.region.data.dtos.response.StudentRegionResponseDto;
import com.icheha.aprendia_api.preferences.region.services.IStudentRegionService;
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
@RequestMapping("/student-regions")
@Tag(name = "3.10. Student Region Management", description = "Endpoints para gestión de relaciones estudiante-región")
public class StudentRegionController {

    @Autowired
    private IStudentRegionService studentRegionService;

    @PostMapping
    @Operation(summary = "Crear relación estudiante-región", description = "Crea una nueva relación estudiante-región")
    @ApiResponse(responseCode = "201", description = "Relación creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos o relación ya existe")
    public ResponseEntity<BaseResponse<StudentRegionResponseDto>> create(@Valid @RequestBody CreateStudentRegionDto createDto) {
        try {
            StudentRegionResponseDto response = studentRegionService.create(createDto);
            return new BaseResponse<>(true, response, "Relación creada exitosamente", HttpStatus.CREATED).buildResponseEntity();
        } catch (IllegalArgumentException e) {
            return new BaseResponse<StudentRegionResponseDto>(false, null, e.getMessage(), HttpStatus.BAD_REQUEST).buildResponseEntity();
        }
    }

    @GetMapping("/student/{studentId}/region-ids")
    @Operation(summary = "Obtener IDs de regiones por ID de estudiante", description = "Retorna los IDs de las regiones asociadas a un estudiante")
    @ApiResponse(responseCode = "200", description = "IDs de regiones obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<Long>>> findByStudentOnlyIds(
            @Parameter(description = "ID del estudiante") @PathVariable Long studentId) {
        List<Long> regionIds = studentRegionService.findByStudentOnlyIds(studentId);
        return new BaseResponse<>(true, regionIds, "IDs de regiones obtenidos exitosamente", HttpStatus.OK).buildResponseEntity();
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Obtener relaciones estudiante-región por ID de estudiante", description = "Retorna las relaciones de un estudiante específico")
    @ApiResponse(responseCode = "200", description = "Relaciones obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<StudentRegionResponseDto>>> findByStudent(
            @Parameter(description = "ID del estudiante") @PathVariable Long studentId) {
        List<StudentRegionResponseDto> relations = studentRegionService.findByStudent(studentId);
        return new BaseResponse<>(true, relations, "Relaciones obtenidas exitosamente", HttpStatus.OK).buildResponseEntity();
    }

    @GetMapping("/region/{regionId}/student-ids")
    @Operation(summary = "Obtener IDs de estudiantes por ID de región", description = "Retorna los IDs de los estudiantes asociados a una región")
    @ApiResponse(responseCode = "200", description = "IDs de estudiantes obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<Long>>> findByRegionOnlyIds(
            @Parameter(description = "ID de la región") @PathVariable Long regionId) {
        List<Long> studentIds = studentRegionService.findByRegionOnlyIds(regionId);
        return new BaseResponse<>(true, studentIds, "IDs de estudiantes obtenidos exitosamente", HttpStatus.OK).buildResponseEntity();
    }
}

