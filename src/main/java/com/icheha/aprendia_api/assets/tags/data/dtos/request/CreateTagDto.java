package com.icheha.aprendia_api.assets.tags.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTagDto {
    private String name;

    public String getName() { return name; }
}
