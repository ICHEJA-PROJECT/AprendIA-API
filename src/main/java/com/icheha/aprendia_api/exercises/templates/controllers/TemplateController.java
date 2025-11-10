package com.icheha.aprendia_api.exercises.templates.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.GetTemplatesByTopicsDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.UpdateTemplateDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateResponseDto;
import com.icheha.aprendia_api.exercises.templates.services.ITemplateService;
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
@RequestMapping("/templates")
@Tag(name = "4.06. Templates", description = "API para gestión de plantillas (Reactivos)")
public class TemplateController {
    
    private final ITemplateService templateService;
    
    @Autowired
    public TemplateController(ITemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping
    @Operation(summary = "Crear template", description = "Crear nuevo template")
    public ResponseEntity<BaseResponse<TemplateResponseDto>> createTemplate(
            @RequestBody CreateTemplateDto createTemplateDto) {
        TemplateResponseDto template = templateService.createTemplate(createTemplateDto);
        BaseResponse<TemplateResponseDto> response = new BaseResponse<>(
                true, template, "Template creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }

    @GetMapping
    @Operation(summary = "Obtener todos los templates", description = "Obtener todos los templates")
    public ResponseEntity<BaseResponse<List<TemplateResponseDto>>> getAllTemplates() {
        List<TemplateResponseDto> templates = templateService.getAllTemplates();
        BaseResponse<List<TemplateResponseDto>> response = new BaseResponse<>(
                true, templates, "Templates obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @PostMapping("/topics")
    @Operation(summary = "Obtener templates por topics", description = "Obtener templates por topics")
    public ResponseEntity<BaseResponse<List<TemplateResponseDto>>> getTemplatesByTopics(
            @RequestBody GetTemplatesByTopicsDto getTemplatesByTopicsDto) {
        List<TemplateResponseDto> templates = templateService.getTemplatesByTopics(getTemplatesByTopicsDto);
        BaseResponse<List<TemplateResponseDto>> response = new BaseResponse<>(
                true, templates, "Templates por topics obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/topic/{id}")
    @Operation(summary = "Obtener template por topic ID", description = "Obtener template por topic ID")
    public ResponseEntity<BaseResponse<List<TemplateResponseDto>>> getTemplatesByTopicId(
            @Parameter(description = "ID del topic") @PathVariable Integer id) {
        List<TemplateResponseDto> templates = templateService.getTemplatesByTopicId(id);
        BaseResponse<List<TemplateResponseDto>> response = new BaseResponse<>(
                true, templates, "Templates por topic obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener template por ID", description = "Obtener template por ID")
    @ApiResponse(responseCode = "200", description = "Template obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Template no encontrado")
    public ResponseEntity<BaseResponse<TemplateResponseDto>> getTemplateById(
            @Parameter(description = "ID del template", required = true) @PathVariable Long id) {
        try {
            TemplateResponseDto template = templateService.getTemplateById(id);
            BaseResponse<TemplateResponseDto> response = new BaseResponse<>(
                    true, template, "Template obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<TemplateResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar template", description = "Actualizar un template/reactivo existente")
    @ApiResponse(responseCode = "200", description = "Template actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Template no encontrado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<TemplateResponseDto>> update(
            @Parameter(description = "ID del template", required = true) @PathVariable Long id,
            @Valid @RequestBody UpdateTemplateDto updateTemplateDto) {
        try {
            TemplateResponseDto template = templateService.update(id, updateTemplateDto);
            BaseResponse<TemplateResponseDto> response = new BaseResponse<>(
                    true, template, "Template actualizado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<TemplateResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar template", description = "Eliminar un template/reactivo del sistema")
    @ApiResponse(responseCode = "200", description = "Template eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Template no encontrado")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del template", required = true) @PathVariable Long id) {
        try {
            templateService.delete(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Template eliminado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
}


