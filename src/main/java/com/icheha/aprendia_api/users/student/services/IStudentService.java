package com.icheha.aprendia_api.users.student.services;

import com.icheha.aprendia_api.users.student.data.dtos.CreateStudentDto;
import com.icheha.aprendia_api.users.student.data.dtos.RegisterStudentResponseDto;
import com.icheha.aprendia_api.users.student.data.dtos.StudentResponseDto;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    
    RegisterStudentResponseDto create(CreateStudentDto createStudentDto);
    
    List<StudentResponseDto> findByTeacher(Long teacherId);
    
    List<StudentResponseDto> findByCurp(String curp);
    
    List<StudentResponseDto> findByName(String name);
    
    Optional<StudentResponseDto> findById(Long id);
    
    List<String> findUniqueNames();
}

