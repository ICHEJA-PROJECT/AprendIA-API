package com.icheha.aprendia_api.assets.assets.domain.entities;

public class Asset {

    private final Long id;
    private final String name;
    private final String description;
    private final String url;

    public Asset(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.url = builder.url;
    }


    public static Builder builder(){return new Builder();}

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getUrl() { return url; }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private String url;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Asset build() {return new Asset(this);}
    }


}
