package ru.relex.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.services.dto.event.EventDto;
import ru.relex.services.service.IEventService;

import java.util.List;

@RestController
@RequestMapping(
        path = "/events",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class EventController {
    private IEventService eventService;

    @Autowired
    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    List<EventDto> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/{id}")
    EventDto findById(@PathVariable("id") int id) {
        return eventService.findById(id);
    }

    @PutMapping("/{id}")
    EventDto update(@PathVariable("id") int id, @RequestBody EventDto event) {
        event.setEventId(id);
        return eventService.update(event);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    EventDto create(@RequestBody EventDto event) {
        return eventService.create(event);
    }

}
