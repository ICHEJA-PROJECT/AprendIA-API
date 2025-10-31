package com.icheha.aprendia_api.assets.assets.domain.entities;

public class Asset {

    private final Long id;
    private final String name;
    private final String url;
    private final String description;
    private final float[] vector;

    private Asset(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.url = builder.url;
        this.description = builder.description;
        this.vector = builder.vector;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getUrl() { return url; }

    public String getDescription() { return description; }

    public float[] getVector() { return vector; }

    public static class Builder {
        private Long id;
        private String name;
        private String url;
        private String description;
        private float[] vector;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder vector(float[] vector) {
            this.vector = vector;
            return this;
        }

        public Asset build() {
            return new Asset(this);
        }
    }
}
