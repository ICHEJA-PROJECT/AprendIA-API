package com.icheha.aprendia_api.preferences.impairments.services.impl;

import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.CreateResourceImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.ResourceImpairmentResponseDto;
import com.icheha.aprendia_api.preferences.impairments.data.entities.ResourceImpairment;
import com.icheha.aprendia_api.preferences.impairments.data.repositories.ResourceImpairmentRepository;
import com.icheha.aprendia_api.preferences.impairments.services.IResourceImpairmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceImpairmentServiceImpl implements IResourceImpairmentService {

    @Autowired
    private ResourceImpairmentRepository resourceImpairmentRepository;

    @Override
    public ResourceImpairmentResponseDto create(CreateResourceImpairmentDto dto) {
        if (resourceImpairmentRepository.existsByResourceIdAndImpairmentId(dto.getResourceId(), dto.getImpairmentId())) {
            throw new IllegalArgumentException("Ya existe una relación entre el recurso y la discapacidad");
        }

        ResourceImpairment entity = new ResourceImpairment();
        entity.setResourceId(dto.getResourceId());
        entity.setImpairmentId(dto.getImpairmentId());

        ResourceImpairment savedEntity = resourceImpairmentRepository.save(entity);

        ResourceImpairmentResponseDto response = new ResourceImpairmentResponseDto();
        response.setResourceId(savedEntity.getResourceId());
        response.setImpairmentId(savedEntity.getImpairmentId());
        return response;
    }

    @Override
    public List<ResourceImpairmentResponseDto> findAll() {
        List<ResourceImpairment> entities = resourceImpairmentRepository.findAll();
        return entities.stream()
                .map(entity -> {
                    ResourceImpairmentResponseDto dto = new ResourceImpairmentResponseDto();
                    dto.setResourceId(entity.getResourceId());
                    dto.setImpairmentId(entity.getImpairmentId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceImpairmentResponseDto> findByResource(Long resourceId) {
        List<ResourceImpairment> entities = resourceImpairmentRepository.findByResourceId(resourceId);
        return entities.stream()
                .map(entity -> {
                    ResourceImpairmentResponseDto dto = new ResourceImpairmentResponseDto();
                    dto.setResourceId(entity.getResourceId());
                    dto.setImpairmentId(entity.getImpairmentId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceImpairmentResponseDto> findByImpairment(Long impairmentId) {
        List<ResourceImpairment> entities = resourceImpairmentRepository.findByImpairmentId(impairmentId);
        return entities.stream()
                .map(entity -> {
                    ResourceImpairmentResponseDto dto = new ResourceImpairmentResponseDto();
                    dto.setResourceId(entity.getResourceId());
                    dto.setImpairmentId(entity.getImpairmentId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceImpairmentResponseDto> findByLearningPath(Long learningPathId) {
        List<ResourceImpairment> entities = resourceImpairmentRepository.findByLearningPathId(learningPathId);
        return entities.stream()
                .map(entity -> {
                    ResourceImpairmentResponseDto dto = new ResourceImpairmentResponseDto();
                    dto.setResourceId(entity.getResourceId());
                    dto.setImpairmentId(entity.getImpairmentId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

