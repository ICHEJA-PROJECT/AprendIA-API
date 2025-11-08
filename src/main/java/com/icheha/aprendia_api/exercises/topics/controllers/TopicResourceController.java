package com.icheha.aprendia_api.exercises.topics.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicResourceResponseDto;
import com.icheha.aprendia_api.exercises.topics.services.ITopicResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic-resource")
@Tag(name = "4.03. Topic Resources", description = "API para gestión de recursos de temas")
public class TopicResourceController {

    private final ITopicResourceService topicResourceService;

    @Autowired
    public TopicResourceController(ITopicResourceService topicResourceService) {
        this.topicResourceService = topicResourceService;
    }

    @PostMapping
    @Operation(summary = "Crear topic-resource", description = "Crear nuevo topic-resource")
    public ResponseEntity<BaseResponse<TopicResourceResponseDto>> createTopicResource(
            @RequestBody CreateTopicResourceDto createTopicResourceDto) {
        TopicResourceResponseDto topicResource = topicResourceService.createTopicResource(createTopicResourceDto);
        BaseResponse<TopicResourceResponseDto> response = new BaseResponse<>(
                true, topicResource, "Topic-resource creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }

    @GetMapping
    @Operation(summary = "Obtener topic-resources", description = "Obtener todos los topic-resources")
    public ResponseEntity<BaseResponse<List<TopicResourceResponseDto>>> getAllTopicResources() {
        List<TopicResourceResponseDto> topicResources = topicResourceService.getAllTopicResources();
        BaseResponse<List<TopicResourceResponseDto>> response = new BaseResponse<>(
                true, topicResources, "Topic-resources obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}


