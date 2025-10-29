package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.ResourceResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.ResourceEntity;
import com.icheha.aprendia_api.exercises.layouts.data.entities.LayoutEntity;
import com.icheha.aprendia_api.exercises.topics.data.entities.pivots.TopicResourceEntity;
import com.icheha.aprendia_api.exercises.topics.data.repositories.ResourceRepository;
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
class ResourceServiceImplTest {

    @Mock
    private ResourceRepository resourceRepository;

    @InjectMocks
    private ResourceServiceImpl resourceService;

    private CreateResourceDto createResourceDto;
    private ResourceEntity resourceEntity;
    private ResourceResponseDto resourceResponseDto;
    private LayoutEntity layoutEntity;
    private TopicResourceEntity topicResourceEntity;

    @BeforeEach
    void setUp() {
        // Setup test data
        createResourceDto = new CreateResourceDto();
        createResourceDto.setTitle("Test Resource");
        createResourceDto.setContent("Test content");
        createResourceDto.setLayoutId(1L);

        layoutEntity = new LayoutEntity();
        layoutEntity.setIdLayout(1L);
        layoutEntity.setNombre("Test Layout");

        topicResourceEntity = new TopicResourceEntity();
        topicResourceEntity.setIdTema(1L);

        resourceEntity = new ResourceEntity();
        resourceEntity.setIdRecurso(1L);
        resourceEntity.setTitulo("Test Resource");
        resourceEntity.setContenido("Test content");
        resourceEntity.setIdLayout(1L);
        resourceEntity.setLayout(layoutEntity);
        resourceEntity.setTopicResources(Arrays.asList(topicResourceEntity));

        resourceResponseDto = new ResourceResponseDto();
        resourceResponseDto.setId(1L);
        resourceResponseDto.setTitle("Test Resource");
        resourceResponseDto.setContent("Test content");
        resourceResponseDto.setLayoutId(1L);
        resourceResponseDto.setLayoutName("Test Layout");
        resourceResponseDto.setTopicIds(Arrays.asList(1L));
    }

    @Test
    void createResource_Success() {
        // Given
        when(resourceRepository.save(any(ResourceEntity.class))).thenReturn(resourceEntity);

        // When
        ResourceResponseDto result = resourceService.createResource(createResourceDto);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Resource", result.getTitle());
        assertEquals("Test content", result.getContent());
        assertEquals(1L, result.getLayoutId());
        verify(resourceRepository).save(any(ResourceEntity.class));
    }

    @Test
    void getAllResources_Success() {
        // Given
        List<ResourceEntity> entities = Arrays.asList(resourceEntity);
        when(resourceRepository.findAll()).thenReturn(entities);

        // When
        List<ResourceResponseDto> result = resourceService.getAllResources();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Test Resource", result.get(0).getTitle());
        verify(resourceRepository).findAll();
    }

    @Test
    void getResourcesByPupilLearningPath_Success() {
        // Given
        List<ResourceEntity> entities = Arrays.asList(resourceEntity);
        when(resourceRepository.findAll()).thenReturn(entities);

        // When
        List<ResourceResponseDto> result = resourceService.getResourcesByPupilLearningPath(1, 1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(resourceRepository).findAll();
    }

    @Test
    void getResourcesByTopicLearningPath_Success() {
        // Given
        List<ResourceEntity> entities = Arrays.asList(resourceEntity);
        when(resourceRepository.findAll()).thenReturn(entities);

        // When
        List<ResourceResponseDto> result = resourceService.getResourcesByTopicLearningPath(1, 1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(resourceRepository).findAll();
    }

    @Test
    void getResourcesByPupil_Success() {
        // Given
        List<ResourceEntity> entities = Arrays.asList(resourceEntity);
        when(resourceRepository.findAll()).thenReturn(entities);

        // When
        List<ResourceResponseDto> result = resourceService.getResourcesByPupil(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(resourceRepository).findAll();
    }

    @Test
    void getResourcesByTopic_Success() {
        // Given
        List<ResourceEntity> entities = Arrays.asList(resourceEntity);
        when(resourceRepository.findAll()).thenReturn(entities);

        // When
        List<ResourceResponseDto> result = resourceService.getResourcesByTopic(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(resourceRepository).findAll();
    }

    @Test
    void getResourceById_Success() {
        // Given
        when(resourceRepository.findById(1L)).thenReturn(Optional.of(resourceEntity));

        // When
        ResourceResponseDto result = resourceService.getResourceById(1);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Resource", result.getTitle());
        assertEquals("Test content", result.getContent());
        assertEquals(1L, result.getLayoutId());
        assertEquals("Test Layout", result.getLayoutName());
        assertEquals(Arrays.asList(1L), result.getTopicIds());
        verify(resourceRepository).findById(1L);
    }

    @Test
    void getResourceById_NotFound() {
        // Given
        when(resourceRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> 
                resourceService.getResourceById(1));
        
        assertEquals("Recurso no encontrado con ID: 1", exception.getMessage());
        verify(resourceRepository).findById(1L);
    }
}
