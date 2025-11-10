package com.icheha.aprendia_api.exercises.topics.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.LearningPathResponseDto;
import com.icheha.aprendia_api.exercises.topics.services.ITopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
@Tag(name = "4.02. Topics", description = "API para gestión de temas")
public class TopicController {

    private final ITopicService topicService;

    @Autowired
    public TopicController(ITopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    @Operation(summary = "Crear tema", description = "Crear nuevo tema")
    public ResponseEntity<BaseResponse<TopicResponseDto>> createTopic(
            @RequestBody CreateTopicDto createTopicDto) {
        TopicResponseDto topic = topicService.createTopic(createTopicDto);
        BaseResponse<TopicResponseDto> response = new BaseResponse<>(
                true, topic, "Tema creado exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }

    @GetMapping
    @Operation(summary = "Obtener todos los temas", description = "Obtener todos los temas")
    public ResponseEntity<BaseResponse<List<TopicResponseDto>>> getAllTopics() {
        List<TopicResponseDto> topics = topicService.getAllTopics();
        BaseResponse<List<TopicResponseDto>> response = new BaseResponse<>(
                true, topics, "Temas obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
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

    @GetMapping("/{id}/learning-path")
    @Operation(summary = "Obtener ruta de aprendizaje por tema", description = "Obtener ruta de aprendizaje por tema")
    public ResponseEntity<BaseResponse<List<LearningPathResponseDto>>> getLearningPathByTopic(
            @Parameter(description = "ID del tema") @PathVariable Integer id) {
        List<LearningPathResponseDto> learningPath = topicService.getLearningPathByTopic(id);
        BaseResponse<List<LearningPathResponseDto>> response = new BaseResponse<>(
                true, learningPath, "Ruta de aprendizaje obtenida exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}


