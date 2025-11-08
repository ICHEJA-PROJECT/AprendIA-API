package com.icheha.aprendia_api.preferences.words.services.impl;

import com.icheha.aprendia_api.preferences.words.data.dtos.request.CreateWordOccupationDto;
import com.icheha.aprendia_api.preferences.words.data.dtos.response.WordOccupationResponseDto;
import com.icheha.aprendia_api.preferences.words.data.entities.WordOccupationEntity;
import com.icheha.aprendia_api.preferences.words.data.repositories.WordOccupationRepository;
import com.icheha.aprendia_api.preferences.words.services.IWordOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordOccupationServiceImpl implements IWordOccupationService {

    @Autowired
    private WordOccupationRepository wordOccupationRepository;

    @Override
    public WordOccupationResponseDto create(CreateWordOccupationDto dto) {
        if (wordOccupationRepository.existsByWordIdAndOccupationId(dto.getWordId(), dto.getOccupationId())) {
            throw new IllegalArgumentException("Ya existe una relación entre la palabra y la ocupación");
        }

        WordOccupationEntity entity = new WordOccupationEntity();
        entity.setWordId(dto.getWordId());
        entity.setOccupationId(dto.getOccupationId());

        WordOccupationEntity savedEntity = wordOccupationRepository.save(entity);

        WordOccupationResponseDto response = new WordOccupationResponseDto();
        response.setWordId(savedEntity.getWordId());
        response.setOccupationId(savedEntity.getOccupationId());
        return response;
    }

    @Override
    public List<WordOccupationResponseDto> findAll() {
        List<WordOccupationEntity> entities = wordOccupationRepository.findAll();
        return entities.stream()
                .map(entity -> {
                    WordOccupationResponseDto dto = new WordOccupationResponseDto();
                    dto.setWordId(entity.getWordId());
                    dto.setOccupationId(entity.getOccupationId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

