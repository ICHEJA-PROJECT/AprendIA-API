package com.icheha.aprendia_api.assets.associated_tags.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssociatedTag {
    private String id;
    private String assetId;
    private String tagId;
}
