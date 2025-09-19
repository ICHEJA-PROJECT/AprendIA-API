package com.icheha.aprendia_api.core.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    @Schema(example = "400", description = "HTTP status code")
    private Integer statusCode;

    @Schema(example = "Input data validation failed", description = "Error message")
    private String message;

    @Schema(example = "VALIDATION_ERROR", description = "Error code")
    private String code;

    @Schema(example = "[\"property a must be a string\", \"property b must be a number\"]", 
            description = "Additional error details", 
            required = false)
    private List<String> details;
}
