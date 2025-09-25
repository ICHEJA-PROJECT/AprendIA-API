package com.icheha.aprendia_api.exercises.topics.controllers;

import com.icheha.aprendia_api.exercises.topics.services.ITopicSequenceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics-sequences")
@Tag(name = "Topic Sequences", description = "API para gestión de secuencias de temas")
public class TopicSequenceController {

    private final ITopicSequenceService topicSequenceService;

    @Autowired
    public TopicSequenceController(ITopicSequenceService topicSequenceService) {
        this.topicSequenceService = topicSequenceService;
    }
}


