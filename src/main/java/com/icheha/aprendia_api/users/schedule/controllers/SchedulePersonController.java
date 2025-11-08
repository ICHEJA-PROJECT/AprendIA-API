package com.icheha.aprendia_api.users.schedule.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.schedule.data.dtos.CreateSchedulePersonDto;
import com.icheha.aprendia_api.users.schedule.data.dtos.SchedulePersonResponseDto;
import com.icheha.aprendia_api.users.schedule.services.ISchedulePersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule-persons")
@Tag(name = "2.09. Schedule Persons", description = "API para gestión de horarios asignados a personas")
public class SchedulePersonController {
    
    private final ISchedulePersonService schedulePersonService;
    
    @Autowired
    public SchedulePersonController(ISchedulePersonService schedulePersonService) {
        this.schedulePersonService = schedulePersonService;
    }
    
    @PostMapping
    @Operation(summary = "Asignar horario a persona", description = "Asigna un horario disponible a una persona con rol")
    public ResponseEntity<BaseResponse<SchedulePersonResponseDto>> create(
            @Valid @RequestBody CreateSchedulePersonDto createSchedulePersonDto) {
        SchedulePersonResponseDto response = schedulePersonService.create(createSchedulePersonDto);
        BaseResponse<SchedulePersonResponseDto> baseResponse = new BaseResponse<>(
                true, response, "Horario asignado exitosamente", HttpStatus.CREATED);
        return baseResponse.buildResponseEntity();
    }
    
    @GetMapping("/person/{personId}")
    @Operation(summary = "Obtener horarios de una persona", description = "Obtiene todos los horarios asignados a una persona")
    public ResponseEntity<BaseResponse<List<SchedulePersonResponseDto>>> findByPerson(
            @Parameter(description = "ID de la persona", required = true) @PathVariable Long personId) {
        List<SchedulePersonResponseDto> schedules = schedulePersonService.findByPerson(personId);
        BaseResponse<List<SchedulePersonResponseDto>> response = new BaseResponse<>(
                true, schedules, "Horarios obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

