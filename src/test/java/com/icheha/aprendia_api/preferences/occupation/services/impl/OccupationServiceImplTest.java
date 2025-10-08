package com.icheha.aprendia_api.preferences.occupation.services.impl;

import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.CreateOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.response.OccupationResponseDto;
import com.icheha.aprendia_api.preferences.occupation.data.entities.OccupationEntity;
import com.icheha.aprendia_api.preferences.occupation.data.repositories.OccupationRepository;
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
class OccupationServiceImplTest {

    @Mock
    private OccupationRepository occupationRepository;

    @InjectMocks
    private OccupationServiceImpl occupationService;

    private CreateOccupationDto createOccupationDto;
    private OccupationEntity occupationEntity;
    private OccupationResponseDto occupationResponseDto;

    @BeforeEach
    void setUp() {
        // Setup test data
        createOccupationDto = new CreateOccupationDto();
        createOccupationDto.setName("Test Occupation");

        occupationEntity = new OccupationEntity();
        occupationEntity.setId(1L);
        occupationEntity.setName("Test Occupation");
        occupationEntity.setStudents(Collections.emptyList());

        occupationResponseDto = new OccupationResponseDto();
        occupationResponseDto.setId(1L);
        occupationResponseDto.setName("Test Occupation");
        occupationResponseDto.setStudentCount(0);
        occupationResponseDto.setStudentIds(Collections.emptyList());
    }

    @Test
    void create_Success() {
        // Given
        when(occupationRepository.existsByName("Test Occupation")).thenReturn(false);
        when(occupationRepository.save(any(OccupationEntity.class))).thenReturn(occupationEntity);

        // When
        OccupationResponseDto result = occupationService.create(createOccupationDto);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Occupation", result.getName());
        assertEquals(0, result.getStudentCount());
        verify(occupationRepository).existsByName("Test Occupation");
        verify(occupationRepository).save(any(OccupationEntity.class));
    }

    @Test
    void create_AlreadyExists() {
        // Given
        when(occupationRepository.existsByName("Test Occupation")).thenReturn(true);

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> 
                occupationService.create(createOccupationDto));
        
        assertEquals("Ya existe una ocupación con el nombre: Test Occupation", exception.getMessage());
        verify(occupationRepository).existsByName("Test Occupation");
        verify(occupationRepository, never()).save(any());
    }

    @Test
    void findAll_Success() {
        // Given
        List<OccupationEntity> entities = Arrays.asList(occupationEntity);
        when(occupationRepository.findAll()).thenReturn(entities);

        // When
        List<OccupationResponseDto> result = occupationService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Test Occupation", result.get(0).getName());
        verify(occupationRepository).findAll();
    }

    @Test
    void findById_Success() {
        // Given
        when(occupationRepository.findById(1L)).thenReturn(Optional.of(occupationEntity));

        // When
        OccupationResponseDto result = occupationService.findById(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Occupation", result.getName());
        verify(occupationRepository).findById(1L);
    }

    @Test
    void findById_NotFound() {
        // Given
        when(occupationRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> 
                occupationService.findById(1L));
        
        assertEquals("Ocupación no encontrada con ID: 1", exception.getMessage());
        verify(occupationRepository).findById(1L);
    }

    @Test
    void findByNameContaining_Success() {
        // Given
        List<OccupationEntity> entities = Arrays.asList(occupationEntity);
        when(occupationRepository.findByNameContaining("Test")).thenReturn(entities);

        // When
        List<OccupationResponseDto> result = occupationService.findByNameContaining("Test");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Test Occupation", result.get(0).getName());
        verify(occupationRepository).findByNameContaining("Test");
    }

    @Test
    void deleteById_Success() {
        // Given
        when(occupationRepository.existsById(1L)).thenReturn(true);

        // When
        occupationService.deleteById(1L);

        // Then
        verify(occupationRepository).existsById(1L);
        verify(occupationRepository).deleteById(1L);
    }

    @Test
    void deleteById_NotFound() {
        // Given
        when(occupationRepository.existsById(1L)).thenReturn(false);

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> 
                occupationService.deleteById(1L));
        
        assertEquals("Ocupación no encontrada con ID: 1", exception.getMessage());
        verify(occupationRepository).existsById(1L);
        verify(occupationRepository, never()).deleteById(any());
    }

    @Test
    void existsByName_Success() {
        // Given
        when(occupationRepository.existsByName("Test Occupation")).thenReturn(true);

        // When
        boolean result = occupationService.existsByName("Test Occupation");

        // Then
        assertTrue(result);
        verify(occupationRepository).existsByName("Test Occupation");
    }

    @Test
    void getAllOccupations_Success() {
        // Given
        List<OccupationEntity> entities = Arrays.asList(occupationEntity);
        when(occupationRepository.findAll()).thenReturn(entities);

        // When
        List<Object> result = occupationService.getAllOccupations();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OccupationResponseDto);
        verify(occupationRepository).findAll();
    }
}
