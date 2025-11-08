package com.icheha.aprendia_api.preferences.impairments.services.impl;

import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.CreateReactiveImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.ReactiveImpairmentResponseDto;
import com.icheha.aprendia_api.preferences.impairments.data.entities.ReactiveImpairment;
import com.icheha.aprendia_api.preferences.impairments.data.repositories.ReactiveImpairmentRepository;
import com.icheha.aprendia_api.preferences.impairments.services.IReactiveImpairmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReactiveImpairmentServiceImpl implements IReactiveImpairmentService {

    @Autowired
    private ReactiveImpairmentRepository reactiveImpairmentRepository;

    @Override
    public ReactiveImpairmentResponseDto create(CreateReactiveImpairmentDto dto) {
        if (reactiveImpairmentRepository.existsByReactiveIdAndImpairmentId(dto.getReactiveId(), dto.getImpairmentId())) {
            throw new IllegalArgumentException("Ya existe una relación entre el reactivo y la discapacidad");
        }

        ReactiveImpairment entity = new ReactiveImpairment();
        entity.setReactiveId(dto.getReactiveId());
        entity.setImpairmentId(dto.getImpairmentId());

        ReactiveImpairment savedEntity = reactiveImpairmentRepository.save(entity);

        ReactiveImpairmentResponseDto response = new ReactiveImpairmentResponseDto();
        response.setReactiveId(savedEntity.getReactiveId());
        response.setImpairmentId(savedEntity.getImpairmentId());
        return response;
    }

    @Override
    public List<ReactiveImpairmentResponseDto> findAll() {
        List<ReactiveImpairment> entities = reactiveImpairmentRepository.findAll();
        return entities.stream()
                .map(entity -> {
                    ReactiveImpairmentResponseDto dto = new ReactiveImpairmentResponseDto();
                    dto.setReactiveId(entity.getReactiveId());
                    dto.setImpairmentId(entity.getImpairmentId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ReactiveImpairmentResponseDto> findByReactive(Long reactiveId) {
        List<ReactiveImpairment> entities = reactiveImpairmentRepository.findByReactiveId(reactiveId);
        return entities.stream()
                .map(entity -> {
                    ReactiveImpairmentResponseDto dto = new ReactiveImpairmentResponseDto();
                    dto.setReactiveId(entity.getReactiveId());
                    dto.setImpairmentId(entity.getImpairmentId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ReactiveImpairmentResponseDto> findByImpairment(Long impairmentId) {
        List<ReactiveImpairment> entities = reactiveImpairmentRepository.findByImpairmentId(impairmentId);
        return entities.stream()
                .map(entity -> {
                    ReactiveImpairmentResponseDto dto = new ReactiveImpairmentResponseDto();
                    dto.setReactiveId(entity.getReactiveId());
                    dto.setImpairmentId(entity.getImpairmentId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ReactiveImpairmentResponseDto> findByLearningPath(Long learningPathId) {
        List<ReactiveImpairment> entities = reactiveImpairmentRepository.findByLearningPathId(learningPathId);
        return entities.stream()
                .map(entity -> {
                    ReactiveImpairmentResponseDto dto = new ReactiveImpairmentResponseDto();
                    dto.setReactiveId(entity.getReactiveId());
                    dto.setImpairmentId(entity.getImpairmentId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

