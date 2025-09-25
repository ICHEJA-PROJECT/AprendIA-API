package com.icheha.aprendia_api.exercises.templates.controllers;


import com.icheha.aprendia_api.exercises.templates.services.ITypeInstructionMediaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructions-medias-types")
@Tag(name = "Type Instruction Media", description = "API para gestión de tipos de medios de instrucción")
public class TypeInstructionMediaController {

    private final ITypeInstructionMediaService typeInstructionMediaService;

    @Autowired
    public TypeInstructionMediaController(ITypeInstructionMediaService typeInstructionMediaService) {
        this.typeInstructionMediaService = typeInstructionMediaService;
    }
}


