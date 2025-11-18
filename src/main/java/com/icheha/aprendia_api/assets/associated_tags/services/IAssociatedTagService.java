package com.icheha.aprendia_api.assets.associated_tags.services;

import java.util.List;

public interface IAssociatedTagService {
    void associateTagsToAsset(Long assetId, List<Long> tagIds);
    void removeAllTagsFromAsset(Long assetId);
}