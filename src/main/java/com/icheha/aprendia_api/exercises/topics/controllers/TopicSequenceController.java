package com.icheha.aprendia_api.exercises.topics.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicSequenceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicSequenceResponseDto;
import com.icheha.aprendia_api.exercises.topics.services.ITopicSequenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics-sequences")
@Tag(name = "4.04. Topic Sequences", description = "API para gestión de secuencias de topics")
public class TopicSequenceController {

    private final ITopicSequenceService topicSequenceService;

    @Autowired
    public TopicSequenceController(ITopicSequenceService topicSequenceService) {
        this.topicSequenceService = topicSequenceService;
    }

    @PostMapping
    @Operation(summary = "Crear secuencia de topic", description = "Crear nueva secuencia de topic")
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
}