package com.icheha.aprendia_api.preferences.impairments.data.mappers;

import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.StudentImpairmentDetailsResponseDto;
import com.icheha.aprendia_api.preferences.impairments.data.entities.StudentImpairment;
import com.icheha.aprendia_api.preferences.impairments.data.repositories.LearningPathImpairmentRepository;
import com.icheha.aprendia_api.preferences.impairments.data.repositories.StudentImpairmentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentImpairmentMapper {
    
    private final LearningPathImpairmentRepository learningPathImpairmentRepository;
    private final StudentImpairmentRepository studentImpairmentRepository;
    
    public StudentImpairmentMapper(LearningPathImpairmentRepository learningPathImpairmentRepository,
                                   StudentImpairmentRepository studentImpairmentRepository) {
        this.learningPathImpairmentRepository = learningPathImpairmentRepository;
        this.studentImpairmentRepository = studentImpairmentRepository;
    }
    
    public com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment toDomain(StudentImpairment entity) {
        if (entity == null) {
            return null;
        }
        
        return com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment.builder()
                .id(entity.getStudentId())
                .studentId(entity.getStudentId())
                .impairmentId(entity.getImpairmentId())
                .build();
    }
    
    public StudentImpairmentDetailsResponseDto toResponseDto(Long studentId, 
                                                             List<com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment> studentImpairments,
                                                             String studentName) {
        StudentImpairmentDetailsResponseDto response = new StudentImpairmentDetailsResponseDto();
        response.setStudentId(studentId);
        response.setStudentName(studentName != null ? studentName : "Estudiante " + studentId);
        
        if (studentImpairments == null || studentImpairments.isEmpty()) {
            response.setImpairments(List.of());
            response.setLearningPath(null);
            return response;
        }
        
        // Mapear discapacidades usando datos reales de la entidad
        List<StudentImpairmentDetailsResponseDto.ImpairmentResponseDto> impairments = studentImpairments.stream()
                .map(this::mapImpairment)
                .collect(Collectors.toList());
        response.setImpairments(impairments);
        
        // Obtener ruta de aprendizaje de la primera discapacidad
        if (!studentImpairments.isEmpty()) {
            Long firstImpairmentId = studentImpairments.get(0).getImpairmentId();
            var learningPathList = learningPathImpairmentRepository.findFirstByImpairmentIdWithLearningPath(firstImpairmentId);
            
            // Tomar el primero de la lista (ya está ordenado por learningPathId DESC)
            if (learningPathList != null && !learningPathList.isEmpty()) {
                var learningPathEntity = learningPathList.get(0);
                StudentImpairmentDetailsResponseDto.LearningPathResponseDto learningPath = 
                        new StudentImpairmentDetailsResponseDto.LearningPathResponseDto();
                learningPath.setId(learningPathEntity.getLearningPathId());
                if (learningPathEntity.getLearningPath() != null) {
                    learningPath.setName(learningPathEntity.getLearningPath().getNombre());
                    learningPath.setDescription("Ruta de aprendizaje para discapacidad " + 
                            (learningPathEntity.getImpairment() != null ? 
                                    learningPathEntity.getImpairment().getName() : ""));
                } else {
                    learningPath.setName("Ruta de Aprendizaje " + learningPathEntity.getLearningPathId());
                    learningPath.setDescription("Ruta de aprendizaje personalizada");
                }
                response.setLearningPath(learningPath);
            } else {
                response.setLearningPath(null);
            }
        } else {
            response.setLearningPath(null);
        }
        
        return response;
    }
    
    private StudentImpairmentDetailsResponseDto.ImpairmentResponseDto mapImpairment(
            com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment studentImpairment) {
        // Buscar la entidad StudentImpairment con la relación impairment cargada
        var entities = studentImpairmentRepository.findByStudentIdWithImpairment(studentImpairment.getStudentId());
        var entityOpt = entities.stream()
                .filter(e -> e.getImpairmentId().equals(studentImpairment.getImpairmentId()))
                .findFirst();
        
        StudentImpairmentDetailsResponseDto.ImpairmentResponseDto impairment = 
                new StudentImpairmentDetailsResponseDto.ImpairmentResponseDto();
        impairment.setId(studentImpairment.getImpairmentId());
        
        if (entityOpt.isPresent() && entityOpt.get().getImpairment() != null) {
            var impairmentEntity = entityOpt.get().getImpairment();
            impairment.setName(impairmentEntity.getName());
            impairment.setDescription("Discapacidad: " + impairmentEntity.getName());
        } else {
            impairment.setName("Discapacidad " + studentImpairment.getImpairmentId());
            impairment.setDescription("Información de discapacidad no disponible");
        }
        
        return impairment;
    }
    
    public StudentImpairment toEntity(com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment domain) {
        if (domain == null) {
            return null;
        }
        
        StudentImpairment entity = new StudentImpairment();
        entity.setStudentId(domain.getStudentId());
        entity.setImpairmentId(domain.getImpairmentId());
        return entity;
    }
}
