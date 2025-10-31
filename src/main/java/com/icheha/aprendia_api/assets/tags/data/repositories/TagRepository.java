package com.icheha.aprendia_api.assets.tags.data.repositories;

import com.icheha.aprendia_api.assets.tags.data.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
