package com.icheha.aprendia_api.assets.tags.services;

import com.icheha.aprendia_api.assets.tags.data.dtos.request.CreateTagDto;
import com.icheha.aprendia_api.assets.tags.data.dtos.response.TagResponseDto;

import java.util.List;

public interface ITagService {
    TagResponseDto createTag(CreateTagDto createTagDto);
    List<TagResponseDto> getAllTags();
}
