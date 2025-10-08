package com.icheha.aprendia_api.records.pupilExcerise.data.mappers;

import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreatePupilExerciseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.PupilExerciseResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.entities.PupilExerciseEntity;
import com.icheha.aprendia_api.records.pupilExcerise.domain.entities.PupilExercise;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PupilExerciseMapper {
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public PupilExercise toDomain(PupilExerciseEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return PupilExercise.builder()
                .id(entity.getId())
                .pupilId(entity.getPupilId())
                .exerciseId(entity.getExerciseId())
                .puntuacion(0.0) // Default value since field doesn't exist
                .tiempoResolucion(parseAssignedTimeToInteger(entity.getAssignedTime()))
                .fechaRealizacion(entity.getCompletedDate())
                .completado(entity.getCompletedDate() != null)
                .intentos(1) // Default value since field doesn't exist
                .build();
    }
    
    public PupilExerciseEntity toEntity(PupilExercise domain) {
        if (domain == null) {
            return null;
        }
        
        PupilExerciseEntity entity = new PupilExerciseEntity();
        entity.setId(domain.getId());
        entity.setPupilId(domain.getPupilId());
        entity.setExerciseId(domain.getExerciseId());
        entity.setAssignedTime(parseIntegerToAssignedTime(domain.getTiempoResolucion()));
        entity.setCompletedDate(domain.getFechaRealizacion());
        entity.setByTeacher(true); // Default value
        // Note: pupil and exercise relationships should be set separately
        return entity;
    }
    
    public PupilExercise toDomain(CreatePupilExerciseDto dto) {
        if (dto == null) {
            return null;
        }
        
        return PupilExercise.builder()
                .pupilId(dto.getPupilId())
                .exerciseId(dto.getExerciseId())
                .puntuacion(0.0)
                .tiempoResolucion(0)
                .fechaRealizacion(LocalDateTime.now())
                .completado(false)
                .intentos(0)
                .build();
    }
    
    public PupilExerciseResponseDto toResponseDto(PupilExercise domain) {
        if (domain == null) {
            return null;
        }
        
        PupilExerciseResponseDto dto = new PupilExerciseResponseDto();
        dto.setId(domain.getId());
        dto.setPupilId(domain.getPupilId());
        dto.setPupilName("Pupil " + domain.getPupilId()); // TODO: Get real name
        dto.setExerciseId(domain.getExerciseId());
        dto.setExerciseName("Exercise " + domain.getExerciseId()); // TODO: Get real name
        dto.setAssignedDate(domain.getFechaRealizacion() != null ? 
                domain.getFechaRealizacion().format(DATE_FORMATTER) : null);
        dto.setCompletedDate(domain.getCompletado() ? 
                domain.getFechaRealizacion().format(DATE_FORMATTER) : null);
        dto.setAssignedTime(domain.getTiempoResolucion() != null ? 
                domain.getTiempoResolucion() + " minutos" : "0 minutos");
        dto.setByTeacher(true); // TODO: Implement logic
        return dto;
    }
    
    public List<PupilExerciseResponseDto> toResponseDtoList(List<PupilExercise> domains) {
        if (domains == null) {
            return null;
        }
        
        return domains.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    /**
     * Convierte String de tiempo asignado a Integer (minutos)
     * Ejemplo: "01:30:00" -> 90 (minutos)
     */
    private Integer parseAssignedTimeToInteger(String assignedTime) {
        if (assignedTime == null || assignedTime.trim().isEmpty()) {
            return 0;
        }
        
        try {
            // Si es un formato de tiempo HH:MM:SS
            if (assignedTime.contains(":")) {
                String[] parts = assignedTime.split(":");
                int hours = Integer.parseInt(parts[0]);
                int minutes = Integer.parseInt(parts[1]);
                return hours * 60 + minutes;
            }
            // Si es un número directo (minutos)
            return Integer.parseInt(assignedTime);
        } catch (NumberFormatException e) {
            return 0; // Valor por defecto en caso de error
        }
    }
    
    /**
     * Convierte Integer (minutos) a String de tiempo asignado
     * Ejemplo: 90 (minutos) -> "01:30:00"
     */
    private String parseIntegerToAssignedTime(Integer tiempoResolucion) {
        if (tiempoResolucion == null) {
            return "00:00:00";
        }
        
        int hours = tiempoResolucion / 60;
        int minutes = tiempoResolucion % 60;
        
        return String.format("%02d:%02d:00", hours, minutes);
    }
}
