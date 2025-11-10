package com.icheha.aprendia_api.exercises.templates.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTypeInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TypeInstructionMediaResponseDto;
import com.icheha.aprendia_api.exercises.templates.services.ITypeInstructionMediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructions-medias-types")
@Tag(name = "4.11. Type Instruction Media", description = "API para gestión de tipos de instrucción media")
public class TypeInstructionMediaController {

    private final ITypeInstructionMediaService typeInstructionMediaService;

    @Autowired
    public TypeInstructionMediaController(ITypeInstructionMediaService typeInstructionMediaService) {
        this.typeInstructionMediaService = typeInstructionMediaService;
    }

    @PostMapping
    @Operation(summary = "Crear tipo de instrucción media", description = "Crear nuevo tipo de instrucción media")
    public ResponseEntity<BaseResponse<TypeInstructionMediaResponseDto>> createTypeInstructionMedia(
            @RequestBody CreateTypeInstructionMediaDto createTypeInstructionMediaDto) {
        TypeInstructionMediaResponseDto typeInstructionMedia = typeInstructionMediaService.createTypeInstructionMedia(createTypeInstructionMediaDto);
        BaseResponse<TypeInstructionMediaResponseDto> response = new BaseResponse<>(
                true, typeInstructionMedia, "Tipo de instrucción media creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }

    @GetMapping
    @Operation(summary = "Obtener tipos de instrucción media", description = "Obtener todos los tipos de instrucción media")
    public ResponseEntity<BaseResponse<List<TypeInstructionMediaResponseDto>>> getAllTypeInstructionMedias() {
        List<TypeInstructionMediaResponseDto> typeInstructionMedias = typeInstructionMediaService.getAllTypeInstructionMedias();
        BaseResponse<List<TypeInstructionMediaResponseDto>> response = new BaseResponse<>(
                true, typeInstructionMedias, "Tipos de instrucción media obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}