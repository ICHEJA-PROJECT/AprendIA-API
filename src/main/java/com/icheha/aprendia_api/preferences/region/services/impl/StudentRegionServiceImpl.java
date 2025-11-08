package com.icheha.aprendia_api.preferences.region.services.impl;

import com.icheha.aprendia_api.preferences.region.data.dtos.request.CreateStudentRegionDto;
import com.icheha.aprendia_api.preferences.region.data.dtos.response.StudentRegionResponseDto;
import com.icheha.aprendia_api.preferences.region.data.entities.StudentRegionEntity;
import com.icheha.aprendia_api.preferences.region.data.repositories.StudentRegionRepository;
import com.icheha.aprendia_api.preferences.region.services.IStudentRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentRegionServiceImpl implements IStudentRegionService {

    @Autowired
    private StudentRegionRepository studentRegionRepository;

    @Override
    public StudentRegionResponseDto create(CreateStudentRegionDto dto) {
        if (studentRegionRepository.existsByStudentIdAndRegionId(dto.getStudentId(), dto.getRegionId())) {
            throw new IllegalArgumentException("Ya existe una relación entre el estudiante y la región");
        }

        StudentRegionEntity entity = new StudentRegionEntity();
        entity.setStudentId(dto.getStudentId());
        entity.setRegionId(dto.getRegionId());

        StudentRegionEntity savedEntity = studentRegionRepository.save(entity);

        StudentRegionResponseDto response = new StudentRegionResponseDto();
        response.setStudentId(savedEntity.getStudentId());
        response.setRegionId(savedEntity.getRegionId());
        return response;
    }

    @Override
    public List<Long> findByStudentOnlyIds(Long studentId) {
        List<StudentRegionEntity> entities = studentRegionRepository.findByStudentId(studentId);
        return entities.stream()
                .map(StudentRegionEntity::getRegionId)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentRegionResponseDto> findByStudent(Long studentId) {
        List<StudentRegionEntity> entities = studentRegionRepository.findByStudentId(studentId);
        return entities.stream()
                .map(entity -> {
                    StudentRegionResponseDto dto = new StudentRegionResponseDto();
                    dto.setStudentId(entity.getStudentId());
                    dto.setRegionId(entity.getRegionId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> findByRegionOnlyIds(Long regionId) {
        List<StudentRegionEntity> entities = studentRegionRepository.findByRegionId(regionId);
        return entities.stream()
                .map(StudentRegionEntity::getStudentId)
                .collect(Collectors.toList());
    }
}

