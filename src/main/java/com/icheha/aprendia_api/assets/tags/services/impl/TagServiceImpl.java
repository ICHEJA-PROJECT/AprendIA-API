package com.icheha.aprendia_api.assets.tags.services.impl;

import com.icheha.aprendia_api.assets.tags.data.dtos.request.CreateTagDto;
import com.icheha.aprendia_api.assets.tags.data.dtos.request.UpdateTagDto;
import com.icheha.aprendia_api.assets.tags.data.dtos.response.TagResponseDto;
import com.icheha.aprendia_api.assets.tags.data.entities.TagEntity;
import com.icheha.aprendia_api.assets.tags.data.repositories.TagRepository;
import com.icheha.aprendia_api.assets.tags.services.ITagService;
import com.icheha.aprendia_api.assets.tags.services.mappers.TagMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    @Transactional(readOnly = true)
    public List<TagResponseDto> getAllTags() {
        List<TagEntity> tags = tagRepository.findAll();
        return tagMapper.toResponseDtoList(tags);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TagResponseDto> findById(Long id) {
        return tagRepository.findById(id)
                .map(tagMapper::toResponseDto);
    }
    
    @Override
    @Transactional
    public TagResponseDto update(Long id, UpdateTagDto updateTagDto) {
        TagEntity entity = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag no encontrado con ID: " + id));
        
        if (updateTagDto.getName() != null && !updateTagDto.getName().trim().isEmpty()) {
            entity.setNombre(updateTagDto.getName());
        }
        
        TagEntity updatedEntity = tagRepository.save(entity);
        return tagMapper.toResponseDto(updatedEntity);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        TagEntity entity = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag no encontrado con ID: " + id));
        tagRepository.delete(entity);
    }
}
