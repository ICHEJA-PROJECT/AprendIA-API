package com.icheha.aprendia_api.exercises.topics.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicSequenceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateTopicSequenceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicSequenceResponseDto;
import com.icheha.aprendia_api.exercises.topics.services.ITopicSequenceService;
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
@RequestMapping("/topics-sequences")
@Tag(name = "4.08. Topic Sequences", description = "API para gestión de secuencias de topics")
public class TopicSequenceController {

    private final ITopicSequenceService topicSequenceService;

    @Autowired
    public TopicSequenceController(ITopicSequenceService topicSequenceService) {
        this.topicSequenceService = topicSequenceService;
    }

    @PostMapping
    @Operation(summary = "Crear secuencia de topic", description = "Crear nueva secuencia de topic")
    @ApiResponse(responseCode = "201", description = "Secuencia de topic creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<TopicSequenceResponseDto>> createTopicSequence(
            @RequestBody CreateTopicSequenceDto createTopicSequenceDto) {
        TopicSequenceResponseDto topicSequence = topicSequenceService.createTopicSequence(createTopicSequenceDto);
        BaseResponse<TopicSequenceResponseDto> response = new BaseResponse<>(
                true, topicSequence, "Secuencia de topic creada exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }

    @GetMapping
    @Operation(summary = "Obtener secuencias de topics", description = "Obtener todas las secuencias de topics")
    public ResponseEntity<BaseResponse<List<TopicSequenceResponseDto>>> getAllTopicSequences() {
        List<TopicSequenceResponseDto> topicSequences = topicSequenceService.getAllTopicSequences();
        BaseResponse<List<TopicSequenceResponseDto>> response = new BaseResponse<>(
                true, topicSequences, "Secuencias de topics obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/{topicId}/{nextTopicId}")
    @Operation(summary = "Obtener secuencia de topic por IDs", description = "Obtener una secuencia de topic específica por topicId y nextTopicId")
    @ApiResponse(responseCode = "200", description = "Secuencia de topic obtenida exitosamente")
    @ApiResponse(responseCode = "404", description = "Secuencia de topic no encontrada")
    public ResponseEntity<BaseResponse<TopicSequenceResponseDto>> findById(
            @Parameter(description = "ID del tema") @PathVariable Long topicId,
            @Parameter(description = "ID del tema siguiente") @PathVariable Long nextTopicId) {
        return topicSequenceService.findById(topicId, nextTopicId)
                .map(topicSequence -> {
                    BaseResponse<TopicSequenceResponseDto> response = new BaseResponse<>(
                            true, topicSequence, "Secuencia de topic obtenida exitosamente", HttpStatus.OK);
                    return response.buildResponseEntity();
                })
                .orElseGet(() -> {
                    BaseResponse<TopicSequenceResponseDto> response = new BaseResponse<>(
                            false, null, "Secuencia de topic no encontrada", HttpStatus.NOT_FOUND);
                    return response.buildResponseEntity();
                });
    }

    @PutMapping("/{topicId}/{nextTopicId}")
    @Operation(summary = "Actualizar secuencia de topic", description = "Actualizar una secuencia de topic existente")
    @ApiResponse(responseCode = "200", description = "Secuencia de topic actualizada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "Secuencia de topic no encontrada")
    public ResponseEntity<BaseResponse<TopicSequenceResponseDto>> update(
            @Parameter(description = "ID del tema") @PathVariable Long topicId,
            @Parameter(description = "ID del tema siguiente") @PathVariable Long nextTopicId,
            @RequestBody UpdateTopicSequenceDto updateTopicSequenceDto) {
        TopicSequenceResponseDto updated = topicSequenceService.update(topicId, nextTopicId, updateTopicSequenceDto);
        BaseResponse<TopicSequenceResponseDto> response = new BaseResponse<>(
                true, updated, "Secuencia de topic actualizada exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @DeleteMapping("/{topicId}/{nextTopicId}")
    @Operation(summary = "Eliminar secuencia de topic", description = "Eliminar una secuencia de topic del sistema")
    @ApiResponse(responseCode = "200", description = "Secuencia de topic eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Secuencia de topic no encontrada")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del tema") @PathVariable Long topicId,
            @Parameter(description = "ID del tema siguiente") @PathVariable Long nextTopicId) {
        topicSequenceService.delete(topicId, nextTopicId);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Secuencia de topic eliminada exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}