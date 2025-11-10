package com.icheha.aprendia_api.exercises.topics.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateUnitDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.UnitResponseDto;
import com.icheha.aprendia_api.exercises.topics.services.IUnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/units")
@Tag(name = "4.01. Units", description = "API para gestión de unidades")
public class UnitController {

    private final IUnitService unitService;

    @Autowired
    public UnitController(IUnitService unitService) {
        this.unitService = unitService;
    }

    @PostMapping
    @Operation(summary = "Crear unidad", description = "Crear nueva unidad")
    public ResponseEntity<BaseResponse<UnitResponseDto>> createUnit(
            @RequestBody CreateUnitDto createUnitDto) {
        UnitResponseDto unit = unitService.createUnit(createUnitDto);
        BaseResponse<UnitResponseDto> response = new BaseResponse<>(
                true, unit, "Unidad creada exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }

    @GetMapping
    @Operation(summary = "Obtener todas las unidades", description = "Obtener todas las unidades")
    public ResponseEntity<BaseResponse<List<UnitResponseDto>>> getAllUnits() {
        List<UnitResponseDto> units = unitService.getAllUnits();
        BaseResponse<List<UnitResponseDto>> response = new BaseResponse<>(
                true, units, "Unidades obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}