package com.icheha.aprendia_api.preferences.impairments.domain.repositories;

import com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment;

import java.util.List;

public interface IStudentImpairmentRepository {
    
    List<StudentImpairment> findByStudentId(Long studentId);
    
    List<StudentImpairment> findByStudentIdWithImpairment(Long studentId);
}
