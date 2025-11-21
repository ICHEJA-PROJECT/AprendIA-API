package com.icheha.aprendia_api.preferences.impairments.domain.repositories;

import com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment;

import java.util.List;

public interface IStudentImpairmentRepository {
    
    List<StudentImpairment> findByStudentId(Long studentId);
    
    List<StudentImpairment> findByStudentIdWithImpairment(Long studentId);
    
    List<Long> findStudentIdsByImpairmentId(Long impairmentId);
    
    List<Long> findImpairmentIdsByStudentId(Long studentId);
    
    List<StudentImpairment> findByImpairmentId(Long impairmentId);
    
    StudentImpairment save(StudentImpairment studentImpairment);
    
    List<StudentImpairment> findAll();
    
    // Métodos para obtener datos con relaciones cargadas (para obtener nombres)
    List<com.icheha.aprendia_api.preferences.impairments.data.entities.StudentImpairment> findAllWithImpairment();
    
    List<com.icheha.aprendia_api.preferences.impairments.data.entities.StudentImpairment> findByStudentIdWithImpairmentForList(Long studentId);
    
    java.util.Optional<com.icheha.aprendia_api.preferences.impairments.data.entities.StudentImpairment> findByStudentIdAndImpairmentId(Long studentId, Long impairmentId);
    
    boolean existsByStudentIdAndImpairmentId(Long studentId, Long impairmentId);
    
    void deleteByStudentIdAndImpairmentId(Long studentId, Long impairmentId);
    
    void deleteByStudentId(Long studentId);
}
