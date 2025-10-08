package com.icheha.aprendia_api.exercises.templates.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.GetSkillsByTemplatesDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.SkillResponseDto;
import com.icheha.aprendia_api.exercises.templates.services.ISkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
@Tag(name = "Skills", description = "API para gestión de habilidades")
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


