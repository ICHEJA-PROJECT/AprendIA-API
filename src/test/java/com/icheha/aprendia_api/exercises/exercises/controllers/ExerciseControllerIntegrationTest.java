package com.icheha.aprendia_api.exercises.exercises.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icheha.aprendia_api.exercises.exercises.data.dtos.request.CreateExerciseDto;
import com.icheha.aprendia_api.exercises.exercises.data.dtos.response.ExerciseResponseDto;
import com.icheha.aprendia_api.exercises.exercises.services.IExerciseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExerciseController.class)
class ExerciseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IExerciseService exerciseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createExercise_Success() throws Exception {
        // Given
        CreateExerciseDto createExerciseDto = new CreateExerciseDto();
        createExerciseDto.setContext("{\"question\": \"Test question\"}");
        createExerciseDto.setTemplateId(1L);

        ExerciseResponseDto exerciseResponseDto = new ExerciseResponseDto();
        exerciseResponseDto.setId(1L);
        exerciseResponseDto.setContext(Map.of("question", "Test question"));
        exerciseResponseDto.setTemplateId(1L);

        when(exerciseService.createExercise(any(CreateExerciseDto.class))).thenReturn(exerciseResponseDto);

        // When & Then
        mockMvc.perform(post("/api/exercises")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createExerciseDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.templateId").value(1))
                .andExpect(jsonPath("$.message").value("Ejercicio creado exitosamente"));

        verify(exerciseService).createExercise(any(CreateExerciseDto.class));
    }

    @Test
    void getAllExercises_Success() throws Exception {
        // Given
        ExerciseResponseDto exerciseResponseDto = new ExerciseResponseDto();
        exerciseResponseDto.setId(1L);
        exerciseResponseDto.setContext(Map.of("question", "Test question"));
        exerciseResponseDto.setTemplateId(1L);

        List<ExerciseResponseDto> exercises = Arrays.asList(exerciseResponseDto);
        when(exerciseService.getAllExercises()).thenReturn(exercises);

        // When & Then
        mockMvc.perform(get("/api/exercises"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].templateId").value(1))
                .andExpect(jsonPath("$.message").value("Ejercicios obtenidos exitosamente"));

        verify(exerciseService).getAllExercises();
    }

    @Test
    void getPercentageByIdAndSkill_Success() throws Exception {
        // Given
        when(exerciseService.getPercentageByIdAndSkill(1, 2)).thenReturn(85.5);

        // When & Then
        mockMvc.perform(get("/api/exercises/porcentage")
                .param("id", "1")
                .param("skillId", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value(85.5))
                .andExpect(jsonPath("$.message").value("Porcentaje obtenido exitosamente"));

        verify(exerciseService).getPercentageByIdAndSkill(1, 2);
    }

    @Test
    void getExercisesByPupil_Success() throws Exception {
        // Given
        ExerciseResponseDto exerciseResponseDto = new ExerciseResponseDto();
        exerciseResponseDto.setId(1L);
        exerciseResponseDto.setContext(Map.of("question", "Test question"));
        exerciseResponseDto.setTemplateId(1L);

        List<ExerciseResponseDto> exercises = Arrays.asList(exerciseResponseDto);
        when(exerciseService.getExercisesByPupil(1, 5)).thenReturn(exercises);

        // When & Then
        mockMvc.perform(get("/api/exercises/pupil/1/learning-path")
                .param("learningPathId", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.message").value("Ejercicios del estudiante obtenidos exitosamente"));

        verify(exerciseService).getExercisesByPupil(1, 5);
    }

    @Test
    void getExerciseById_Success() throws Exception {
        // Given
        ExerciseResponseDto exerciseResponseDto = new ExerciseResponseDto();
        exerciseResponseDto.setId(1L);
        exerciseResponseDto.setContext(Map.of("question", "Test question"));
        exerciseResponseDto.setTemplateId(1L);

        when(exerciseService.getExerciseById(1L)).thenReturn(exerciseResponseDto);

        // When & Then
        mockMvc.perform(get("/api/exercises/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.templateId").value(1))
                .andExpect(jsonPath("$.message").value("Ejercicio obtenido exitosamente"));

        verify(exerciseService).getExerciseById(1L);
    }

    @Test
    void getRandomExerciseByTemplate_Success() throws Exception {
        // Given
        ExerciseResponseDto exerciseResponseDto = new ExerciseResponseDto();
        exerciseResponseDto.setId(1L);
        exerciseResponseDto.setContext(Map.of("question", "Test question"));
        exerciseResponseDto.setTemplateId(1L);

        when(exerciseService.getRandomExerciseByTemplate(1)).thenReturn(exerciseResponseDto);

        // When & Then
        mockMvc.perform(get("/api/exercises/templates/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.templateId").value(1))
                .andExpect(jsonPath("$.message").value("Ejercicio aleatorio obtenido exitosamente"));

        verify(exerciseService).getRandomExerciseByTemplate(1);
    }
}
