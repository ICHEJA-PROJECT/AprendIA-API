package com.icheha.aprendia_api.exercises.exercises.services.impl;

import com.icheha.aprendia_api.exercises.exercises.data.dtos.request.CreateExerciseDto;
import com.icheha.aprendia_api.exercises.exercises.data.dtos.response.ExerciseResponseDto;
import com.icheha.aprendia_api.exercises.exercises.data.entities.ExerciseEntity;
import com.icheha.aprendia_api.exercises.exercises.data.repositories.ExerciseRepository;
import com.icheha.aprendia_api.exercises.exercises.data.repositories.IExerciseRepository;
import com.icheha.aprendia_api.exercises.exercises.services.mappers.ExerciseMapper;
import com.icheha.aprendia_api.exercises.templates.data.entities.TemplateEntity;
import com.icheha.aprendia_api.exercises.templates.data.repositories.ITemplateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExerciseServiceImplTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private IExerciseRepository iExerciseRepository;

    @Mock
    private ITemplateRepository templateRepository;

    @Mock
    private ExerciseMapper exerciseMapper;

    @InjectMocks
    private ExerciseServiceImpl exerciseService;

    private CreateExerciseDto createExerciseDto;
    private ExerciseEntity exerciseEntity;
    private TemplateEntity templateEntity;
    private ExerciseResponseDto exerciseResponseDto;

    @BeforeEach
    void setUp() {
        // Setup test data
        createExerciseDto = new CreateExerciseDto();
        createExerciseDto.setContext("{\"question\": \"Test question\"}");
        createExerciseDto.setTemplateId(1L);

        templateEntity = new TemplateEntity();
        templateEntity.setId(1L);
        templateEntity.setTitulo("Test Template");
        templateEntity.setInstrucciones("Test instructions");

        exerciseEntity = new ExerciseEntity();
        exerciseEntity.setId(1L);
        exerciseEntity.setContext(Map.of("question", "Test question"));
        exerciseEntity.setTemplate(templateEntity);

        exerciseResponseDto = new ExerciseResponseDto();
        exerciseResponseDto.setId(1L);
        exerciseResponseDto.setContext(Map.of("question", "Test question"));
        exerciseResponseDto.setTemplateId(1L);
        exerciseResponseDto.setTemplateNombre("Test Template");
    }

    @Test
    void getAllExercises_Success() {
        // Given
        List<ExerciseEntity> entities = Arrays.asList(exerciseEntity);
        List<ExerciseResponseDto> responseDtos = Arrays.asList(exerciseResponseDto);
        
        when(exerciseRepository.findAll()).thenReturn(entities);
        when(exerciseMapper.toResponseDtoList(entities)).thenReturn(responseDtos);

        // When
        List<ExerciseResponseDto> result = exerciseService.getAllExercises();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(exerciseRepository).findAll();
        verify(exerciseMapper).toResponseDtoList(entities);
    }

    @Test
    void createExercise_Success() {
        // Given
        when(templateRepository.findById(1L)).thenReturn(Optional.of(templateEntity));
        when(exerciseMapper.toEntity(createExerciseDto, templateEntity)).thenReturn(exerciseEntity);
        when(exerciseRepository.save(exerciseEntity)).thenReturn(exerciseEntity);
        when(exerciseMapper.toResponseDto(exerciseEntity)).thenReturn(exerciseResponseDto);

        // When
        ExerciseResponseDto result = exerciseService.createExercise(createExerciseDto);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(templateRepository).findById(1L);
        verify(exerciseMapper).toEntity(createExerciseDto, templateEntity);
        verify(exerciseRepository).save(exerciseEntity);
        verify(exerciseMapper).toResponseDto(exerciseEntity);
    }

    @Test
    void createExercise_TemplateNotFound() {
        // Given
        when(templateRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> 
                exerciseService.createExercise(createExerciseDto));
        
        assertEquals("Template no encontrado con ID: 1", exception.getMessage());
        verify(templateRepository).findById(1L);
        verify(exerciseRepository, never()).save(any());
    }

    @Test
    void getExerciseById_Success() {
        // Given
        when(exerciseRepository.findById(1L)).thenReturn(Optional.of(exerciseEntity));
        when(exerciseMapper.toResponseDto(exerciseEntity)).thenReturn(exerciseResponseDto);

        // When
        ExerciseResponseDto result = exerciseService.getExerciseById(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(exerciseRepository).findById(1L);
        verify(exerciseMapper).toResponseDto(exerciseEntity);
    }

    @Test
    void getExerciseById_NotFound() {
        // Given
        when(exerciseRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> 
                exerciseService.getExerciseById(1L));
        
        assertEquals("Ejercicio no encontrado con ID: 1", exception.getMessage());
        verify(exerciseRepository).findById(1L);
        verify(exerciseMapper, never()).toResponseDto(any());
    }

    @Test
    void getPercentageByIdAndSkill_Success() {
        // Given
        when(iExerciseRepository.findPercentageByExerciseAndSkill(1L, 2L)).thenReturn(85.5);

        // When
        Double result = exerciseService.getPercentageByIdAndSkill(1, 2);

        // Then
        assertNotNull(result);
        assertEquals(85.5, result);
        verify(iExerciseRepository).findPercentageByExerciseAndSkill(1L, 2L);
    }

    @Test
    void getExercisesByPupil_Success() {
        // Given
        List<ExerciseEntity> entities = Arrays.asList(exerciseEntity, exerciseEntity);
        List<ExerciseResponseDto> responseDtos = Arrays.asList(exerciseResponseDto, exerciseResponseDto);
        
        when(exerciseRepository.findAll()).thenReturn(entities);
        when(exerciseMapper.toResponseDtoList(anyList())).thenReturn(responseDtos);

        // When
        List<ExerciseResponseDto> result = exerciseService.getExercisesByPupil(1, 5);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(exerciseRepository).findAll();
        verify(exerciseMapper).toResponseDtoList(anyList());
    }

    @Test
    void getExercisesByTemplateId_Success() {
        // Given
        List<ExerciseEntity> entities = Arrays.asList(exerciseEntity);
        List<ExerciseResponseDto> responseDtos = Arrays.asList(exerciseResponseDto);
        
        when(exerciseRepository.findByTemplateId(1L)).thenReturn(entities);
        when(exerciseMapper.toResponseDtoList(entities)).thenReturn(responseDtos);

        // When
        List<ExerciseResponseDto> result = exerciseService.getExercisesByTemplateId(1L);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(exerciseRepository).findByTemplateId(1L);
        verify(exerciseMapper).toResponseDtoList(entities);
    }

    @Test
    void getRandomExerciseByTemplate_Success() {
        // Given
        when(exerciseRepository.findRandomByTemplateId(1L)).thenReturn(Optional.of(exerciseEntity));
        when(exerciseMapper.toResponseDto(exerciseEntity)).thenReturn(exerciseResponseDto);

        // When
        ExerciseResponseDto result = exerciseService.getRandomExerciseByTemplate(1);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(exerciseRepository).findRandomByTemplateId(1L);
        verify(exerciseMapper).toResponseDto(exerciseEntity);
    }

    @Test
    void getRandomExerciseByTemplate_NotFound() {
        // Given
        when(exerciseRepository.findRandomByTemplateId(1L)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> 
                exerciseService.getRandomExerciseByTemplate(1));
        
        assertEquals("No se encontraron ejercicios para el template con ID: 1", exception.getMessage());
        verify(exerciseRepository).findRandomByTemplateId(1L);
        verify(exerciseMapper, never()).toResponseDto(any());
    }
}
