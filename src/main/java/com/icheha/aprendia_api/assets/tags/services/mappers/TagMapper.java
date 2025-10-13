package com.icheha.aprendia_api.assets.tags.services.mappers;

import com.icheha.aprendia_api.assets.tags.data.dtos.request.CreateTagDto;
import com.icheha.aprendia_api.assets.tags.data.dtos.response.TagResponseDto;
import com.icheha.aprendia_api.assets.tags.data.entities.TagEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("tagServiceMapper")
public class TagMapper {
    public TagEntity toEntity(CreateTagDto createTagDto) {
        TagEntity tagEntity = new TagEntity();
        tagEntity.setNombre(createTagDto.getName());
        return tagEntity;
    }

    public TagResponseDto toResponseDto(TagEntity entity) {
        TagResponseDto tagResponseDto = new TagResponseDto();
        tagResponseDto.setId(entity.getId());
        tagResponseDto.setName(entity.getNombre());
        return tagResponseDto;
    }

    public List<TagResponseDto> toResponseDtoList(List<TagEntity> entities) {
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}
