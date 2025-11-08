package com.icheha.aprendia_api.exercises.layouts.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.CreateLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.response.LayoutResponseDto;
import com.icheha.aprendia_api.exercises.layouts.services.ILayoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/layouts")
@Tag(name = "4.07. Layouts", description = "API para gestión de diseños")
public class LayoutController {

    private final ILayoutService layoutService;

    @Autowired
    public LayoutController(ILayoutService layoutService) {
        this.layoutService = layoutService;
    }

    @PostMapping
    @Operation(summary = "Crear layout", description = "Crear nuevo layout")
    public ResponseEntity<BaseResponse<LayoutResponseDto>> createLayout(
            @RequestBody CreateLayoutDto createLayoutDto) {
        LayoutResponseDto layout = layoutService.createLayout(createLayoutDto);
        BaseResponse<LayoutResponseDto> response = new BaseResponse<>(
                true, layout, "Layout creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }

    @GetMapping
    @Operation(summary = "Obtener todos los layouts", description = "Obtener todos los layouts")
    public ResponseEntity<BaseResponse<List<LayoutResponseDto>>> getAllLayouts() {
        List<LayoutResponseDto> layouts = layoutService.getAllLayouts();
        BaseResponse<List<LayoutResponseDto>> response = new BaseResponse<>(
                true, layouts, "Layouts obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}


