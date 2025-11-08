package com.icheha.aprendia_api.preferences.region.services;

import com.icheha.aprendia_api.preferences.region.data.dtos.request.CreateWordRegionDto;
import com.icheha.aprendia_api.preferences.region.data.dtos.response.WordRegionResponseDto;

import java.util.List;

public interface IWordRegionService {
    WordRegionResponseDto create(CreateWordRegionDto dto);
    List<WordRegionResponseDto> findAll();
}

