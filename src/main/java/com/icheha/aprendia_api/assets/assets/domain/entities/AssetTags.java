package com.icheha.aprendia_api.assets.assets.domain.entities;

import java.util.List;

public class AssetTags {

    private final Long id;
    private final String name;
    private final String url;
    private final String description;
    private final List<String> tags;

    private AssetTags(AssetTags.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.url = builder.url;
        this.description = builder.description;
        this.tags = builder.tags;
    }

    public static AssetTags.Builder builder() {
        return new AssetTags.Builder();
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getUrl() { return url; }

    public String getDescription() { return description; }

    public List<String> getTags() { return tags; }

    public static class Builder {
        private Long id;
        private String name;
        private String url;
        private String description;
        private List<String> tags;

        public AssetTags.Builder id(long id) {
            this.id = id;
            return this;
        }

        public AssetTags.Builder name(String name) {
            this.name = name;
            return this;
        }

        public AssetTags.Builder url(String url) {
            this.url = url;
            return this;
        }

        public AssetTags.Builder description(String description) {
            this.description = description;
            return this;
        }

        public AssetTags.Builder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public AssetTags build() {
            return new AssetTags(this);
        }
    }

}
