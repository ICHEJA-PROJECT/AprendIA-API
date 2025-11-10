package com.icheha.aprendia_api.assets.tags.services.impl;

import com.icheha.aprendia_api.assets.tags.data.dtos.request.CreateTagDto;
import com.icheha.aprendia_api.assets.tags.data.dtos.response.TagResponseDto;
import com.icheha.aprendia_api.assets.tags.data.entities.TagEntity;
import com.icheha.aprendia_api.assets.tags.data.repositories.TagRepository;
import com.icheha.aprendia_api.assets.tags.services.ITagService;
import com.icheha.aprendia_api.assets.tags.services.mappers.TagMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements ITagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagServiceImpl(
            TagRepository tagRepository,
            @org.springframework.beans.factory.annotation.Qualifier("tagServiceMapper") TagMapper tagMapper
    ) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    public TagResponseDto createTag(CreateTagDto createTagDto) {
        TagEntity tag = tagMapper.toEntity(createTagDto);
        TagEntity savedTag = tagRepository.save(tag);
        return tagMapper.toResponseDto(savedTag);
    }

    @Override
    public List<TagResponseDto> getAllTags() {
        List<TagEntity> tags = tagRepository.findAll();
        return tagMapper.toResponseDtoList(tags);
    }
}
