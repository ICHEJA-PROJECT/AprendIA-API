package com.icheha.aprendia_api.preferences.words.services.impl;

import com.icheha.aprendia_api.preferences.words.data.dtos.request.CreateWordDto;
import com.icheha.aprendia_api.preferences.words.data.dtos.response.WordResponseDto;
import com.icheha.aprendia_api.preferences.words.data.entities.WordEntity;
import com.icheha.aprendia_api.preferences.words.data.repositories.WordRepository;
import com.icheha.aprendia_api.preferences.words.services.IWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements IWordService {
    
    @Autowired
    private WordRepository wordRepository;
    
    @Override
    public WordResponseDto create(CreateWordDto dto) {
        WordEntity entity = new WordEntity();
        entity.setWord(dto.getWord());
        
        WordEntity savedEntity = wordRepository.save(entity);
        
        WordResponseDto response = new WordResponseDto();
        response.setId(savedEntity.getId());
        response.setWord(savedEntity.getWord());
        return response;
    }
    
    @Override
    public List<WordResponseDto> findAll() {
        List<WordEntity> entities = wordRepository.findAll();
        return entities.stream()
                .map(entity -> {
                    WordResponseDto dto = new WordResponseDto();
                    dto.setId(entity.getId());
                    dto.setWord(entity.getWord());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public WordResponseDto findById(Long id) {
        Optional<WordEntity> entityOpt = wordRepository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new RuntimeException("Palabra no encontrada con ID: " + id);
        }
        
        WordEntity entity = entityOpt.get();
        WordResponseDto response = new WordResponseDto();
        response.setId(entity.getId());
        response.setWord(entity.getWord());
        return response;
    }
}

