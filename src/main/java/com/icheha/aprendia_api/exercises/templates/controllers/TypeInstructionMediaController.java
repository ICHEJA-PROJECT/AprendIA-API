package com.icheha.aprendia_api.exercises.templates.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTypeInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.UpdateTypeInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TypeInstructionMediaResponseDto;
import com.icheha.aprendia_api.exercises.templates.services.ITypeInstructionMediaService;
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
@RequestMapping("/instructions-medias-types")
@Tag(name = "4.13. Type Instruction Media", description = "API para gestión de tipos de instrucción media")
public class TypeInstructionMediaController {

    private final ITypeInstructionMediaService typeInstructionMediaService;

    @Autowired
    public TypeInstructionMediaController(ITypeInstructionMediaService typeInstructionMediaService) {
        this.typeInstructionMediaService = typeInstructionMediaService;
    }

    @PostMapping
    @Operation(summary = "Crear tipo de instrucción media", description = "Crear nuevo tipo de instrucción media")
    @ApiResponse(responseCode = "201", description = "Tipo de instrucción media creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
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

    @GetMapping("/{id}")
    @Operation(summary = "Obtener tipo de instrucción media por ID", description = "Obtener un tipo de instrucción media específico por su ID")
    @ApiResponse(responseCode = "200", description = "Tipo de instrucción media obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Tipo de instrucción media no encontrado")
    public ResponseEntity<BaseResponse<TypeInstructionMediaResponseDto>> findById(
            @Parameter(description = "ID del tipo de instrucción media") @PathVariable Long id) {
        return typeInstructionMediaService.findById(id)
                .map(typeInstructionMedia -> {
                    BaseResponse<TypeInstructionMediaResponseDto> response = new BaseResponse<>(
                            true, typeInstructionMedia, "Tipo de instrucción media obtenido exitosamente", HttpStatus.OK);
                    return response.buildResponseEntity();
                })
                .orElseGet(() -> {
                    BaseResponse<TypeInstructionMediaResponseDto> response = new BaseResponse<>(
                            false, null, "Tipo de instrucción media no encontrado", HttpStatus.NOT_FOUND);
                    return response.buildResponseEntity();
                });
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tipo de instrucción media", description = "Actualizar un tipo de instrucción media existente")
    @ApiResponse(responseCode = "200", description = "Tipo de instrucción media actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "Tipo de instrucción media no encontrado")
    public ResponseEntity<BaseResponse<TypeInstructionMediaResponseDto>> update(
            @Parameter(description = "ID del tipo de instrucción media") @PathVariable Long id,
            @RequestBody UpdateTypeInstructionMediaDto updateTypeInstructionMediaDto) {
        TypeInstructionMediaResponseDto updated = typeInstructionMediaService.update(id, updateTypeInstructionMediaDto);
        BaseResponse<TypeInstructionMediaResponseDto> response = new BaseResponse<>(
                true, updated, "Tipo de instrucción media actualizado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tipo de instrucción media", description = "Eliminar un tipo de instrucción media del sistema")
    @ApiResponse(responseCode = "200", description = "Tipo de instrucción media eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Tipo de instrucción media no encontrado")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del tipo de instrucción media") @PathVariable Long id) {
        typeInstructionMediaService.delete(id);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Tipo de instrucción media eliminado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}