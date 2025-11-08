package com.icheha.aprendia_api.preferences.impairments.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentImpairmentResponseDto {
    
    private Long studentId;
    private Long impairmentId;
    private String impairmentName;
}

