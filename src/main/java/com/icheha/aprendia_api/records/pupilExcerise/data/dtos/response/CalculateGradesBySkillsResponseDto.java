package com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateGradesBySkillsResponseDto {
    
    private Long skillId;
    private Double grade;
}

