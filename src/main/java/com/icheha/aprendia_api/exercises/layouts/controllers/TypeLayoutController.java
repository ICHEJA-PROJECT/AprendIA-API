package com.icheha.aprendia_api.exercises.layouts.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.CreateTypeLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.response.TypeLayoutResponseDto;
import com.icheha.aprendia_api.exercises.layouts.services.ITypeLayoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/layouts-types")
@Tag(name = "Type Layout", description = "API para gestión de tipos de layout")
public class TypeLayoutController {

    private final ITypeLayoutService typeLayoutService;

    @Autowired
    public TypeLayoutController(ITypeLayoutService typeLayoutService) {
        this.typeLayoutService = typeLayoutService;
    }

    @PostMapping
    @Operation(summary = "Crear tipo de layout", description = "Crear nuevo tipo de layout")
    public ResponseEntity<BaseResponse<TypeLayoutResponseDto>> createTypeLayout(
            @RequestBody CreateTypeLayoutDto createTypeLayoutDto) {
        TypeLayoutResponseDto typeLayout = typeLayoutService.createTypeLayout(createTypeLayoutDto);
        BaseResponse<TypeLayoutResponseDto> response = new BaseResponse<>(
                true, typeLayout, "Tipo de layout creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }

    @GetMapping
    @Operation(summary = "Obtener tipos de layout", description = "Obtener todos los tipos de layout")
    public ResponseEntity<BaseResponse<List<TypeLayoutResponseDto>>> getAllTypeLayouts() {
        List<TypeLayoutResponseDto> typeLayouts = typeLayoutService.getAllTypeLayouts();
        BaseResponse<List<TypeLayoutResponseDto>> response = new BaseResponse<>(
                true, typeLayouts, "Tipos de layout obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}