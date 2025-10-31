package com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePupilExerciseDto {
    
    private String assignedDate;
    private String completedDate;
}
