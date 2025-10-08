package com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePupilExerciseDto {
    
    @NotNull(message = "Pupil ID es requerido")
    private Long pupilId;
    
    @NotNull(message = "Exercise ID es requerido")
    private Long exerciseId;
    
    private String assignedDate;
    
    private String completedDate;
    
    @NotNull(message = "Assigned Time es requerido")
    private String assignedTime;
    
    @NotNull(message = "By Teacher es requerido")
    private Boolean byTeacher;
}
