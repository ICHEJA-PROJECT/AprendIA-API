package com.icheha.aprendia_api.assets.associated_tags.data.repositories;

import com.icheha.aprendia_api.assets.associated_tags.data.entities.AssociatedTagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociatedTagRepository extends JpaRepository<AssociatedTagsEntity, String> {
}
