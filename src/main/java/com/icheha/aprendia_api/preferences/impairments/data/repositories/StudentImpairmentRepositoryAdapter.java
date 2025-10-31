package com.icheha.aprendia_api.preferences.impairments.data.repositories;


import com.icheha.aprendia_api.preferences.impairments.data.mappers.StudentImpairmentMapper;
import com.icheha.aprendia_api.preferences.impairments.domain.repositories.IStudentImpairmentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentImpairmentRepositoryAdapter implements IStudentImpairmentRepository {
    
    private final StudentImpairmentRepository studentImpairmentRepository;
    private final StudentImpairmentMapper studentImpairmentMapper;
    
    public StudentImpairmentRepositoryAdapter(StudentImpairmentRepository studentImpairmentRepository, 
                                            StudentImpairmentMapper studentImpairmentMapper) {
        this.studentImpairmentRepository = studentImpairmentRepository;
        this.studentImpairmentMapper = studentImpairmentMapper;
    }
    
    @Override
    public List<com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment> findByStudentId(Long studentId) {
        return studentImpairmentRepository.findByStudentId(studentId).stream()
                .map(studentImpairmentMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment> findByStudentIdWithImpairment(Long studentId) {
        return studentImpairmentRepository.findByStudentIdWithImpairment(studentId).stream()
                .map(studentImpairmentMapper::toDomain)
                .collect(Collectors.toList());
    }
}
