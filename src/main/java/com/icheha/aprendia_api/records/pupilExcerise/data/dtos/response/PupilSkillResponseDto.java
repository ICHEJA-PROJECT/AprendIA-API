package com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PupilSkillResponseDto {
    
    private Long id;
    private Long pupilExerciseId;
    private String pupilName;
    private String exerciseName;
    private Long skillId;
    private String skillName;
    private Double score;
}
