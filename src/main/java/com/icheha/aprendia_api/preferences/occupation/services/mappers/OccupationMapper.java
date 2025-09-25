package com.icheha.aprendia_api.preferences.occupation.services.mappers;

import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.CreateOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.response.OccupationResponseDto;
import com.icheha.aprendia_api.preferences.occupation.data.entities.OccupationEntity;
import com.icheha.aprendia_api.preferences.occupation.domain.entities.Occupation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper para convertir entre entidades y DTOs de Occupation
 */
@Component
public class OccupationMapper {
    
    /**
     * Convertir CreateOccupationDto a entidad de dominio
     */
    public Occupation toDomain(CreateOccupationDto dto) {
        return new Occupation.Builder()
                .name(dto.getName())
                .build();
    }
    
    /**
     * Convertir entidad de dominio a DTO de respuesta
     */
    public OccupationResponseDto toResponseDto(Occupation occupation) {
        OccupationResponseDto dto = new OccupationResponseDto();
        dto.setId(occupation.getId());
        dto.setName(occupation.getName());
        dto.setStudentCount(occupation.getStudentCount());
        dto.setExerciseCount(occupation.getExerciseCount());
        
        // Mapear IDs de relaciones
        if (occupation.getStudents() != null) {
            dto.setStudentIds(occupation.getStudents().stream()
                    .map(so -> so.getStudentId())
                    .collect(Collectors.toList()));
        }
        
        
        if (occupation.getExercises() != null) {
            dto.setExerciseIds(occupation.getExercises().stream()
                    .map(eo -> eo.getExerciseId())
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }
    
    /**
     * Convertir entidad JPA a entidad de dominio
     */
    public Occupation toDomain(OccupationEntity entity) {
        return new Occupation.Builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
    
    /**
     * Convertir entidad de dominio a entidad JPA
     */
    public OccupationEntity toEntity(Occupation occupation) {
        OccupationEntity entity = new OccupationEntity();
        entity.setId(occupation.getId());
        entity.setName(occupation.getName());
        return entity;
    }
    
    /**
     * Convertir lista de entidades de dominio a lista de DTOs de respuesta
     */
    public List<OccupationResponseDto> toResponseDtoList(List<Occupation> occupations) {
        return occupations.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}
