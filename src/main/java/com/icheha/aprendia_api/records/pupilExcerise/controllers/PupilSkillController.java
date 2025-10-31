package com.icheha.aprendia_api.records.pupilExcerise.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreatePupilSkillDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.PupilSkillResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.GradeSkillResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.services.IPupilSkillService;
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
@RequestMapping("/pupil-skills")
@Tag(name = "Pupil Skills", description = "Endpoints de gestión de habilidades de alumnos")
public class PupilSkillController {
    
    private final IPupilSkillService pupilSkillService;
    
    @Autowired
    public PupilSkillController(IPupilSkillService pupilSkillService) {
        this.pupilSkillService = pupilSkillService;
    }
    
    @PostMapping
    @Operation(
        summary = "Crear registro de skill de alumno",
        description = "Crear registro de skill de alumno"
    )
    @ApiResponse(responseCode = "201", description = "Registro creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<BaseResponse<PupilSkillResponseDto>> createPupilSkill(
            @RequestBody CreatePupilSkillDto createPupilSkillDto) {
        PupilSkillResponseDto pupilSkill = pupilSkillService.createPupilSkill(createPupilSkillDto);
        BaseResponse<PupilSkillResponseDto> response = new BaseResponse<>(
                true, pupilSkill, "Registro de skill creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }
    
    @PostMapping("/many")
    @Operation(
        summary = "Crear múltiples registros de skills",
        description = "Crear múltiples registros de skills"
    )
    @ApiResponse(responseCode = "201", description = "Registros creados exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<BaseResponse<List<PupilSkillResponseDto>>> createManyPupilSkills(
            @RequestBody List<CreatePupilSkillDto> createPupilSkillDtos) {
        List<PupilSkillResponseDto> pupilSkills = pupilSkillService.createManyPupilSkills(createPupilSkillDtos);
        BaseResponse<List<PupilSkillResponseDto>> response = new BaseResponse<>(
                true, pupilSkills, "Registros de skills creados exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener skills de alumnos",
        description = "Obtener skills de alumnos"
    )
    @ApiResponse(responseCode = "200", description = "Skills obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<PupilSkillResponseDto>>> getAllPupilSkills() {
        List<PupilSkillResponseDto> pupilSkills = pupilSkillService.getAllPupilSkills();
        BaseResponse<List<PupilSkillResponseDto>> response = new BaseResponse<>(
                true, pupilSkills, "Skills de alumnos obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/pupil/{id}")
    @Operation(
        summary = "Obtener skills por alumno",
        description = "Obtener skills por alumno"
    )
    @ApiResponse(responseCode = "200", description = "Skills obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<PupilSkillResponseDto>>> getSkillsByPupilId(
            @Parameter(description = "ID del alumno") @PathVariable Integer id) {
        List<PupilSkillResponseDto> pupilSkills = pupilSkillService.getSkillsByPupilId(id);
        BaseResponse<List<PupilSkillResponseDto>> response = new BaseResponse<>(
                true, pupilSkills, "Skills del alumno obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/grades/skills")
    @Operation(
        summary = "Obtener skills por grados",
        description = "Obtener skills por grados"
    )
    @ApiResponse(responseCode = "200", description = "Skills por grados obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<GradeSkillResponseDto>>> getGradeSkills() {
        List<GradeSkillResponseDto> gradeSkills = pupilSkillService.getGradeSkills();
        BaseResponse<List<GradeSkillResponseDto>> response = new BaseResponse<>(
                true, gradeSkills, "Skills por grados obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}
