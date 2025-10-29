package com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PupilExerciseResponseDto {
    
    private Long id;
    private Long pupilId;
    private String pupilName;
    private Long exerciseId;
    private String exerciseName;
    private String assignedDate;
    private String completedDate;
    private String assignedTime;
    private Boolean byTeacher;
}
