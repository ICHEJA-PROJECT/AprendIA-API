package com.icheha.aprendia_api.assets.associated_tags.services.impl;

import com.icheha.aprendia_api.assets.associated_tags.data.entities.AssociatedTagsEntity;
import com.icheha.aprendia_api.assets.associated_tags.data.repositories.AssociatedTagRepository;
import com.icheha.aprendia_api.assets.associated_tags.services.IAssociatedTagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociatedTagServiceImpl implements IAssociatedTagService {

    private final AssociatedTagRepository associatedTagRepository;

    public AssociatedTagServiceImpl(AssociatedTagRepository associatedTagRepository) {
        this.associatedTagRepository = associatedTagRepository;
    }

    @Override
    public void associateTagsToAsset(Long assetId, List<Long> tagIds) {
        for (Long tagId : tagIds) {
            AssociatedTagsEntity associatedTagEntity = new AssociatedTagsEntity();
            associatedTagEntity.setId_asset(assetId);
            associatedTagEntity.setId_tag(tagId);
            associatedTagRepository.save(associatedTagEntity);
        }
    }

    @Override
    public void removeAllTagsFromAsset(Long assetId) {
        associatedTagRepository.deleteByAssetId(assetId);
    }
}
