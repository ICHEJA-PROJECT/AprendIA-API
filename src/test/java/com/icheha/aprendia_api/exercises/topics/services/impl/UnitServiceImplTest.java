package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateUnitDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.UnitResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.UnitEntity;
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
class UnitServiceImplTest {

    @Mock
    private UnitRepository unitRepository;

    @InjectMocks
    private UnitServiceImpl unitService;

    private CreateUnitDto createUnitDto;
    private UnitEntity unitEntity;
    private UnitResponseDto unitResponseDto;

    @BeforeEach
    void setUp() {
        // Setup test data
        createUnitDto = new CreateUnitDto();
        createUnitDto.setName("Test Unit");
        createUnitDto.setDescription("Test Unit Description");

        unitEntity = new UnitEntity();
        unitEntity.setIdUnidad(1L);
        unitEntity.setNombre("Test Unit");
        unitEntity.setDescripcion("Test Unit Description");

        unitResponseDto = new UnitResponseDto();
        unitResponseDto.setId(1L);
        unitResponseDto.setName("Test Unit");
        unitResponseDto.setDescription("Test Unit Description");
    }

    @Test
    void createUnit_Success() {
        // Given
        when(unitRepository.existsByNombre("Test Unit")).thenReturn(false);
        when(unitRepository.save(any(UnitEntity.class))).thenReturn(unitEntity);

        // When
        UnitResponseDto result = unitService.createUnit(createUnitDto);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Unit", result.getName());
        assertEquals("Test Unit Description", result.getDescription());
        verify(unitRepository).existsByNombre("Test Unit");
        verify(unitRepository).save(any(UnitEntity.class));
    }

    @Test
    void createUnit_AlreadyExists() {
        // Given
        when(unitRepository.existsByNombre("Test Unit")).thenReturn(true);

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> 
                unitService.createUnit(createUnitDto));
        
        assertEquals("Ya existe una unidad con el nombre: Test Unit", exception.getMessage());
        verify(unitRepository).existsByNombre("Test Unit");
        verify(unitRepository, never()).save(any());
    }

    @Test
    void getAllUnits_Success() {
        // Given
        List<UnitEntity> entities = Arrays.asList(unitEntity);
        when(unitRepository.findAll()).thenReturn(entities);

        // When
        List<UnitResponseDto> result = unitService.getAllUnits();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Test Unit", result.get(0).getName());
        assertEquals("Test Unit Description", result.get(0).getDescription());
        verify(unitRepository).findAll();
    }

    @Test
    void getAllUnits_EmptyList() {
        // Given
        when(unitRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<UnitResponseDto> result = unitService.getAllUnits();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(unitRepository).findAll();
    }
}
