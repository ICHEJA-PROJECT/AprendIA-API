package com.icheha.aprendia_api.exercises.topics.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.ResourceResponseDto;
import com.icheha.aprendia_api.exercises.topics.services.IResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@Tag(name = "Resources", description = "API para gestión de recursos")
public class ResourceController {

    private final IResourceService resourceService;

    @Autowired
    public ResourceController(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    @Operation(summary = "Crear recurso", description = "Crear nuevo recurso")
    public ResponseEntity<BaseResponse<ResourceResponseDto>> createResource(
            @RequestBody CreateResourceDto createResourceDto) {
        ResourceResponseDto resource = resourceService.createResource(createResourceDto);
        BaseResponse<ResourceResponseDto> response = new BaseResponse<>(
                true, resource, "Recurso creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }

    @GetMapping
    @Operation(summary = "Obtener todos los recursos", description = "Obtener todos los recursos")
    public ResponseEntity<BaseResponse<List<ResourceResponseDto>>> getAllResources() {
        List<ResourceResponseDto> resources = resourceService.getAllResources();
        BaseResponse<List<ResourceResponseDto>> response = new BaseResponse<>(
                true, resources, "Recursos obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/pupils/{id}/learning-path")
    @Operation(summary = "Obtener recursos por alumno y ruta de aprendizaje", description = "Obtener recursos por alumno y ruta de aprendizaje")
    public ResponseEntity<BaseResponse<List<ResourceResponseDto>>> getResourcesByPupilLearningPath(
            @Parameter(description = "ID del alumno") @PathVariable Integer id,
            @Parameter(description = "ID de la ruta de aprendizaje") @RequestParam Integer learningPathId) {
        List<ResourceResponseDto> resources = resourceService.getResourcesByPupilLearningPath(id, learningPathId);
        BaseResponse<List<ResourceResponseDto>> response = new BaseResponse<>(
                true, resources, "Recursos del alumno y ruta de aprendizaje obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/pupils/{id}")
    @Operation(summary = "Obtener recursos por alumno", description = "Obtener recursos por alumno")
    public ResponseEntity<BaseResponse<List<ResourceResponseDto>>> getResourcesByPupil(
            @Parameter(description = "ID del alumno") @PathVariable Integer id) {
        List<ResourceResponseDto> resources = resourceService.getResourcesByPupil(id);
        BaseResponse<List<ResourceResponseDto>> response = new BaseResponse<>(
                true, resources, "Recursos del alumno obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/topic/{id}/learning-path")
    @Operation(summary = "Obtener recursos por topic", description = "Obtener recursos por topic")
    public ResponseEntity<BaseResponse<List<ResourceResponseDto>>> getResourcesByTopic(
            @Parameter(description = "ID del topic") @PathVariable Integer id) {
        List<ResourceResponseDto> resources = resourceService.getResourcesByTopic(id);
        BaseResponse<List<ResourceResponseDto>> response = new BaseResponse<>(
                true, resources, "Recursos del topic obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener recurso por ID", description = "Obtener recurso por ID")
    public ResponseEntity<BaseResponse<ResourceResponseDto>> getResourceById(
            @Parameter(description = "ID del recurso") @PathVariable Integer id) {
        ResourceResponseDto resource = resourceService.getResourceById(id);
        BaseResponse<ResourceResponseDto> response = new BaseResponse<>(
                true, resource, "Recurso obtenido exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/topic/{id}/learning-path/{learningPathId}")
    @Operation(summary = "Obtener recursos por tema y ruta de aprendizaje", description = "Obtener recursos por tema y ruta de aprendizaje")
    public ResponseEntity<BaseResponse<List<ResourceResponseDto>>> getResourcesByTopicLearningPath(
            @Parameter(description = "ID del tema") @PathVariable Integer id,
            @Parameter(description = "ID de la ruta de aprendizaje") @PathVariable Integer learningPathId) {
        List<ResourceResponseDto> resources = resourceService.getResourcesByTopicLearningPath(id, learningPathId);
        BaseResponse<List<ResourceResponseDto>> response = new BaseResponse<>(
                true, resources, "Recursos del tema y ruta de aprendizaje obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}


