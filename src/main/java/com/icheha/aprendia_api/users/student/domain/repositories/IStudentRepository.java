package com.icheha.aprendia_api.users.student.domain.repositories;

import com.icheha.aprendia_api.users.student.domain.entities.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository {
    
    Student create(Student student, Long personId, Long teacherId, String qrPath);
    
    Student create(Student student, Long personId, Long teacherId, String qrPath, Long createdByUserId);
    
    List<Student> findByTeacher(Long teacherId);
    
    void updateQrPath(Long studentId, String qrPath);
    
    List<Student> findByCurp(String curp);
    
    List<Student> findByName(String firstName, String paternalSurname);
    
    Optional<Student> findById(Long id);
    
    Optional<Student> findByPersonId(Long personId);
    
    List<String> findUniqueNames();
    
    Student update(Long studentId, Long teacherId, String qrPath);
    
    void delete(Long studentId);
    
    List<Student> findAll();
}

