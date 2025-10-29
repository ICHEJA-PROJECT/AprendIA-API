package com.icheha.aprendia_api.preferences.impairments.services;

import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.StudentImpairmentDetailsResponseDto;

public interface IStudentImpairmentService {
    
    StudentImpairmentDetailsResponseDto getStudentPreferencesWithDetails(Integer id);
}
