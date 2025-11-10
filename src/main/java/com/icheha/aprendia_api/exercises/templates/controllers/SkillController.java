package com.icheha.aprendia_api.exercises.templates.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.GetSkillsByTemplatesDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.UpdateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.SkillResponseDto;
import com.icheha.aprendia_api.exercises.templates.services.ISkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
@Tag(name = "4.11. Skills", description = "API para gestión de habilidades")
public class SkillController {

    private final ISkillService skillService;

    @Autowired
    public SkillController(ISkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    @Operation(summary = "Crear skill", description = "Crear nueva skill")
    public ResponseEntity<BaseResponse<SkillResponseDto>> createSkill(
            @RequestBody CreateSkillDto createSkillDto) {
        SkillResponseDto skill = skillService.createSkill(createSkillDto);
        BaseResponse<SkillResponseDto> response = new BaseResponse<>(
                true, skill, "Skill creada exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }

    @GetMapping
    @Operation(summary = "Obtener todas las skills", description = "Obtener todas las skills")
    public ResponseEntity<BaseResponse<List<SkillResponseDto>>> getAllSkills() {
        List<SkillResponseDto> skills = skillService.getAllSkills();
        BaseResponse<List<SkillResponseDto>> response = new BaseResponse<>(
                true, skills, "Skills obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener skill por ID", description = "Obtener una skill específica por su ID")
    public ResponseEntity<BaseResponse<SkillResponseDto>> findById(
            @Parameter(description = "ID de la skill") @PathVariable Long id) {
        return skillService.findById(id)
                .map(skill -> {
                    BaseResponse<SkillResponseDto> response = new BaseResponse<>(
                            true, skill, "Skill obtenida exitosamente", HttpStatus.OK);
                    return response.buildResponseEntity();
                })
                .orElseGet(() -> {
                    BaseResponse<SkillResponseDto> response = new BaseResponse<>(
                            false, null, "Skill no encontrada", HttpStatus.NOT_FOUND);
                    return response.buildResponseEntity();
                });
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar skill", description = "Actualizar una skill existente")
    public ResponseEntity<BaseResponse<SkillResponseDto>> update(
            @Parameter(description = "ID de la skill") @PathVariable Long id,
            @RequestBody UpdateSkillDto updateSkillDto) {
        SkillResponseDto updated = skillService.update(id, updateSkillDto);
        BaseResponse<SkillResponseDto> response = new BaseResponse<>(
                true, updated, "Skill actualizada exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar skill", description = "Eliminar una skill del sistema")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID de la skill") @PathVariable Long id) {
        skillService.delete(id);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Skill eliminada exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PostMapping("/templates")
    @Operation(summary = "Obtener skills por templates", description = "Obtener skills por templates")
    public ResponseEntity<BaseResponse<List<SkillResponseDto>>> getSkillsByTemplates(
            @RequestBody GetSkillsByTemplatesDto getSkillsByTemplatesDto) {
        List<SkillResponseDto> skills = skillService.getSkillsByTemplates(getSkillsByTemplatesDto);
        BaseResponse<List<SkillResponseDto>> response = new BaseResponse<>(
                true, skills, "Skills por templates obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}


