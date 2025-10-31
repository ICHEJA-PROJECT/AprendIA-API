package com.icheha.aprendia_api.preferences.impairments.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentImpairmentDetailsResponseDto {
    
    private Long studentId;
    private String studentName;
    private List<ImpairmentResponseDto> impairments;
    private LearningPathResponseDto learningPath;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImpairmentResponseDto {
        private Long id;
        private String name;
        private String description;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LearningPathResponseDto {
        private Long id;
        private String name;
        private String description;
    }
}
