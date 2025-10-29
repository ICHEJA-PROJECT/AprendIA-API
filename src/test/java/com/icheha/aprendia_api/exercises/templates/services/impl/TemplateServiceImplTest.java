package com.icheha.aprendia_api.exercises.templates.services.impl;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTemplateDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.GetTemplatesByTopicsDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TemplateResponseDto;
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
class TemplateServiceImplTest {

    @Mock
    private ITemplateRepository templateRepository;

    @InjectMocks
    private TemplateServiceImpl templateService;

    private CreateTemplateDto createTemplateDto;
    private TemplateEntity templateEntity;
    private TemplateResponseDto templateResponseDto;
    private GetTemplatesByTopicsDto getTemplatesByTopicsDto;

    @BeforeEach
    void setUp() {
        // Setup test data
        createTemplateDto = new CreateTemplateDto();
        createTemplateDto.setTitle("Test Template");
        createTemplateDto.setInstructions("Test instructions");
        createTemplateDto.setSuggestTime("30 minutes");
        createTemplateDto.setTopic(1L);
        createTemplateDto.setLayout(1L);

        templateEntity = new TemplateEntity();
        templateEntity.setId(1L);
        templateEntity.setTitulo("Test Template");
        templateEntity.setInstrucciones("Test instructions");
        templateEntity.setSuggestTime("30 minutes");
        templateEntity.setTopicId(1L);
        templateEntity.setLayoutId(1L);

        templateResponseDto = new TemplateResponseDto();
        templateResponseDto.setId(1L);
        templateResponseDto.setTitle("Test Template");
        templateResponseDto.setInstructions("Test instructions");
        templateResponseDto.setSuggestTime("30 minutes");
        templateResponseDto.setTopicId(1L);
        templateResponseDto.setLayoutId(1L);

        getTemplatesByTopicsDto = new GetTemplatesByTopicsDto();
        getTemplatesByTopicsDto.setTopicIds(Arrays.asList(1L, 2L));
    }

    @Test
    void createTemplate_Success() {
        // Given
        when(templateRepository.save(any(TemplateEntity.class))).thenReturn(templateEntity);

        // When
        TemplateResponseDto result = templateService.createTemplate(createTemplateDto);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Template", result.getTitle());
        assertEquals("Test instructions", result.getInstructions());
        assertEquals("30 minutes", result.getSuggestTime());
        assertEquals(1L, result.getTopicId());
        assertEquals(1L, result.getLayoutId());
        verify(templateRepository).save(any(TemplateEntity.class));
    }

    @Test
    void getAllTemplates_Success() {
        // Given
        List<TemplateEntity> entities = Arrays.asList(templateEntity);
        when(templateRepository.findAll()).thenReturn(entities);

        // When
        List<TemplateResponseDto> result = templateService.getAllTemplates();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Test Template", result.get(0).getTitle());
        verify(templateRepository).findAll();
    }

    @Test
    void getTemplatesByTopicId_Success() {
        // Given
        List<TemplateEntity> entities = Arrays.asList(templateEntity);
        when(templateRepository.findByTopicId(1L)).thenReturn(entities);

        // When
        List<TemplateResponseDto> result = templateService.getTemplatesByTopicId(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(templateRepository).findByTopicId(1L);
    }

    @Test
    void getTemplatesByTopics_Success() {
        // Given
        List<TemplateEntity> entities = Arrays.asList(templateEntity);
        when(templateRepository.findByTopicIds(Arrays.asList(1L, 2L))).thenReturn(entities);

        // When
        List<TemplateResponseDto> result = templateService.getTemplatesByTopics(getTemplatesByTopicsDto);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(templateRepository).findByTopicIds(Arrays.asList(1L, 2L));
    }

    @Test
    void getTemplateById_Success() {
        // Given
        when(templateRepository.findById(1L)).thenReturn(Optional.of(templateEntity));

        // When
        TemplateResponseDto result = templateService.getTemplateById(1);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Template", result.getTitle());
        verify(templateRepository).findById(1L);
    }

    @Test
    void getTemplateById_NotFound() {
        // Given
        when(templateRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> 
                templateService.getTemplateById(1));
        
        assertEquals("Template no encontrado con ID: 1", exception.getMessage());
        verify(templateRepository).findById(1L);
    }
}
