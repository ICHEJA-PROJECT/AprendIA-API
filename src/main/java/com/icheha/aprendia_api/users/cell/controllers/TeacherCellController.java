package com.icheha.aprendia_api.users.cell.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.cell.data.dtos.CreateTeacherCellDto;
import com.icheha.aprendia_api.users.cell.data.dtos.TeacherCellResponseDto;
import com.icheha.aprendia_api.users.cell.services.ITeacherCellService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher-cells")
@Tag(name = "2.07. Teacher Cells", description = "API para gestión de relaciones educador-célula")
public class TeacherCellController {
    
    private final ITeacherCellService teacherCellService;
    
    @Autowired
    public TeacherCellController(ITeacherCellService teacherCellService) {
        this.teacherCellService = teacherCellService;
    }
    
    @PostMapping
    @Operation(summary = "Asignar educador a célula", description = "Asigna un educador a una célula educativa")
    public ResponseEntity<BaseResponse<TeacherCellResponseDto>> create(@Valid @RequestBody CreateTeacherCellDto createTeacherCellDto) {
        TeacherCellResponseDto response = teacherCellService.create(createTeacherCellDto);
        BaseResponse<TeacherCellResponseDto> baseResponse = new BaseResponse<>(
                true, response, "Educador asignado exitosamente", HttpStatus.CREATED);
        return baseResponse.buildResponseEntity();
    }
}

