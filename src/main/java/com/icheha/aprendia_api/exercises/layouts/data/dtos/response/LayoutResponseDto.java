package com.icheha.aprendia_api.exercises.layouts.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LayoutResponseDto {
    
    private Long id;
    private String name;
    private String attributes;
    private Long typeLayoutId;
    private String typeLayoutName;
    private Integer resourceCount;
    private Integer templateCount;
}
