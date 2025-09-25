package com.icheha.aprendia_api.exercises.topics.controllers;


import com.icheha.aprendia_api.exercises.topics.services.ITopicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")

@Tag(name = "Topics", description = "API para gestión de temas")
public class TopicController {

    private final ITopicService topicService;

    @Autowired
    public TopicController(ITopicService topicService) {
        this.topicService = topicService;
    }
}


