package com.icheha.aprendia_api.preferences.words.services;

import com.icheha.aprendia_api.preferences.words.data.dtos.request.CreateWordMeaningDto;
import com.icheha.aprendia_api.preferences.words.data.dtos.response.WordMeaningResponseDto;

import java.util.List;

public interface IWordMeaningService {
    WordMeaningResponseDto create(CreateWordMeaningDto dto);
    List<WordMeaningResponseDto> findAll();
}

