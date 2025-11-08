package com.icheha.aprendia_api.core.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    @Schema(example = "true")
    private Boolean success;

    @Schema
    private T data;

    @Schema(example = "Request successful")
    private String message;

    @Schema
    @JsonIgnore
    private HttpStatus status;

    public ResponseEntity<BaseResponse<T>> buildResponseEntity() {
        return new ResponseEntity<>(this, this.status);
    }
}
