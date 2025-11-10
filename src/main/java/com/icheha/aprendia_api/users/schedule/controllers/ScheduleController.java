package com.icheha.aprendia_api.users.schedule.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.schedule.data.dtos.CreateScheduleDto;
import com.icheha.aprendia_api.users.schedule.data.dtos.ScheduleResponseDto;
import com.icheha.aprendia_api.users.schedule.data.dtos.UpdateScheduleDto;
import com.icheha.aprendia_api.users.schedule.services.IScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/schedules")
@Tag(name = "2.08. Schedules", description = "API para gestión de horarios disponibles")
public class ScheduleController {
    
    private final IScheduleService scheduleService;
    
    @Autowired
    public ScheduleController(IScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    
    @PostMapping
    @Operation(summary = "Crear horario", description = "Crea un nuevo horario disponible en el sistema")
    public ResponseEntity<BaseResponse<ScheduleResponseDto>> create(@Valid @RequestBody CreateScheduleDto createScheduleDto) {
        ScheduleResponseDto response = scheduleService.create(createScheduleDto);
        BaseResponse<ScheduleResponseDto> baseResponse = new BaseResponse<>(
                true, response, "Horario creado exitosamente", HttpStatus.CREATED);
        return baseResponse.buildResponseEntity();
    }
    
    @GetMapping
    @Operation(summary = "Listar horarios", description = "Obtiene todos los horarios disponibles")
    public ResponseEntity<BaseResponse<List<ScheduleResponseDto>>> findAll() {
        List<ScheduleResponseDto> schedules = scheduleService.findAll();
        BaseResponse<List<ScheduleResponseDto>> response = new BaseResponse<>(
                true, schedules, "Horarios obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener horario por ID", description = "Obtiene un horario por su ID")
    public ResponseEntity<BaseResponse<ScheduleResponseDto>> findById(
            @Parameter(description = "ID del horario", required = true) @PathVariable Long id) {
        Optional<ScheduleResponseDto> schedule = scheduleService.findById(id);
        if (schedule.isPresent()) {
            BaseResponse<ScheduleResponseDto> response = new BaseResponse<>(
                    true, schedule.get(), "Horario obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } else {
            BaseResponse<ScheduleResponseDto> response = new BaseResponse<>(
                    false, null, "Horario no encontrado", HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar horario", description = "Actualiza un horario existente")
    public ResponseEntity<BaseResponse<ScheduleResponseDto>> update(
            @Parameter(description = "ID del horario", required = true) @PathVariable Long id,
            @Valid @RequestBody UpdateScheduleDto updateScheduleDto) {
        ScheduleResponseDto updated = scheduleService.update(id, updateScheduleDto);
        BaseResponse<ScheduleResponseDto> response = new BaseResponse<>(
                true, updated, "Horario actualizado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar horario", description = "Elimina un horario del sistema")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del horario", required = true) @PathVariable Long id) {
        scheduleService.delete(id);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Horario eliminado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

