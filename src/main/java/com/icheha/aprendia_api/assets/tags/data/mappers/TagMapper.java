package com.icheha.aprendia_api.assets.tags.data.mappers;

import com.icheha.aprendia_api.assets.tags.data.entities.TagEntity;
import com.icheha.aprendia_api.assets.tags.domain.entities.Tag;
import org.springframework.stereotype.Component;

@Component("tagDataMapper")
public class TagMapper {

    public Tag toDomain(TagEntity tagEntity) {
        if (tagEntity == null) {
            return null;
        }

        return Tag.builder()
                .id(tagEntity.getId())
                .name(tagEntity.getNombre())
                .build();
    }

    public TagEntity toEntity(Tag tagDomain) {
        if (tagDomain == null) {
            return null;
        }

        TagEntity tagEntity = new TagEntity();
        tagEntity.setId(tagDomain.getId());
        tagEntity.setNombre(tagDomain.getName());
        return tagEntity;
    }
}
