package com.icheha.aprendia_api.preferences.impairments.services.impl;

import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.StudentImpairmentDetailsResponseDto;
import com.icheha.aprendia_api.preferences.impairments.services.IStudentImpairmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentImpairmentServiceImpl implements IStudentImpairmentService {
    
    @Override
    public StudentImpairmentDetailsResponseDto getStudentPreferencesWithDetails(Integer id) {
        StudentImpairmentDetailsResponseDto response = new StudentImpairmentDetailsResponseDto();
        response.setStudentId(Long.valueOf(id));
        response.setStudentName("Student " + id);
        
        // Mock impairments
        List<StudentImpairmentDetailsResponseDto.ImpairmentResponseDto> impairments = new ArrayList<>();
        StudentImpairmentDetailsResponseDto.ImpairmentResponseDto impairment = new StudentImpairmentDetailsResponseDto.ImpairmentResponseDto();
        impairment.setId(1L);
        impairment.setName("Auditiva");
        impairment.setDescription("Discapacidad auditiva");
        impairments.add(impairment);
        response.setImpairments(impairments);
        
        // Mock learning path
        StudentImpairmentDetailsResponseDto.LearningPathResponseDto learningPath = new StudentImpairmentDetailsResponseDto.LearningPathResponseDto();
        learningPath.setId(1L);
        learningPath.setName("Ruta de Aprendizaje Auditiva");
        learningPath.setDescription("Ruta adaptada para estudiantes con discapacidad auditiva");
        response.setLearningPath(learningPath);
        
        return response;
    }
}
