package com.icheha.aprendia_api.exercises.topics.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateTopicDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.LearningPathResponseDto;
import com.icheha.aprendia_api.exercises.topics.services.ITopicService;
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
@RequestMapping("/topics")
@Tag(name = "4.04. Topics", description = "API para gestión de temas")
public class TopicController {

    private final ITopicService topicService;
    private final IImageUploadService imageUploadService;

    @Autowired
    public TopicController(ITopicService topicService, IImageUploadService imageUploadService) {
        this.topicService = topicService;
        this.imageUploadService = imageUploadService;
    }

    @PostMapping
    @Operation(summary = "Crear tema", description = "Crear nuevo tema")
    @ApiResponse(responseCode = "201", description = "Tema creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<TopicResponseDto>> createTopic(
            @Valid @RequestBody CreateTopicDto createTopicDto) {
        TopicResponseDto topic = topicService.createTopic(createTopicDto);
        BaseResponse<TopicResponseDto> response = new BaseResponse<>(
                true, topic, "Tema creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }

    @GetMapping
    @Operation(summary = "Obtener todos los temas", description = "Obtener todos los temas")
    @ApiResponse(responseCode = "200", description = "Temas obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<TopicResponseDto>>> getAllTopics() {
        List<TopicResponseDto> topics = topicService.getAllTopics();
        BaseResponse<List<TopicResponseDto>> response = new BaseResponse<>(
                true, topics, "Temas obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener tema por ID", description = "Obtener un tema específico por su ID")
    @ApiResponse(responseCode = "200", description = "Tema obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Tema no encontrado")
    public ResponseEntity<BaseResponse<TopicResponseDto>> findById(
            @Parameter(description = "ID del tema", required = true) @PathVariable Long id) {
        return topicService.findById(id)
                .map(topic -> {
                    BaseResponse<TopicResponseDto> response = new BaseResponse<>(
                            true, topic, "Tema obtenido exitosamente", HttpStatus.OK);
                    return response.buildResponseEntity();
                })
                .orElseGet(() -> {
                    BaseResponse<TopicResponseDto> response = new BaseResponse<>(
                            false, null, "Tema no encontrado", HttpStatus.NOT_FOUND);
                    return response.buildResponseEntity();
                });
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tema", description = "Actualizar un tema existente")
    @ApiResponse(responseCode = "200", description = "Tema actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Tema no encontrado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<TopicResponseDto>> update(
            @Parameter(description = "ID del tema", required = true) @PathVariable Long id,
            @Valid @RequestBody UpdateTopicDto updateTopicDto) {
        try {
            TopicResponseDto topic = topicService.update(id, updateTopicDto);
            BaseResponse<TopicResponseDto> response = new BaseResponse<>(
                    true, topic, "Tema actualizado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<TopicResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tema", description = "Eliminar un tema del sistema")
    @ApiResponse(responseCode = "200", description = "Tema eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Tema no encontrado")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del tema", required = true) @PathVariable Long id) {
        try {
            topicService.delete(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Tema eliminado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }

    @GetMapping("/pupils/{id}/learning-path")
    @Operation(summary = "Obtener temas por alumno y ruta de aprendizaje", description = "Obtener temas por alumno y ruta de aprendizaje")
    public ResponseEntity<BaseResponse<List<TopicResponseDto>>> getTopicsByPupilLearningPath(
            @Parameter(description = "ID del alumno") @PathVariable Integer id,
            @Parameter(description = "ID de la ruta de aprendizaje") @RequestParam Integer learningPathId) {
        List<TopicResponseDto> topics = topicService.getTopicsByPupilLearningPath(id, learningPathId);
        BaseResponse<List<TopicResponseDto>> response = new BaseResponse<>(
                true, topics, "Temas del alumno y ruta de aprendizaje obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/pupils/{id}")
    @Operation(summary = "Obtener temas por alumno", description = "Obtener temas por alumno")
    public ResponseEntity<BaseResponse<List<TopicResponseDto>>> getTopicsByPupil(
            @Parameter(description = "ID del alumno") @PathVariable Integer id) {
        List<TopicResponseDto> topics = topicService.getTopicsByPupil(id);
        BaseResponse<List<TopicResponseDto>> response = new BaseResponse<>(
                true, topics, "Temas del alumno obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/unit/{unitId}")
    @Operation(summary = "Obtener temas por unidad", description = "Obtiene todos los temas asociados a una unidad específica")
    @ApiResponse(responseCode = "200", description = "Temas obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<TopicResponseDto>>> getTopicsByUnit(
            @Parameter(description = "ID de la unidad", required = true)
            @PathVariable Long unitId) {
        List<TopicResponseDto> topics = topicService.getTopicsByUnit(unitId);
        BaseResponse<List<TopicResponseDto>> response = new BaseResponse<>(
                true, topics, "Temas obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/{id}/learning-path")
    @Operation(summary = "Obtener ruta de aprendizaje por tema", description = "Obtener ruta de aprendizaje por tema")
    public ResponseEntity<BaseResponse<List<LearningPathResponseDto>>> getLearningPathByTopic(
            @Parameter(description = "ID del tema") @PathVariable Integer id) {
        List<LearningPathResponseDto> learningPath = topicService.getLearningPathByTopic(id);
        BaseResponse<List<LearningPathResponseDto>> response = new BaseResponse<>(
                true, learningPath, "Ruta de aprendizaje obtenida exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PostMapping(value = "/{id}/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Subir imagen de tema", description = "Sube una imagen para un tema usando Cloudinary")
    @ApiResponse(responseCode = "200", description = "Imagen subida exitosamente")
    @ApiResponse(responseCode = "404", description = "Tema no encontrado")
    public ResponseEntity<BaseResponse<String>> uploadImage(
            @Parameter(description = "ID del tema") @PathVariable Long id,
            @Parameter(description = "Archivo de imagen") @RequestPart("file") MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = (originalFilename != null && originalFilename.contains(".")) 
                    ? originalFilename.substring(originalFilename.lastIndexOf(".")) 
                    : ".jpg";
            String fileName = "topic-" + id + "-" + 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + extension;
            String imageUrl = imageUploadService.uploadImage(file.getBytes(), fileName, "topics-images");
            
            // Actualizar el tema con la URL de la imagen
            UpdateTopicDto updateDto = new UpdateTopicDto();
            updateDto.setUrlImagen(imageUrl);
            topicService.update(id, updateDto);
            
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


