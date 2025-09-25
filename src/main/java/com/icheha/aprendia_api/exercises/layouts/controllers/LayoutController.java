package com.icheha.aprendia_api.exercises.layouts.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;

import com.icheha.aprendia_api.exercises.layouts.services.ILayoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/layouts")
@Tag(name = "Layouts", description = "API para gestión de diseños")
public class LayoutController {

    private final ILayoutService iLayoutService;

    public LayoutController(ILayoutService iLayoutService) {
        this.iLayoutService = iLayoutService;
    }

    ResponseEntity<BaseResponse> getAllLayouts() {
        return null;
    }


}


