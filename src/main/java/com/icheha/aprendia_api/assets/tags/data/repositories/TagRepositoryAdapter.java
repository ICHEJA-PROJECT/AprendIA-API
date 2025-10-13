package com.icheha.aprendia_api.assets.tags.data.repositories;

import com.icheha.aprendia_api.assets.tags.data.entities.TagEntity;
import com.icheha.aprendia_api.assets.tags.data.mappers.TagMapper;
import com.icheha.aprendia_api.assets.tags.domain.entities.Tag;
import com.icheha.aprendia_api.assets.tags.domain.repositories.ITagRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TagRepositoryAdapter implements ITagRepository {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagRepositoryAdapter(
            TagRepository tagRepository,
            @org.springframework.beans.factory.annotation.Qualifier("tagDataMapper") TagMapper tagMapper
    ) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }


    @Override
    public Tag save(Tag tag) {
        TagEntity tagEntity = tagMapper.toEntity(tag);
        TagEntity savedEntity = tagRepository.save(tagEntity);
        return tagMapper.toDomain(savedEntity);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll().stream()
                .map(tagMapper::toDomain)
                .collect(Collectors.toList());
    }
}
