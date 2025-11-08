package com.icheha.aprendia_api.preferences.region.services;

import com.icheha.aprendia_api.preferences.region.data.dtos.request.CreateStudentRegionDto;
import com.icheha.aprendia_api.preferences.region.data.dtos.response.StudentRegionResponseDto;

import java.util.List;

public interface IStudentRegionService {
    StudentRegionResponseDto create(CreateStudentRegionDto dto);
    List<Long> findByStudentOnlyIds(Long studentId);
    List<StudentRegionResponseDto> findByStudent(Long studentId);
    List<Long> findByRegionOnlyIds(Long regionId);
}

