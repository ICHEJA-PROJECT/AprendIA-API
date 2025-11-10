package com.icheha.aprendia_api.preferences.words.services.impl;

import com.icheha.aprendia_api.preferences.words.data.dtos.request.CreateWordDto;
import com.icheha.aprendia_api.preferences.words.data.dtos.request.UpdateWordDto;
import com.icheha.aprendia_api.preferences.words.data.dtos.response.WordResponseDto;
import com.icheha.aprendia_api.preferences.words.data.entities.WordEntity;
import com.icheha.aprendia_api.preferences.words.data.repositories.WordRepository;
import com.icheha.aprendia_api.preferences.words.services.IWordService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional(readOnly = true)
    public WordResponseDto findById(Long id) {
        WordEntity entity = wordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Palabra no encontrada con ID: " + id));
        
        WordResponseDto response = new WordResponseDto();
        response.setId(entity.getId());
        response.setWord(entity.getWord());
        return response;
    }
    
    @Override
    @Transactional
    public WordResponseDto update(Long id, UpdateWordDto updateWordDto) {
        WordEntity entity = wordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Palabra no encontrada con ID: " + id));
        
        if (updateWordDto.getWord() != null && !updateWordDto.getWord().trim().isEmpty()) {
            entity.setWord(updateWordDto.getWord());
        }
        
        WordEntity updatedEntity = wordRepository.save(entity);
        WordResponseDto response = new WordResponseDto();
        response.setId(updatedEntity.getId());
        response.setWord(updatedEntity.getWord());
        return response;
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        WordEntity entity = wordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Palabra no encontrada con ID: " + id));
        wordRepository.delete(entity);
    }
}

