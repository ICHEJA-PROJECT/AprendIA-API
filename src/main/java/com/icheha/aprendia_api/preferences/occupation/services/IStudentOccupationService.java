package com.icheha.aprendia_api.preferences.occupation.services;

import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.CreateStudentOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.response.StudentOccupationResponseDto;

import java.util.List;

public interface IStudentOccupationService {
    StudentOccupationResponseDto create(CreateStudentOccupationDto dto);
    List<Long> findByStudentOnlyIds(Long studentId);
    StudentOccupationResponseDto findByStudent(Long studentId);
    List<Long> findByOccupationOnlyIds(Long occupationId);
}

