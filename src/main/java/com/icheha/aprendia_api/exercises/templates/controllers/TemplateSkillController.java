package com.icheha.aprendia_api.exercises.templates.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.UpdateTemplateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateSkillResponseDto;
import com.icheha.aprendia_api.exercises.templates.services.ITemplateSkillService;
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
@RequestMapping("/templates-skills")
@Tag(name = "4.12. Template Skills", description = "API para gestión de template-skills")
public class TemplateSkillController {

    private final ITemplateSkillService templateSkillService;

    @Autowired
    public TemplateSkillController(ITemplateSkillService templateSkillService) {
        this.templateSkillService = templateSkillService;
    }

    @PostMapping
    @Operation(summary = "Crear template-skill", description = "Crear nuevo template-skill")
    @ApiResponse(responseCode = "201", description = "Template-skill creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
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

    @GetMapping("/{templateId}/{skillId}")
    @Operation(summary = "Obtener template-skill por IDs", description = "Obtener un template-skill específico por templateId y skillId")
    @ApiResponse(responseCode = "200", description = "Template-skill obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Template-skill no encontrado")
    public ResponseEntity<BaseResponse<TemplateSkillResponseDto>> findById(
            @Parameter(description = "ID del template") @PathVariable Long templateId,
            @Parameter(description = "ID de la skill") @PathVariable Long skillId) {
        return templateSkillService.findById(templateId, skillId)
                .map(templateSkill -> {
                    BaseResponse<TemplateSkillResponseDto> response = new BaseResponse<>(
                            true, templateSkill, "Template-skill obtenido exitosamente", HttpStatus.OK);
                    return response.buildResponseEntity();
                })
                .orElseGet(() -> {
                    BaseResponse<TemplateSkillResponseDto> response = new BaseResponse<>(
                            false, null, "Template-skill no encontrado", HttpStatus.NOT_FOUND);
                    return response.buildResponseEntity();
                });
    }

    @PutMapping("/{templateId}/{skillId}")
    @Operation(summary = "Actualizar template-skill", description = "Actualizar un template-skill existente")
    @ApiResponse(responseCode = "200", description = "Template-skill actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "Template-skill no encontrado")
    public ResponseEntity<BaseResponse<TemplateSkillResponseDto>> update(
            @Parameter(description = "ID del template") @PathVariable Long templateId,
            @Parameter(description = "ID de la skill") @PathVariable Long skillId,
            @RequestBody UpdateTemplateSkillDto updateTemplateSkillDto) {
        TemplateSkillResponseDto updated = templateSkillService.update(templateId, skillId, updateTemplateSkillDto);
        BaseResponse<TemplateSkillResponseDto> response = new BaseResponse<>(
                true, updated, "Template-skill actualizado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @DeleteMapping("/{templateId}/{skillId}")
    @Operation(summary = "Eliminar template-skill", description = "Eliminar un template-skill del sistema")
    @ApiResponse(responseCode = "200", description = "Template-skill eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Template-skill no encontrado")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del template") @PathVariable Long templateId,
            @Parameter(description = "ID de la skill") @PathVariable Long skillId) {
        templateSkillService.delete(templateId, skillId);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Template-skill eliminado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}