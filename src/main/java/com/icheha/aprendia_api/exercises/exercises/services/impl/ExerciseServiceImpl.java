package com.icheha.aprendia_api.exercises.exercises.services.impl;

import com.icheha.aprendia_api.exercises.exercises.data.dtos.request.CreateExerciseDto;
import com.icheha.aprendia_api.exercises.exercises.data.dtos.request.UpdateExerciseDto;
import com.icheha.aprendia_api.exercises.exercises.data.dtos.response.ExerciseResponseDto;
import com.icheha.aprendia_api.exercises.exercises.data.entities.ExerciseEntity;
import com.icheha.aprendia_api.exercises.exercises.data.repositories.ExerciseRepository;
import com.icheha.aprendia_api.exercises.exercises.data.repositories.IExerciseRepository;
import com.icheha.aprendia_api.exercises.exercises.services.IExerciseService;
import com.icheha.aprendia_api.exercises.exercises.services.mappers.ExerciseMapper;
import com.icheha.aprendia_api.exercises.templates.data.entities.TemplateEntity;
import com.icheha.aprendia_api.exercises.templates.data.repositories.ITemplateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements IExerciseService {
    
    @Autowired
    private ExerciseRepository exerciseRepository;
    
    @Autowired
    private IExerciseRepository iExerciseRepository;
    
    @Autowired
    private ITemplateRepository templateRepository;
    
    @Autowired
    private ExerciseMapper exerciseMapper;
    
    @Override
    public List<ExerciseResponseDto> getAllExercises() {
        List<ExerciseEntity> entities = exerciseRepository.findAll();
        return exerciseMapper.toResponseDtoList(entities);
    }
    
    @Override
    public ExerciseResponseDto createExercise(CreateExerciseDto createExerciseDto) {
        // Buscar el template por ID
        Optional<TemplateEntity> templateOpt = templateRepository.findById(createExerciseDto.getTemplateId());
        if (templateOpt.isEmpty()) {
            throw new RuntimeException("Template no encontrado con ID: " + createExerciseDto.getTemplateId());
        }
        
        // Crear la entidad del ejercicio
        ExerciseEntity exerciseEntity = exerciseMapper.toEntity(createExerciseDto, templateOpt.get());
        
        // Guardar en la base de datos
        ExerciseEntity savedEntity = exerciseRepository.save(exerciseEntity);
        
        // Convertir a DTO de respuesta
        return exerciseMapper.toResponseDto(savedEntity);
    }
    
    @Override
    public ExerciseResponseDto getExerciseById(Long id) {
        Optional<ExerciseEntity> exerciseOpt = exerciseRepository.findById(id);
        if (exerciseOpt.isEmpty()) {
            throw new RuntimeException("Ejercicio no encontrado con ID: " + id);
        }
        return exerciseMapper.toResponseDto(exerciseOpt.get());
    }
    
    @Override
    public Double getPercentageByIdAndSkill(Integer exerciseId, Integer skillId) {
        // Usar la consulta nativa del repositorio para obtener el peso
        return iExerciseRepository.findWeightByExerciseAndSkill(exerciseId.longValue(), skillId.longValue());
    }
    
    @Override
    public List<ExerciseResponseDto> getExercisesByPupil(Integer pupilId, Integer learningPathId) {
        // TODO: Implementar lógica de algoritmo genético para seleccionar ejercicios
        // Por ahora, retornamos ejercicios aleatorios limitados
        List<ExerciseEntity> entities = exerciseRepository.findAll();
        
        // Limitar a los primeros N ejercicios (simulando algoritmo genético)
        int limit = Math.min(learningPathId != null ? learningPathId : 10, entities.size());
        List<ExerciseEntity> limitedEntities = entities.stream()
                .limit(limit)
                .toList();
        
        return exerciseMapper.toResponseDtoList(limitedEntities);
    }
    
    @Override
    public List<ExerciseResponseDto> getExercisesByTemplateId(Long templateId) {
        List<ExerciseEntity> entities = exerciseRepository.findByTemplateId(templateId);
        return exerciseMapper.toResponseDtoList(entities);
    }
    
    @Override
    public ExerciseResponseDto getRandomExerciseByTemplate(Integer templateId) {
        Optional<ExerciseEntity> exerciseOpt = exerciseRepository.findRandomByTemplateId(templateId.longValue());
        if (exerciseOpt.isEmpty()) {
            throw new RuntimeException("No se encontraron ejercicios para el template con ID: " + templateId);
        }
        return exerciseMapper.toResponseDto(exerciseOpt.get());
    }
    
    @Override
    public List<Double> getPorcentages(Integer exerciseId) {
        List<Object[]> results = iExerciseRepository.findWeightsByExercise(exerciseId.longValue());
        return results.stream()
                .map(result -> {
                    // result[1] contiene el peso
                    if (result[1] instanceof Number) {
                        return ((Number) result[1]).doubleValue();
                    }
                    return 0.0;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ExerciseResponseDto> findByIds(List<Integer> ids) {
        List<Long> longIds = ids.stream()
                .map(Integer::longValue)
                .collect(Collectors.toList());
        List<ExerciseEntity> entities = iExerciseRepository.findByIds(longIds);
        return exerciseMapper.toResponseDtoList(entities);
    }
    
    @Override
    @Transactional
    public ExerciseResponseDto update(Long id, UpdateExerciseDto updateExerciseDto) {
        ExerciseEntity entity = exerciseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ejercicio no encontrado con ID: " + id));
        
        if (updateExerciseDto.getContext() != null) {
            entity.setContext(updateExerciseDto.getContext());
        }
        
        if (updateExerciseDto.getTemplateId() != null) {
            templateRepository.findById(updateExerciseDto.getTemplateId())
                    .orElseThrow(() -> new EntityNotFoundException("Template no encontrado con ID: " + updateExerciseDto.getTemplateId()));
            entity.setIdReactivo(updateExerciseDto.getTemplateId());
        }
        
        ExerciseEntity updatedEntity = exerciseRepository.save(entity);
        return exerciseMapper.toResponseDto(updatedEntity);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        ExerciseEntity entity = exerciseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ejercicio no encontrado con ID: " + id));
        exerciseRepository.delete(entity);
    }
}
