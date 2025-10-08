package com.icheha.aprendia_api.exercises.templates.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateInstructionMediaResponseDto;
import com.icheha.aprendia_api.exercises.templates.services.ITemplateInstructionMediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates-instructions-medias")
@Tag(name = "Template Instruction Media", description = "API para gestión de template-instruction-media")
public class TemplateInstructionMediaController {

    private final ITemplateInstructionMediaService templateInstructionMediaService;

    @Autowired
    public TemplateInstructionMediaController(ITemplateInstructionMediaService templateInstructionMediaService) {
        this.templateInstructionMediaService = templateInstructionMediaService;
    }

    @PostMapping
    @Operation(summary = "Crear template-instruction-media", description = "Crear nuevo template-instruction-media")
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
}