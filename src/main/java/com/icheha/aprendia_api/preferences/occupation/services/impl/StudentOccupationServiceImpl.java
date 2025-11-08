package com.icheha.aprendia_api.preferences.occupation.services.impl;

import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.CreateStudentOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.response.StudentOccupationResponseDto;
import com.icheha.aprendia_api.preferences.occupation.data.entities.StudentOccupationEntity;
import com.icheha.aprendia_api.preferences.occupation.data.repositories.StudentOccupationRepository;
import com.icheha.aprendia_api.preferences.occupation.services.IStudentOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentOccupationServiceImpl implements IStudentOccupationService {

    @Autowired
    private StudentOccupationRepository studentOccupationRepository;

    @Override
    public StudentOccupationResponseDto create(CreateStudentOccupationDto dto) {
        if (studentOccupationRepository.existsByStudentIdAndOccupationId(dto.getStudentId(), dto.getOccupationId())) {
            throw new IllegalArgumentException("Ya existe una relación entre el estudiante y la ocupación");
        }

        StudentOccupationEntity entity = new StudentOccupationEntity();
        entity.setStudentId(dto.getStudentId());
        entity.setOccupationId(dto.getOccupationId());

        StudentOccupationEntity savedEntity = studentOccupationRepository.save(entity);

        StudentOccupationResponseDto response = new StudentOccupationResponseDto();
        response.setStudentId(savedEntity.getStudentId());
        response.setOccupationId(savedEntity.getOccupationId());
        return response;
    }

    @Override
    public List<Long> findByStudentOnlyIds(Long studentId) {
        List<StudentOccupationEntity> entities = studentOccupationRepository.findByStudentId(studentId);
        return entities.stream()
                .map(StudentOccupationEntity::getOccupationId)
                .collect(Collectors.toList());
    }

    @Override
    public StudentOccupationResponseDto findByStudent(Long studentId) {
        List<StudentOccupationEntity> entities = studentOccupationRepository.findByStudentId(studentId);
        if (entities.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron ocupaciones para el estudiante con ID: " + studentId);
        }
        StudentOccupationEntity entity = entities.get(0);
        StudentOccupationResponseDto response = new StudentOccupationResponseDto();
        response.setStudentId(entity.getStudentId());
        response.setOccupationId(entity.getOccupationId());
        return response;
    }

    @Override
    public List<Long> findByOccupationOnlyIds(Long occupationId) {
        List<StudentOccupationEntity> entities = studentOccupationRepository.findByOccupationId(occupationId);
        return entities.stream()
                .map(StudentOccupationEntity::getStudentId)
                .collect(Collectors.toList());
    }
}

