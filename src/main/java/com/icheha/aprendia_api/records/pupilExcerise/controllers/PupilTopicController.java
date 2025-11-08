package com.icheha.aprendia_api.records.pupilExcerise.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreatePupilTopicDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.PupilTopicResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.services.IPupilTopicService;
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
@RequestMapping("/pupil-topics")
@Tag(name = "5.3. Pupil Topics", description = "Endpoints de gestión de temas de alumnos")
public class PupilTopicController {
    
    private final IPupilTopicService pupilTopicService;
    
    @Autowired
    public PupilTopicController(IPupilTopicService pupilTopicService) {
        this.pupilTopicService = pupilTopicService;
    }
    
    @PostMapping
    @Operation(
        summary = "Crear registro de topic de alumno",
        description = "Crear registro de topic de alumno"
    )
    @ApiResponse(responseCode = "201", description = "Registro creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<BaseResponse<PupilTopicResponseDto>> createPupilTopic(
            @RequestBody CreatePupilTopicDto createPupilTopicDto) {
        PupilTopicResponseDto pupilTopic = pupilTopicService.createPupilTopic(createPupilTopicDto);
        BaseResponse<PupilTopicResponseDto> response = new BaseResponse<>(
                true, pupilTopic, "Registro de topic creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener topic por ID",
        description = "Obtener topic por ID"
    )
    @ApiResponse(responseCode = "200", description = "Topic encontrado")
    @ApiResponse(responseCode = "404", description = "Topic no encontrado")
    public ResponseEntity<BaseResponse<PupilTopicResponseDto>> getPupilTopicById(
            @Parameter(description = "ID del pupil-topic") @PathVariable Integer id) {
        PupilTopicResponseDto pupilTopic = pupilTopicService.getPupilTopicById(id);
        BaseResponse<PupilTopicResponseDto> response = new BaseResponse<>(
                true, pupilTopic, "Topic obtenido exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/pupil/{pupilId}")
    @Operation(
        summary = "Obtener topics por alumno",
        description = "Obtener todos los topics de un alumno por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Topics obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<PupilTopicResponseDto>>> findByPupil(
            @Parameter(description = "ID del alumno") @PathVariable Integer pupilId) {
        List<PupilTopicResponseDto> pupilTopics = pupilTopicService.findByPupil(pupilId);
        BaseResponse<List<PupilTopicResponseDto>> response = new BaseResponse<>(
                true, pupilTopics, "Topics del alumno obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}
