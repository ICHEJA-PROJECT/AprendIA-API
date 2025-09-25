package com.icheha.aprendia_api.exercises.templates.controllers;


import com.icheha.aprendia_api.exercises.templates.services.ITemplateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/templates")
@Tag(name = "Templates", description = "API para gestión de plantillas")
public class TemplateController {
    private final ITemplateService templateService;
    @Autowired
    public TemplateController(ITemplateService templateService) {
        this.templateService = templateService;
    }


}


