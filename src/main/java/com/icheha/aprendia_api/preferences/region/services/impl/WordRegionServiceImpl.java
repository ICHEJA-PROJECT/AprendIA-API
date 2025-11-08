package com.icheha.aprendia_api.preferences.region.services.impl;

import com.icheha.aprendia_api.preferences.region.data.dtos.request.CreateWordRegionDto;
import com.icheha.aprendia_api.preferences.region.data.dtos.response.WordRegionResponseDto;
import com.icheha.aprendia_api.preferences.region.data.entities.WordRegionEntity;
import com.icheha.aprendia_api.preferences.region.data.repositories.WordRegionRepository;
import com.icheha.aprendia_api.preferences.region.services.IWordRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordRegionServiceImpl implements IWordRegionService {

    @Autowired
    private WordRegionRepository wordRegionRepository;

    @Override
    public WordRegionResponseDto create(CreateWordRegionDto dto) {
        if (wordRegionRepository.existsByWordIdAndRegionId(dto.getWordId(), dto.getRegionId())) {
            throw new IllegalArgumentException("Ya existe una relación entre la palabra y la región");
        }

        WordRegionEntity entity = new WordRegionEntity();
        entity.setWordId(dto.getWordId());
        entity.setRegionId(dto.getRegionId());

        WordRegionEntity savedEntity = wordRegionRepository.save(entity);

        WordRegionResponseDto response = new WordRegionResponseDto();
        response.setWordId(savedEntity.getWordId());
        response.setRegionId(savedEntity.getRegionId());
        return response;
    }

    @Override
    public List<WordRegionResponseDto> findAll() {
        List<WordRegionEntity> entities = wordRegionRepository.findAll();
        return entities.stream()
                .map(entity -> {
                    WordRegionResponseDto dto = new WordRegionResponseDto();
                    dto.setWordId(entity.getWordId());
                    dto.setRegionId(entity.getRegionId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

