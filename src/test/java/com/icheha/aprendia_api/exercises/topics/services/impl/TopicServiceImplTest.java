package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.LearningPathResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.TopicEntity;
import com.icheha.aprendia_api.exercises.topics.data.entities.UnitEntity;
import com.icheha.aprendia_api.exercises.topics.data.repositories.TopicRepository;
import com.icheha.aprendia_api.exercises.topics.data.repositories.UnitRepository;
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
class TopicServiceImplTest {

    @Mock
    private TopicRepository topicRepository;

    @Mock
    private UnitRepository unitRepository;

    @InjectMocks
    private TopicServiceImpl topicService;

    private CreateTopicDto createTopicDto;
    private TopicEntity topicEntity;
    private UnitEntity unitEntity;
    private TopicResponseDto topicResponseDto;

    @BeforeEach
    void setUp() {
        // Setup test data
        createTopicDto = new CreateTopicDto();
        createTopicDto.setName("Test Topic");
        createTopicDto.setUnitId(1L);

        unitEntity = new UnitEntity();
        unitEntity.setIdUnidad(1L);
        unitEntity.setNombre("Test Unit");
        unitEntity.setDescripcion("Test Unit Description");

        topicEntity = new TopicEntity();
        topicEntity.setIdTema(1L);
        topicEntity.setNombre("Test Topic");
        topicEntity.setIdUnidad(1L);
        topicEntity.setUnidad(unitEntity);

        topicResponseDto = new TopicResponseDto();
        topicResponseDto.setId(1L);
        topicResponseDto.setName("Test Topic");
        topicResponseDto.setUnitId(1L);
        topicResponseDto.setUnitName("Test Unit");
    }

    @Test
    void createTopic_Success() {
        // Given
        when(unitRepository.findById(1L)).thenReturn(Optional.of(unitEntity));
        when(topicRepository.save(any(TopicEntity.class))).thenReturn(topicEntity);

        // When
        TopicResponseDto result = topicService.createTopic(createTopicDto);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Topic", result.getName());
        assertEquals(1L, result.getUnitId());
        assertEquals("Test Unit", result.getUnitName());
        verify(unitRepository).findById(1L);
        verify(topicRepository).save(any(TopicEntity.class));
    }

    @Test
    void createTopic_UnitNotFound() {
        // Given
        when(unitRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> 
                topicService.createTopic(createTopicDto));
        
        assertEquals("Unidad no encontrada con ID: 1", exception.getMessage());
        verify(unitRepository).findById(1L);
        verify(topicRepository, never()).save(any());
    }

    @Test
    void getAllTopics_Success() {
        // Given
        List<TopicEntity> entities = Arrays.asList(topicEntity);
        when(topicRepository.findAll()).thenReturn(entities);
        when(unitRepository.findById(1L)).thenReturn(Optional.of(unitEntity));

        // When
        List<TopicResponseDto> result = topicService.getAllTopics();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Test Topic", result.get(0).getName());
        verify(topicRepository).findAll();
    }

    @Test
    void getTopicsByPupilLearningPath_Success() {
        // Given
        List<TopicEntity> entities = Arrays.asList(topicEntity);
        when(topicRepository.findAll()).thenReturn(entities);
        when(unitRepository.findById(1L)).thenReturn(Optional.of(unitEntity));

        // When
        List<TopicResponseDto> result = topicService.getTopicsByPupilLearningPath(1, 1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(topicRepository).findAll();
    }

    @Test
    void getTopicsByPupil_Success() {
        // Given
        List<TopicEntity> entities = Arrays.asList(topicEntity);
        when(topicRepository.findAll()).thenReturn(entities);
        when(unitRepository.findById(1L)).thenReturn(Optional.of(unitEntity));

        // When
        List<TopicResponseDto> result = topicService.getTopicsByPupil(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(topicRepository).findAll();
    }

    @Test
    void getLearningPathsByTopicId_Success() {
        // When
        List<LearningPathResponseDto> result = topicService.getLearningPathsByTopicId(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Learning Path 1", result.get(0).getName());
    }

    @Test
    void getLearningPathByTopic_Success() {
        // When
        List<LearningPathResponseDto> result = topicService.getLearningPathByTopic(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Learning Path 1", result.get(0).getName());
    }
}
