package com.icheha.aprendia_api.assets.tags.services;

import com.icheha.aprendia_api.assets.tags.data.dtos.request.CreateTagDto;
import com.icheha.aprendia_api.assets.tags.data.dtos.request.UpdateTagDto;
import com.icheha.aprendia_api.assets.tags.data.dtos.response.TagResponseDto;

import java.util.List;
import java.util.Optional;

public interface ITagService {
    TagResponseDto createTag(CreateTagDto createTagDto);
    List<TagResponseDto> getAllTags();
    Optional<TagResponseDto> findById(Long id);
    TagResponseDto update(Long id, UpdateTagDto updateTagDto);
    void delete(Long id);
}
