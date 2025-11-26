package com.icheha.aprendia_api.exercises.topics.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.ResourceResponseDto;
import com.icheha.aprendia_api.exercises.topics.services.IResourceService;
import com.icheha.aprendia_api.users.person.services.IImageUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/resources")
@Tag(name = "4.05. Resources", description = "API para gestión de recursos")
public class ResourceController {

    private final IResourceService resourceService;
    private final IImageUploadService imageUploadService;

    @Autowired
    public ResourceController(IResourceService resourceService, IImageUploadService imageUploadService) {
        this.resourceService = resourceService;
        this.imageUploadService = imageUploadService;
    }

    @PostMapping
    @Operation(summary = "Crear recurso", description = "Crear nuevo recurso")
    @ApiResponse(responseCode = "201", description = "Recurso creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<ResourceResponseDto>> createResource(
            @Valid @RequestBody CreateResourceDto createResourceDto) {
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
    @ApiResponse(responseCode = "200", description = "Recurso obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    public ResponseEntity<BaseResponse<ResourceResponseDto>> getResourceById(
            @Parameter(description = "ID del recurso", required = true) @PathVariable Long id) {
        try {
            ResourceResponseDto resource = resourceService.getResourceById(id);
            BaseResponse<ResourceResponseDto> response = new BaseResponse<>(
                    true, resource, "Recurso obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<ResourceResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar recurso", description = "Actualizar un recurso existente")
    @ApiResponse(responseCode = "200", description = "Recurso actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<ResourceResponseDto>> update(
            @Parameter(description = "ID del recurso", required = true) @PathVariable Long id,
            @Valid @RequestBody UpdateResourceDto updateResourceDto) {
        try {
            ResourceResponseDto resource = resourceService.update(id, updateResourceDto);
            BaseResponse<ResourceResponseDto> response = new BaseResponse<>(
                    true, resource, "Recurso actualizado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<ResourceResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar recurso", description = "Eliminar un recurso del sistema")
    @ApiResponse(responseCode = "200", description = "Recurso eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del recurso", required = true) @PathVariable Long id) {
        try {
            resourceService.delete(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Recurso eliminado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
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
    
    @PostMapping(value = "/{id}/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Subir imagen de recurso", description = "Sube una imagen para un recurso usando Cloudinary")
    @ApiResponse(responseCode = "200", description = "Imagen subida exitosamente")
    @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    public ResponseEntity<BaseResponse<String>> uploadImage(
            @Parameter(description = "ID del recurso") @PathVariable Long id,
            @Parameter(description = "Archivo de imagen") @RequestPart("file") MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = (originalFilename != null && originalFilename.contains(".")) 
                    ? originalFilename.substring(originalFilename.lastIndexOf(".")) 
                    : ".jpg";
            String fileName = "resource-" + id + "-" + 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + extension;
            String imageUrl = imageUploadService.uploadImage(file.getBytes(), fileName, "resources-images");
            
            // Actualizar el recurso con la URL de la imagen
            UpdateResourceDto updateDto = new UpdateResourceDto();
            updateDto.setUrlImagen(imageUrl);
            resourceService.update(id, updateDto);
            
            BaseResponse<String> response = new BaseResponse<>(
                    true, imageUrl, "Imagen subida exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (Exception e) {
            BaseResponse<String> response = new BaseResponse<>(
                    false, null, "Error al subir imagen: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return response.buildResponseEntity();
        }
    }
}


