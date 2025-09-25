package com.icheha.aprendia_api.exercises.templates.controllers;


import com.icheha.aprendia_api.exercises.templates.services.ITemplateInstructionMediaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/templates-instructions-medias")
@Tag(name = "Template Instruction Media", description = "API para gestión de medios de instrucción de plantillas")
public class TemplateInstructionMediaController {

    private final ITemplateInstructionMediaService templateInstructionMediaService;


    @Autowired
    public TemplateInstructionMediaController(ITemplateInstructionMediaService templateInstructionMediaService) {
        this.templateInstructionMediaService = templateInstructionMediaService;
    }
}


