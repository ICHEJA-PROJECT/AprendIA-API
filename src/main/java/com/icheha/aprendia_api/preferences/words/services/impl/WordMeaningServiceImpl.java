package com.icheha.aprendia_api.preferences.words.services.impl;

import com.icheha.aprendia_api.preferences.words.data.dtos.request.CreateWordMeaningDto;
import com.icheha.aprendia_api.preferences.words.data.dtos.response.WordMeaningResponseDto;
import com.icheha.aprendia_api.preferences.words.data.entities.WordEntity;
import com.icheha.aprendia_api.preferences.words.data.entities.WordMeaningEntity;
import com.icheha.aprendia_api.preferences.words.data.repositories.WordMeaningRepository;
import com.icheha.aprendia_api.preferences.words.data.repositories.WordRepository;
import com.icheha.aprendia_api.preferences.words.services.IWordMeaningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WordMeaningServiceImpl implements IWordMeaningService {

    @Autowired
    private WordMeaningRepository wordMeaningRepository;

    @Autowired
    private WordRepository wordRepository;

    @Override
    public WordMeaningResponseDto create(CreateWordMeaningDto dto) {
        Optional<WordEntity> wordOpt = wordRepository.findById(dto.getWordId());
        if (wordOpt.isEmpty()) {
            throw new IllegalArgumentException("Palabra no encontrada con ID: " + dto.getWordId());
        }

        WordMeaningEntity entity = new WordMeaningEntity();
        entity.setWord(wordOpt.get());
        entity.setMeaning(dto.getMeaning());

        WordMeaningEntity savedEntity = wordMeaningRepository.save(entity);

        WordMeaningResponseDto response = new WordMeaningResponseDto();
        response.setMeaningId(savedEntity.getMeaningId());
        response.setWordId(savedEntity.getWord().getId());
        response.setMeaning(savedEntity.getMeaning());
        return response;
    }

    @Override
    public List<WordMeaningResponseDto> findAll() {
        List<WordMeaningEntity> entities = wordMeaningRepository.findAll();
        return entities.stream()
                .map(entity -> {
                    WordMeaningResponseDto dto = new WordMeaningResponseDto();
                    dto.setMeaningId(entity.getMeaningId());
                    dto.setWordId(entity.getWord() != null ? entity.getWord().getId() : null);
                    dto.setMeaning(entity.getMeaning());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

