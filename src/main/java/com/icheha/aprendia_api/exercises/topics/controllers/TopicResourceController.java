package com.icheha.aprendia_api.exercises.topics.controllers;

import com.icheha.aprendia_api.exercises.topics.services.ITopicResourceService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/topic-resource")
@Tag(name = "Topic Resources", description = "API para gestión de recursos de temas")
public class TopicResourceController {

    private final ITopicResourceService topicResourceService;
    

    @Autowired
    public TopicResourceController(ITopicResourceService topicResourceService) {
        this.topicResourceService = topicResourceService;
    }
}


