package com.icheha.aprendia_api.exercises.layouts.controllers;


import com.icheha.aprendia_api.exercises.layouts.services.ITypeLayoutService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/layouts-types")
@Tag(name = "Type Layouts", description = "API para gestión de tipos de diseño")
public class TypeLayoutController {

    private final ITypeLayoutService typeLayoutService;

    public TypeLayoutController(ITypeLayoutService typeLayoutService) {
        this.typeLayoutService = typeLayoutService;
    }



}


