package com.icheha.aprendia_api.exercises.layouts.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.CreateLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.UpdateLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.response.LayoutResponseDto;
import com.icheha.aprendia_api.exercises.layouts.services.ILayoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/layouts")
@Tag(name = "4.10. Layouts", description = "API para gestión de diseños")
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
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener layout por ID", description = "Obtener un layout específico por su ID")
    public ResponseEntity<BaseResponse<LayoutResponseDto>> findById(
            @Parameter(description = "ID del layout") @PathVariable Long id) {
        return layoutService.findById(id)
                .map(layout -> {
                    BaseResponse<LayoutResponseDto> response = new BaseResponse<>(
                            true, layout, "Layout obtenido exitosamente", HttpStatus.OK);
                    return response.buildResponseEntity();
                })
                .orElseGet(() -> {
                    BaseResponse<LayoutResponseDto> response = new BaseResponse<>(
                            false, null, "Layout no encontrado", HttpStatus.NOT_FOUND);
                    return response.buildResponseEntity();
                });
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar layout", description = "Actualizar un layout existente")
    public ResponseEntity<BaseResponse<LayoutResponseDto>> update(
            @Parameter(description = "ID del layout") @PathVariable Long id,
            @RequestBody UpdateLayoutDto updateLayoutDto) {
        LayoutResponseDto updated = layoutService.update(id, updateLayoutDto);
        BaseResponse<LayoutResponseDto> response = new BaseResponse<>(
                true, updated, "Layout actualizado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar layout", description = "Eliminar un layout del sistema")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del layout") @PathVariable Long id) {
        layoutService.delete(id);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Layout eliminado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}


