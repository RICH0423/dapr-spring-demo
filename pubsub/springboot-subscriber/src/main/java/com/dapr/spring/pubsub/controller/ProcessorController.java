package com.dapr.spring.pubsub.controller;

import com.dapr.spring.pubsub.pojo.Message;
import com.dapr.spring.pubsub.service.EventService;
import io.cloudevents.CloudEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequestMapping("/processors")
@RestController
public class ProcessorController {

    EventService eventService;

    @Autowired
    public ProcessorController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/A")
    public void subscribe(@RequestBody(required = false) byte[] body,
                          @RequestHeader Map<String, String> headers) {
        CloudEvent event = eventService.getCloudEvent(body);
        String eventId = event.getId();
        Message message = eventService.getEventMessage(event);
        log.info("Received eventId: {}, message: {}", eventId, message);
    }
}
