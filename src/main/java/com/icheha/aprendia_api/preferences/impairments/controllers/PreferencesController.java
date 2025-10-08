package com.icheha.aprendia_api.preferences.impairments.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.StudentImpairmentDetailsResponseDto;
import com.icheha.aprendia_api.preferences.impairments.services.IStudentImpairmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/preferences")
@Tag(name = "Preferences", description = "Endpoints de gestión de preferencias")
public class PreferencesController {
    
    private final IStudentImpairmentService studentImpairmentService;
    
    @Autowired
    public PreferencesController(IStudentImpairmentService studentImpairmentService) {
        this.studentImpairmentService = studentImpairmentService;
    }
    
    @GetMapping("/student-impairments/students/{id}/details")
    @Operation(
        summary = "Obtener preferencias de alumno",
        description = "Obtener preferencias de alumno (discapacidad y ruta de aprendizaje)"
    )
    @ApiResponse(responseCode = "200", description = "Preferencias del estudiante obtenidas")
    @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    public ResponseEntity<BaseResponse<StudentImpairmentDetailsResponseDto>> getStudentPreferencesWithDetails(
            @Parameter(description = "ID del estudiante") @PathVariable Integer id) {
        try {
            StudentImpairmentDetailsResponseDto preferences = studentImpairmentService.getStudentPreferencesWithDetails(id);
            
            BaseResponse<StudentImpairmentDetailsResponseDto> response = new BaseResponse<>(
                    true, preferences, "Preferencias del estudiante obtenidas exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (Exception e) {
            BaseResponse<StudentImpairmentDetailsResponseDto> response = new BaseResponse<>(
                    false, null, "Error al obtener preferencias del estudiante: " + e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
}
