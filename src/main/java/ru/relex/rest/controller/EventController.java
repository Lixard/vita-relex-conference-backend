package ru.relex.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.services.dto.event.EventDto;
import ru.relex.services.dto.shedule.EventVisitorDto;
import ru.relex.services.dto.speaker.EventSpeakerDto;
import ru.relex.services.service.IEventService;
import ru.relex.services.service.IEventSpeakerService;
import ru.relex.services.service.IEventVisitorService;

import java.util.List;

@RestController
@RequestMapping(
        path = "/events",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class EventController {
    private IEventService eventService;
    private IEventSpeakerService eventSpeakerService;
    private IEventVisitorService eventVisitorService;
    @Autowired
    public EventController(IEventService eventService, IEventSpeakerService eventSpeakerService, IEventVisitorService eventVisitorService) {
        this.eventService = eventService;
        this.eventSpeakerService =  eventSpeakerService;
        this.eventVisitorService = eventVisitorService;
    }

    @GetMapping
    List<EventDto> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/{id}")
    EventDto findById(@PathVariable("id") int id) {
        return eventService.findById(id);
    }

    @GetMapping("/{id}/speakers")
    List<EventSpeakerDto> getSpeakerByEventId(@PathVariable("id") int id) {
        return eventSpeakerService.getSpeakersByEventId(id);
    }

    @GetMapping("/{id}/users")
    List<EventVisitorDto> getVisitorsByEventId(@PathVariable("id") int id) {
        return eventVisitorService.getVisitorsByEventId(id);
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
