package com.icheha.aprendia_api.exercises.templates.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateSkillResponseDto;
import com.icheha.aprendia_api.exercises.templates.services.ITemplateSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates-skills")
@Tag(name = "Template Skills", description = "API para gestión de template-skills")
public class TemplateSkillController {

    private final ITemplateSkillService templateSkillService;

    @Autowired
    public TemplateSkillController(ITemplateSkillService templateSkillService) {
        this.templateSkillService = templateSkillService;
    }

    @PostMapping
    @Operation(summary = "Crear template-skill", description = "Crear nuevo template-skill")
    public ResponseEntity<BaseResponse<TemplateSkillResponseDto>> createTemplateSkill(
            @RequestBody CreateTemplateSkillDto createTemplateSkillDto) {
        TemplateSkillResponseDto templateSkill = templateSkillService.createTemplateSkill(createTemplateSkillDto);
        BaseResponse<TemplateSkillResponseDto> response = new BaseResponse<>(
                true, templateSkill, "Template-skill creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }

    @GetMapping
    @Operation(summary = "Obtener templates-skills", description = "Obtener todos los templates-skills")
    public ResponseEntity<BaseResponse<List<TemplateSkillResponseDto>>> getAllTemplateSkills() {
        List<TemplateSkillResponseDto> templateSkills = templateSkillService.getAllTemplateSkills();
        BaseResponse<List<TemplateSkillResponseDto>> response = new BaseResponse<>(
                true, templateSkills, "Templates-skills obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}