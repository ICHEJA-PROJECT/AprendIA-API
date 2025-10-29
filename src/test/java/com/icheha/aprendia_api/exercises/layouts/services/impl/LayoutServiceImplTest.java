package com.icheha.aprendia_api.exercises.layouts.services.impl;

import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.CreateLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.response.LayoutResponseDto;
import com.icheha.aprendia_api.exercises.layouts.data.entities.LayoutEntity;
import com.icheha.aprendia_api.exercises.layouts.data.entities.TypeLayoutEntity;
import com.icheha.aprendia_api.exercises.layouts.data.repositories.LayoutRepository;
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
class LayoutServiceImplTest {

    @Mock
    private LayoutRepository layoutRepository;

    @InjectMocks
    private LayoutServiceImpl layoutService;

    private CreateLayoutDto createLayoutDto;
    private LayoutEntity layoutEntity;
    private LayoutResponseDto layoutResponseDto;
    private TypeLayoutEntity typeLayoutEntity;

    @BeforeEach
    void setUp() {
        // Setup test data
        createLayoutDto = new CreateLayoutDto();
        createLayoutDto.setName("Test Layout");
        createLayoutDto.setAttributes("{\"type\": \"multiple_choice\"}");
        createLayoutDto.setTypeLayoutId(1L);

        typeLayoutEntity = new TypeLayoutEntity();
        typeLayoutEntity.setIdTipoLayout(1L);
        typeLayoutEntity.setNombre("Test Type Layout");

        layoutEntity = new LayoutEntity();
        layoutEntity.setIdLayout(1L);
        layoutEntity.setNombre("Test Layout");
        layoutEntity.setAtributos("{\"type\": \"multiple_choice\"}");
        layoutEntity.setIdTipoLayout(1L);
        layoutEntity.setTipoLayout(typeLayoutEntity);

        layoutResponseDto = new LayoutResponseDto();
        layoutResponseDto.setId(1L);
        layoutResponseDto.setName("Test Layout");
        layoutResponseDto.setAttributes("{\"type\": \"multiple_choice\"}");
        layoutResponseDto.setTypeLayoutId(1L);
        layoutResponseDto.setTypeLayoutName("Test Type Layout");
        layoutResponseDto.setResourceCount(0);
        layoutResponseDto.setTemplateCount(0);
    }

    @Test
    void createLayout_Success() {
        // Given
        when(layoutRepository.save(any(LayoutEntity.class))).thenReturn(layoutEntity);

        // When
        LayoutResponseDto result = layoutService.createLayout(createLayoutDto);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Layout", result.getName());
        assertEquals("{\"type\": \"multiple_choice\"}", result.getAttributes());
        assertEquals(1L, result.getTypeLayoutId());
        verify(layoutRepository).save(any(LayoutEntity.class));
    }

    @Test
    void getAllLayouts_Success() {
        // Given
        List<LayoutEntity> entities = Arrays.asList(layoutEntity);
        when(layoutRepository.findAll()).thenReturn(entities);

        // When
        List<LayoutResponseDto> result = layoutService.getAllLayouts();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Test Layout", result.get(0).getName());
        assertEquals("{\"type\": \"multiple_choice\"}", result.get(0).getAttributes());
        assertEquals(1L, result.get(0).getTypeLayoutId());
        assertEquals("Test Type Layout", result.get(0).getTypeLayoutName());
        verify(layoutRepository).findAll();
    }

    @Test
    void getAllLayouts_EmptyList() {
        // Given
        when(layoutRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<LayoutResponseDto> result = layoutService.getAllLayouts();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(layoutRepository).findAll();
    }
}
