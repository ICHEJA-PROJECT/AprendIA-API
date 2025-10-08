package com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePupilSkillDto {
    
    @NotNull(message = "Pupil Exercise ID es requerido")
    private Long pupilExerciseId;
    
    @NotNull(message = "Skill ID es requerido")
    private Long skillId;
    
    @NotNull(message = "Score es requerido")
    private Double score;
}
