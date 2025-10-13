package com.icheha.aprendia_api.assets.tags.domain.entities;

public class Tag {

    public final Long id;
    public final String name;

    public Tag(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static Builder builder() { return new Builder(); }

    public Long getId() { return  id; }

    public String getName() { return  name; }

    public static class Builder {
        private Long id;
        private String name;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Tag build() { return new Tag(this); }
    }
}
