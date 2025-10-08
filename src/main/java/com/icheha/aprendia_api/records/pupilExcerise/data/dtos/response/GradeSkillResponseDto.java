package com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeSkillResponseDto {
    
    private Long id;
    private String gradeName;
    private Long skillId;
    private String skillName;
    private Double averageScore;
    private Integer totalStudents;
}
