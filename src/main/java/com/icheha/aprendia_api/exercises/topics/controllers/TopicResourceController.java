package com.icheha.aprendia_api.exercises.topics.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateTopicResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicResourceResponseDto;
import com.icheha.aprendia_api.exercises.topics.services.ITopicResourceService;
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
@RequestMapping("/topic-resource")
@Tag(name = "4.07. Topic Resources", description = "API para gestión de recursos de temas")
public class TopicResourceController {

    private final ITopicResourceService topicResourceService;

    @Autowired
    public TopicResourceController(ITopicResourceService topicResourceService) {
        this.topicResourceService = topicResourceService;
    }

    @PostMapping
    @Operation(summary = "Crear topic-resource", description = "Crear nuevo topic-resource")
    @ApiResponse(responseCode = "201", description = "Topic-resource creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
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

    @GetMapping("/{topicId}/{resourceId}")
    @Operation(summary = "Obtener topic-resource por IDs", description = "Obtener un topic-resource específico por topicId y resourceId")
    @ApiResponse(responseCode = "200", description = "Topic-resource obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Topic-resource no encontrado")
    public ResponseEntity<BaseResponse<TopicResourceResponseDto>> findById(
            @Parameter(description = "ID del tema") @PathVariable Long topicId,
            @Parameter(description = "ID del recurso") @PathVariable Long resourceId) {
        return topicResourceService.findById(topicId, resourceId)
                .map(topicResource -> {
                    BaseResponse<TopicResourceResponseDto> response = new BaseResponse<>(
                            true, topicResource, "Topic-resource obtenido exitosamente", HttpStatus.OK);
                    return response.buildResponseEntity();
                })
                .orElseGet(() -> {
                    BaseResponse<TopicResourceResponseDto> response = new BaseResponse<>(
                            false, null, "Topic-resource no encontrado", HttpStatus.NOT_FOUND);
                    return response.buildResponseEntity();
                });
    }

    @PutMapping("/{topicId}/{resourceId}")
    @Operation(summary = "Actualizar topic-resource", description = "Actualizar un topic-resource existente")
    @ApiResponse(responseCode = "200", description = "Topic-resource actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "Topic-resource no encontrado")
    public ResponseEntity<BaseResponse<TopicResourceResponseDto>> update(
            @Parameter(description = "ID del tema") @PathVariable Long topicId,
            @Parameter(description = "ID del recurso") @PathVariable Long resourceId,
            @RequestBody UpdateTopicResourceDto updateTopicResourceDto) {
        TopicResourceResponseDto updated = topicResourceService.update(topicId, resourceId, updateTopicResourceDto);
        BaseResponse<TopicResourceResponseDto> response = new BaseResponse<>(
                true, updated, "Topic-resource actualizado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @DeleteMapping("/{topicId}/{resourceId}")
    @Operation(summary = "Eliminar topic-resource", description = "Eliminar un topic-resource del sistema")
    @ApiResponse(responseCode = "200", description = "Topic-resource eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Topic-resource no encontrado")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del tema") @PathVariable Long topicId,
            @Parameter(description = "ID del recurso") @PathVariable Long resourceId) {
        topicResourceService.delete(topicId, resourceId);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Topic-resource eliminado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}


