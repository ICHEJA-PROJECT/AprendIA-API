package com.icheha.aprendia_api.exercises.exercises.services.mappers;

import com.icheha.aprendia_api.exercises.exercises.data.dtos.request.CreateExerciseDto;
import com.icheha.aprendia_api.exercises.exercises.data.dtos.response.ExerciseResponseDto;
import com.icheha.aprendia_api.exercises.exercises.data.entities.ExerciseEntity;
import com.icheha.aprendia_api.exercises.templates.data.entities.TemplateEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("exerciseServiceMapper")
public class ExerciseMapper {
    
    public ExerciseEntity toEntity(CreateExerciseDto dto, TemplateEntity template) {
        ExerciseEntity entity = new ExerciseEntity();
        entity.setContext(parseContext(dto.getContext()));
        entity.setTemplate(template);
        return entity;
    }
    
    public ExerciseResponseDto toResponseDto(ExerciseEntity entity) {
        ExerciseResponseDto dto = new ExerciseResponseDto();
        dto.setId(entity.getId());
        dto.setContext(entity.getContext());
        dto.setTemplateId(entity.getTemplate() != null ? entity.getTemplate().getId() : null);
        
        if (entity.getTemplate() != null) {
            dto.setTemplateNombre(entity.getTemplate().getTitulo());
            dto.setTemplateDescripcion(entity.getTemplate().getInstrucciones());
            dto.setTemplateInstrucciones(entity.getTemplate().getInstrucciones());
        }
        
        return dto;
    }
    
    public List<ExerciseResponseDto> toResponseDtoList(List<ExerciseEntity> entities) {
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    private Map<String, Object> parseContext(String context) {
        try {
            // Aquí podrías usar Jackson ObjectMapper para parsear el JSON
            // Por simplicidad, retornamos un mapa con el contexto como string
            return Map.of("context", context);
        } catch (Exception e) {
            return Map.of("context", context);
        }
    }
}
