package com.icheha.aprendia_api.assets.tags.domain.repositories;

import com.icheha.aprendia_api.assets.tags.domain.entities.Tag;

import java.util.List;

public interface ITagRepository {
    Tag save(Tag tag);
    List<Tag> findAll();
}
