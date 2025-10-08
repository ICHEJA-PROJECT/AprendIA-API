package com.icheha.aprendia_api.preferences.impairments.data.mappers;

import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.StudentImpairmentDetailsResponseDto;
import com.icheha.aprendia_api.preferences.impairments.data.entities.StudentImpairment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentImpairmentMapper {
    
    public com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment toDomain(StudentImpairment entity) {
        if (entity == null) {
            return null;
        }
        
        return com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment.builder()
                .id(entity.getStudentId()) // Usar studentId como id único para la entidad de dominio
                .studentId(entity.getStudentId())
                .impairmentId(entity.getImpairmentId())
                .build();
    }
    
    public StudentImpairmentDetailsResponseDto toResponseDto(Long studentId, List<com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment> studentImpairments) {
        if (studentImpairments == null || studentImpairments.isEmpty()) {
            return null;
        }
        
        StudentImpairmentDetailsResponseDto response = new StudentImpairmentDetailsResponseDto();
        response.setStudentId(studentId);
        response.setStudentName("Student " + studentId); // TODO: Get real name
        
        // Map impairments
        List<StudentImpairmentDetailsResponseDto.ImpairmentResponseDto> impairments = studentImpairments.stream()
                .map(this::mapImpairment)
                .collect(Collectors.toList());
        response.setImpairments(impairments);
        
        // TODO: Map learning path
        StudentImpairmentDetailsResponseDto.LearningPathResponseDto learningPath = new StudentImpairmentDetailsResponseDto.LearningPathResponseDto();
        learningPath.setId(1L);
        learningPath.setName("Learning Path Mock");
        learningPath.setDescription("Mock learning path");
        response.setLearningPath(learningPath);
        
        return response;
    }
    
    private StudentImpairmentDetailsResponseDto.ImpairmentResponseDto mapImpairment(com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment studentImpairment) {
        StudentImpairmentDetailsResponseDto.ImpairmentResponseDto impairment = new StudentImpairmentDetailsResponseDto.ImpairmentResponseDto();
        impairment.setId(studentImpairment.getImpairmentId());
        impairment.setName("Impairment " + studentImpairment.getImpairmentId());
        impairment.setDescription("Mock impairment description");
        return impairment;
    }
}
