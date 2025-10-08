package com.icheha.aprendia_api.exercises.templates.services.impl;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateSkillDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.GetSkillsByTemplatesDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.SkillResponseDto;
import com.icheha.aprendia_api.exercises.templates.data.entities.SkillEntity;
import com.icheha.aprendia_api.exercises.templates.data.repositories.SkillRepository;
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
class SkillServiceImplTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillServiceImpl skillService;

    private CreateSkillDto createSkillDto;
    private SkillEntity skillEntity;
    private SkillResponseDto skillResponseDto;
    private GetSkillsByTemplatesDto getSkillsByTemplatesDto;

    @BeforeEach
    void setUp() {
        // Setup test data
        createSkillDto = new CreateSkillDto();
        createSkillDto.setName("Test Skill");

        skillEntity = new SkillEntity();
        skillEntity.setIdHabilidad(1L);
        skillEntity.setNombre("Test Skill");

        skillResponseDto = new SkillResponseDto();
        skillResponseDto.setId(1L);
        skillResponseDto.setName("Test Skill");

        getSkillsByTemplatesDto = new GetSkillsByTemplatesDto();
        getSkillsByTemplatesDto.setTemplateIds(Arrays.asList(1L, 2L));
    }

    @Test
    void createSkill_Success() {
        // Given
        when(skillRepository.save(any(SkillEntity.class))).thenReturn(skillEntity);

        // When
        SkillResponseDto result = skillService.createSkill(createSkillDto);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Skill", result.getName());
        verify(skillRepository).save(any(SkillEntity.class));
    }

    @Test
    void getAllSkills_Success() {
        // Given
        List<SkillEntity> entities = Arrays.asList(skillEntity);
        when(skillRepository.findAll()).thenReturn(entities);

        // When
        List<SkillResponseDto> result = skillService.getAllSkills();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Test Skill", result.get(0).getName());
        verify(skillRepository).findAll();
    }

    @Test
    void getSkillsByTemplates_Success() {
        // Given
        List<SkillEntity> entities = Arrays.asList(skillEntity);
        when(skillRepository.findByTemplateIds(Arrays.asList(1L, 2L))).thenReturn(entities);

        // When
        List<SkillResponseDto> result = skillService.getSkillsByTemplates(getSkillsByTemplatesDto);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Test Skill", result.get(0).getName());
        verify(skillRepository).findByTemplateIds(Arrays.asList(1L, 2L));
    }

    @Test
    void getAllSkills_EmptyList() {
        // Given
        when(skillRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<SkillResponseDto> result = skillService.getAllSkills();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(skillRepository).findAll();
    }

    @Test
    void getSkillsByTemplates_EmptyList() {
        // Given
        when(skillRepository.findByTemplateIds(Arrays.asList(1L, 2L))).thenReturn(Collections.emptyList());

        // When
        List<SkillResponseDto> result = skillService.getSkillsByTemplates(getSkillsByTemplatesDto);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(skillRepository).findByTemplateIds(Arrays.asList(1L, 2L));
    }
}
