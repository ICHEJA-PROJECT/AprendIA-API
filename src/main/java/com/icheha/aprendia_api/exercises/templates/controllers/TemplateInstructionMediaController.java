package com.icheha.aprendia_api.exercises.templates.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.UpdateTemplateInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateInstructionMediaResponseDto;
import com.icheha.aprendia_api.exercises.templates.services.ITemplateInstructionMediaService;
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
@RequestMapping("/templates-instructions-medias")
@Tag(name = "4.14. Template Instruction Media", description = "API para gestión de template-instruction-media")
public class TemplateInstructionMediaController {

    private final ITemplateInstructionMediaService templateInstructionMediaService;

    @Autowired
    public TemplateInstructionMediaController(ITemplateInstructionMediaService templateInstructionMediaService) {
        this.templateInstructionMediaService = templateInstructionMediaService;
    }

    @PostMapping
    @Operation(summary = "Crear template-instruction-media", description = "Crear nuevo template-instruction-media")
    @ApiResponse(responseCode = "201", description = "Template-instruction-media creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<TemplateInstructionMediaResponseDto>> createTemplateInstructionMedia(
            @RequestBody CreateTemplateInstructionMediaDto createTemplateInstructionMediaDto) {
        TemplateInstructionMediaResponseDto templateInstructionMedia = templateInstructionMediaService.createTemplateInstructionMedia(createTemplateInstructionMediaDto);
        BaseResponse<TemplateInstructionMediaResponseDto> response = new BaseResponse<>(
                true, templateInstructionMedia, "Template-instruction-media creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }

    @GetMapping
    @Operation(summary = "Obtener templates-instructions-medias", description = "Obtener todos los templates-instructions-medias")
    public ResponseEntity<BaseResponse<List<TemplateInstructionMediaResponseDto>>> getAllTemplateInstructionMedias() {
        List<TemplateInstructionMediaResponseDto> templateInstructionMedias = templateInstructionMediaService.getAllTemplateInstructionMedias();
        BaseResponse<List<TemplateInstructionMediaResponseDto>> response = new BaseResponse<>(
                true, templateInstructionMedias, "Templates-instructions-medias obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/{templateId}/{typeMediaId}")
    @Operation(summary = "Obtener template-instruction-media por IDs", description = "Obtener un template-instruction-media específico por templateId y typeMediaId")
    @ApiResponse(responseCode = "200", description = "Template-instruction-media obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Template-instruction-media no encontrado")
    public ResponseEntity<BaseResponse<TemplateInstructionMediaResponseDto>> findById(
            @Parameter(description = "ID del template") @PathVariable Long templateId,
            @Parameter(description = "ID del tipo de media") @PathVariable Long typeMediaId) {
        return templateInstructionMediaService.findById(templateId, typeMediaId)
                .map(templateInstructionMedia -> {
                    BaseResponse<TemplateInstructionMediaResponseDto> response = new BaseResponse<>(
                            true, templateInstructionMedia, "Template-instruction-media obtenido exitosamente", HttpStatus.OK);
                    return response.buildResponseEntity();
                })
                .orElseGet(() -> {
                    BaseResponse<TemplateInstructionMediaResponseDto> response = new BaseResponse<>(
                            false, null, "Template-instruction-media no encontrado", HttpStatus.NOT_FOUND);
                    return response.buildResponseEntity();
                });
    }

    @PutMapping("/{templateId}/{typeMediaId}")
    @Operation(summary = "Actualizar template-instruction-media", description = "Actualizar un template-instruction-media existente")
    @ApiResponse(responseCode = "200", description = "Template-instruction-media actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "Template-instruction-media no encontrado")
    public ResponseEntity<BaseResponse<TemplateInstructionMediaResponseDto>> update(
            @Parameter(description = "ID del template") @PathVariable Long templateId,
            @Parameter(description = "ID del tipo de media") @PathVariable Long typeMediaId,
            @RequestBody UpdateTemplateInstructionMediaDto updateTemplateInstructionMediaDto) {
        TemplateInstructionMediaResponseDto updated = templateInstructionMediaService.update(templateId, typeMediaId, updateTemplateInstructionMediaDto);
        BaseResponse<TemplateInstructionMediaResponseDto> response = new BaseResponse<>(
                true, updated, "Template-instruction-media actualizado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @DeleteMapping("/{templateId}/{typeMediaId}")
    @Operation(summary = "Eliminar template-instruction-media", description = "Eliminar un template-instruction-media del sistema")
    @ApiResponse(responseCode = "200", description = "Template-instruction-media eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Template-instruction-media no encontrado")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del template") @PathVariable Long templateId,
            @Parameter(description = "ID del tipo de media") @PathVariable Long typeMediaId) {
        templateInstructionMediaService.delete(templateId, typeMediaId);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Template-instruction-media eliminado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}