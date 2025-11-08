package com.icheha.aprendia_api.preferences.impairments.services;

import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.CreateStudentImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.StudentImpairmentDetailsResponseDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.StudentImpairmentResponseDto;

import java.util.List;

public interface IStudentImpairmentService {
    
    StudentImpairmentDetailsResponseDto getStudentPreferencesWithDetails(Integer id);
    
    StudentImpairmentResponseDto create(CreateStudentImpairmentDto dto);
    
    List<StudentImpairmentResponseDto> findAll();
    
    List<Long> findByImpairmentOnlyIds(Long impairmentId);
    
    List<Long> findByStudentOnlyIds(Long studentId);
    
    List<StudentImpairmentResponseDto> findByStudent(Long studentId);
}
