package com.icheha.aprendia_api.preferences.words.services;

import com.icheha.aprendia_api.preferences.words.data.dtos.request.CreateWordOccupationDto;
import com.icheha.aprendia_api.preferences.words.data.dtos.response.WordOccupationResponseDto;

import java.util.List;

public interface IWordOccupationService {
    WordOccupationResponseDto create(CreateWordOccupationDto dto);
    List<WordOccupationResponseDto> findAll();
}

